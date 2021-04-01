package com.example.grpc.client.grpcclient.Theads;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class TasksProcess {

    private List<StubManagement.StubInit> stubInitList;
    private StubManagement stubManagement = new StubManagement<>();
    // create a thread poor
    public ExecutorService executor = Executors.newFixedThreadPool(50);
    public Map<String, List<int[][]>> addTaskMap;
    public CountDownLatch latch;

    /**
     * reformat the result of multiply tasks and create the new map for blocks adding
     * @param resMultiplyList
     */
    public void initAddTaskMap (List<Map<String, int[][]>> resMultiplyList){
        /**
         * the resMultiplyList:
         * e.g. [{0 0:[[1,2],[3,4]]},{0 0:[[1,2],[3,4]]}...] ("0 0" means the location supposed in the result matrix)
         *
         * the addTaskMap:
         * e.g. {0 0:[[[1,2][3,4]],[[1,2][3,4]]]...}...
         */
        addTaskMap = new HashMap();
        List<int[][]> tempList = new ArrayList();

        Map tempMap = new HashMap();
        for (Map<String, int[][]> resMultiplyMap:resMultiplyList){
            for (String key: resMultiplyMap.keySet()){
                if (!tempMap.containsKey(key)){
                    tempMap.put(key,null);
                    tempList = new ArrayList<>();
                    addTaskMap.put(key, tempList);
                }
                tempList.add(resMultiplyMap.get(key));
            }
        }
    }


    /**
     * run blocks tasks function, including adding and multiplying tasks
     * @param splitMatrixA
     * @param splitMatrixB
     * @param numberServer
     * @return Map<String, List<int[][]>>
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<String, List<int[][]>> runTasks(List<List<int[][]>> splitMatrixA, List<List<int[][]>> splitMatrixB, int numberServer) throws ExecutionException, InterruptedException {
        List<Map<String, int[][]>> resMultiplyList = multiplyTasks(splitMatrixA,splitMatrixB,numberServer);

        // reformat the result of multiply tasks
        initAddTaskMap(resMultiplyList);

        // get the result map after blocks adding
        Map<String,List<int[][]>> resAddMap = addTasks();
        return resAddMap;
    }

    /**
     * run blocks multiplying tasks function
     * @param splitMatrixA
     * @param splitMatrixB
     * @param numberServer
     * @return List<Map<String, int[][]>>
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<Map<String, int[][]>> multiplyTasks(List<List<int[][]>> splitMatrixA, List<List<int[][]>> splitMatrixB, int numberServer) throws ExecutionException, InterruptedException {
        List<List> multiplyTaskList = new ArrayList();
        List tempList = new ArrayList();

        // initial the list of stubs
        stubInitList = getStubs(9092, numberServer);

        // create multiply tasks
        int i = 0;
        for (int x = 0; x < splitMatrixA.size(); x++){
            for (int y = 0; y < splitMatrixB.get(x).size(); y++){
                for (int z = 0; z < splitMatrixB.size(); z++){
                    MultiplyTask t = new MultiplyTask(x +" "+ y, splitMatrixA.get(x).get(z), splitMatrixB.get(z).get(y), stubInitList.get(i++).getStub());
                    tempList.add(t);
                    if (tempList.size() == numberServer){
                        i = 0;
                        multiplyTaskList.add(tempList);
                        tempList = new ArrayList();
                    }
                }
            }
        }
        if (!tempList.isEmpty()){
            multiplyTaskList.add(tempList);
        }

        // run multiply tasks and get the result
        List<Map<String, int[][]>> resMultiplyList = new ArrayList();
        for (List taskList:multiplyTaskList){
            List<Map> tempResList = (grpcMultiplyCalls(taskList));
            for (Map<String, int[][]> resMultiplyMap:tempResList){
                resMultiplyList.add(resMultiplyMap);
            }
        }
        return resMultiplyList;
    }

    /**
     * run blocks adding tasks function
     * @param
     * @return Map<String,List<int[][]>>
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<String,List<int[][]>> addTasks() throws InterruptedException {
        int num = addTaskMap.get("0 0").size();    // the number of blocks in each dimension
        int addCount = num*num*(num-1);            // the number of blocks adding
        latch = new CountDownLatch(addCount);
        for (String key:addTaskMap.keySet()){
            checkAddAction(key);                   // do adding tasks
        }
        latch.await();
        return addTaskMap;
    }

    /**
     * check if there is a new blocks adding task can be initiate
     * @param key
     */
    public void checkAddAction(String key){
        int count = 0;
        List<int[][]> list = addTaskMap.get(key);
        String location = key;
        int numberServer = stubInitList.size();    // the number of server

        while(list.size() >= 2){
            // get every two first matrixes to do adding tasks and remove them from the addTaskMap
            int[][] A =  list.remove(0);
            int[][] B =  list.remove(0);
            AddTask task = new AddTask(location, A, B, stubInitList.get(count++%numberServer).getStub(), this);
            executor.submit(task);
        }
    }

    /**
     * get the list of stubs
     * @param initPort
     * @param numberServer
     * @return List
     */
    public List getStubs(int initPort, int numberServer){
        // the list of grpc server addresses
        List<StubManagement.GrpcAddress> addressList = stubManagement.getAddressList(initPort, numberServer);
        // initial stubs
        stubManagement.stubList(addressList);
        List<StubManagement.StubInit> stubInitList = stubManagement.getStubs();
        return stubInitList;
    }

    /**
     * grpcCalls for blocks multiplying
     * @param taskList
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    static List<Map> grpcMultiplyCalls(List<MultiplyTask> taskList) throws InterruptedException, ExecutionException {

        int numThead = taskList.size();    // the number of threads
        CountDownLatch latch = new CountDownLatch(numThead);
        ExecutorService executor = Executors.newFixedThreadPool(numThead);

        // multithreaded process
        List<Future> futureList = new ArrayList();
        for (MultiplyTask task:taskList){
            task.setLatch(latch);
            Future<Map> future = executor.submit(task);
            futureList.add(future);
        }
        latch.await();    // wait for all threads being done
        executor.shutdown();

        // get results
        List<Map> resList = new ArrayList<>();
        for (Future<Map> future:futureList){
            resList.add(future.get());
        }
        return resList;
    }
}

package com.example.grpc.client.grpcclient.Rest;

import com.example.grpc.client.grpcclient.Theads.MultiplyTask;
import com.example.grpc.client.grpcclient.Theads.StubManagement;
import com.example.grpc.client.grpcclient.Theads.TasksProcess;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;

@Service
public class GRPCClientService {
	private int blockSize = 0;
	private Map<String, int[][]> matrixMap = new HashMap();
	private List<List<int[][]>> splitMatrixA = new ArrayList();
	private List<List<int[][]>> splitMatrixB = new ArrayList();
	public TasksProcess tasksProcess = new TasksProcess();

	/**
	 * read matrix file and save to matrixMap
	 * @param name
	 * @param file
	 * @return int[][]
	 */
	public int[][] readFile (String name, MultipartFile file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
		List<String> result = new ArrayList<String>();
		String line = null;

		// read each line in the matrix file
		while ((line = br.readLine()) != null){
			result.add(line);
		}

		//judge whether the matrix meets the requirement (the power of 2)
		int rows = matrixSize(result);
		if (rows == 0){
			return null;
		}
		else {
			int[][] matrix = new int[rows][rows];
			for (int i = 0; i < rows; i++){
				String[] row = result.get(i).split(" ");
				for(int j = 0; j< rows; j++){
					matrix[i][j] = Integer.parseInt(row[j]);
				}
			}
			matrixMap.put(name, matrix);
			return matrix;
		}
	}

	/**
	 * Judge the matrix size
	 * @param list
	 * @return int
	 */
	public int matrixSize(List<String> list) {
		// read no thing in the matrix file
		if (list == null || list.isEmpty()) {
			System.out.println("can not read the matrix!");
			return 0;
		}
		else {
			int rows = list.size();
			String[] str = list.get(0).split(" ");
			int cols = str.length;
			// the size of row and column are not equal
			if (rows != cols) {
				System.out.println("the size of row and column are not equal!");
				return 0;
			}
			else {
				if ((rows & (rows - 1)) == 0) return rows;
				else return 0;
			}
		}
	}

	public Map<String, int[][]> getMatrix(){
    	return matrixMap;
	}

	/**
	 * split matrix A and B
	 * @param blockSize
	 * @return Map<String, List>
	 */
	public Map<String, List> splitMatrix(int blockSize){
		this.blockSize = blockSize;
		splitMatrixA = new ArrayList<>();
		splitMatrixB = new ArrayList<>();

		// get matrix A and B
		int[][] A = (int[][]) matrixMap.values().toArray()[0];
		int[][] B = (int[][]) matrixMap.values().toArray()[matrixMap.size()-1];

		int n = A.length/blockSize;    // the number of blocks in each dimension

		// split A and B and save as splitMatrixA and splitMatrixB
    	for (int i = 0; i < n; i++){
			List<int[][]> aRowList = new ArrayList();
			List<int[][]> bRowList = new ArrayList();
    		for (int j = 0; j < n; j++){
				int[][] resA = new int[blockSize][blockSize];
				int[][] resB = new int[blockSize][blockSize];
				for (int k = 0; k < blockSize; k++){
					for (int t = 0; t < blockSize; t++){
						resA[k][t] = A[i*blockSize+k][j*blockSize+t];
						resB[k][t] = B[i*blockSize+k][j*blockSize+t];
					}
				}
				aRowList.add(resA);
				bRowList.add(resB);
			}
    		splitMatrixA.add(aRowList);
			splitMatrixB.add(bRowList);
		}

    	// judge whether matrix A and B can be split
		if (!splitMatrixA.isEmpty() && !splitMatrixB.isEmpty()){
			System.out.println("the number of blocks after splitting A and B is "+ n*n +", row: "+n+", col: "+n);

			Map splitData = new HashMap();
			splitData.put("splitMatrixA", splitMatrixA);
			splitData.put("splitMatrixB", splitMatrixB);
			return splitData;
		}
    	else {
    		System.out.println("matrixes split error!");
    		return null;
		}
    }

	/**
	 * footPrinting function, run 100 times for single block in one server and get the average
	 * @param a
	 * @param b
	 * @return long
	 * @throws InterruptedException
	 */
	public long footPrinting(int[][] a, int[][] b) throws InterruptedException {
		long sum = 0;
		int m = 100;    // 100 times
		for (int i = 0; i < m; i++){
			final CountDownLatch latch = new CountDownLatch(1);
			// initial stub
			List<StubManagement.StubInit> stubInitList = tasksProcess.getStubs(9092, 1);

			// create one task
			MultiplyTask task = new MultiplyTask(a, b, stubInitList.get(0).getStub(), latch);
			ExecutorService executor = Executors.newFixedThreadPool(1);
			executor.submit(task);
			latch.await();
			executor.shutdown();

			long footprint = task.getFootPrint();
			sum += footprint;
		}
		long footprint= sum/m;    // average value
		return footprint;
	}

	/**
	 * scaling function to determine the number of grpc server and workloads
	 * @param deadline
	 * @return java.util.Map
	 */
	public Map scaling (long deadline) throws InterruptedException {
		Map scalResMap = new HashMap();

		// the time of footPrint
		long footPrint = footPrinting(splitMatrixA.get(0).get(0), splitMatrixB.get(0).get(0));
		scalResMap.put("footPrint", footPrint);
		System.out.println("the time of footPrinting for one block is " + footPrint);

		// the number of multiply tasks (ignore the adding time)
		long numBlockCalls = 0;
		for (int x = 0; x < splitMatrixA.size(); x++){
			for (int y = 0; y < splitMatrixB.get(x).size(); y++){
				for (int z = 0; z < splitMatrixB.size(); z++){
					numBlockCalls ++;
				}
			}
		}
		long numberServer = (footPrint * numBlockCalls) / deadline;
		System.out.println("the  minimal number of servers required to achieve the deadline of " + deadline + " is " + numberServer);
		scalResMap.put("numberServer", numberServer);
		return scalResMap;
	}

	/**
	 * multiply blocks function
	 * @param numberServer
	 * @return int[][]
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public int[][] multiplyBlock (int numberServer) throws InterruptedException, ExecutionException {

		long startTime = System.nanoTime();

        // do the blocks multiplication tasks (including multiply and add tasks)
		Map<String,List<int[][]>> resAddMap = tasksProcess.runTasks(splitMatrixA, splitMatrixB, numberServer);
		if (resAddMap.isEmpty() || resAddMap == null){
			System.out.println("the matrixes multiplication error!");
			return null;
		}
		else {
			// get the result matrix
			int[][] result = new int[blockSize][blockSize];
			result = getResultMatrix(resAddMap);
			if (result.length == 0){
				System.out.println("blocks failed to combine!");
				return null;
			}

			long endTime = System.nanoTime();
			System.out.println("the time spending for "+numberServer+" servers is: "+(endTime - startTime));

			return result;
		}
	}

	/**
	 * add blocks method
	 * @param addTaskMap
	 * @param blockSize
	 * @return java.util.Map
	 */
	public Map addBlocks(Map<String, List<int[][]>> addTaskMap, int blockSize){
		Map resAddMap = new HashMap();
		for (String key:addTaskMap.keySet()){
			int[][] res = new int[blockSize][blockSize];
			for (int[][] matrix:addTaskMap.get(key)){
				for (int j = 0; j < blockSize; j++){
					for (int k = 0; k < blockSize; k++){
						res[j][k] = res[j][k] + matrix[j][k];
					}
				}

			}
			resAddMap.put(key, res);
		}
		return resAddMap;
	}


	/**
	 * combine blocks and get the result matrix
	 * @param resAddMap
	 * @return int[][]
	 */
	public int[][] getResultMatrix(Map<String,List<int[][]>> resAddMap){
		int[][] A = (int[][]) matrixMap.values().toArray()[0];
		int[][] result = new int[A.length][A.length];
		for (String key:resAddMap.keySet()){
			String[] location = key.split(" ");
			int row = Integer.parseInt(location[0]);    // row index
			int col = Integer.parseInt(location[1]);    // column index
			int[][] matrix = resAddMap.get(key).get(0);
			for (int i = 0; i < blockSize; i++){
				for (int j = 0; j < blockSize; j++){
					result[row*blockSize+i][col*blockSize+j] = matrix[i][j];
				}
			}
		}
		return result;
	}
}

import os;
for i in range(3):
    port = 10000 + i
    os.system('mvnw spring-boot:run -Dserver.port=' + port)
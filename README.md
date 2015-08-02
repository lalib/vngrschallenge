# vngrschallenge

#Running

You can run application with typing java -jar vngrschallenge-0.0.1-SNAPSHOT.jar

The default mongodb connection properties are for the localhost. If you want to override them, you can give parameters as JVM arguments such as

-Dspring.data.mongodb.host=YOUR?MONGODB_HOST
-Dspring.data.mongodb.port=YOUR_MONGODB_PORT
-Dspring.data.mongodb.database=YOUR_MONGODB_DATABASE

The default settings can be found in src/main/resources/application.properties as

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=vngrschallenge

The tests are under the src/test/java

#Usage

There are 3 options in program. 

1 - File Insert : You given the file path then it processes.
2 - Name Search : If you'd like to search name of a contact. you can use this menu.
3 - Exit        : End the program.

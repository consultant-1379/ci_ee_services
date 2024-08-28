=======================================
EniqEventsServices Project Description
=======================================

The aim of the project structure is to keep deployable resources and testing resources clearly seperated in different folders, while complying with the maven standard project structure.

+-build
|   The output folder for the build process
|   
+-install
|   Contains the apache ant instructions for installing the application on the server
|
+-src
| +--main
| |    +--java
| |    |     Contains the java source code for the application
| |    +--resources
| |    |     Contains the runtime resources of the application (velocity templates, 
| |    |     UIMetaData, etc)
| |    +--webapp
| |          Contains the deployable webapp resources for the application (ie: web.xml)
| |          
| +--test
|     +--java
|     |     Contains junit tests for the application
|     +--resources
|           Contains the test resources for the application
|   
+-pom.xml
   The maven configuration file
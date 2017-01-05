# Gamesys Coding Exercise #

This is a project provided by Gamesys as a coding exercise.

The task is a restful registration with exclusion services

## Java Versions ##
It was mentioned over the phone Java 8 could be used but the docuemntation says Java 7. Thus I have provided two versions:

* Head/[aaf04b3](https://bitbucket.org/jackomonster/coding-exercise/commits/aaf04b322361f329ed554cbc34d489300b7759c4) - Java 7 Version
* [078e412](https://bitbucket.org/jackomonster/coding-exercise/commits/078e412da314d91fa3aa0f8002cea91e3e466e40) - Java 8 Version

## Installation/Build Enviroment ##
* Maven
* Java 7

### Build Instructions ###
The web service module runs under the Maven Tomcat plugin.

Execute maven against the */Gamesys Interview/web-services*
```
#!bash

mvn -Dtomcat.port=8080 -Dtomcat.path=/ -B tomcat7:run
```
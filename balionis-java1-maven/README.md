# Why?

This package is meant to provide me with the prototype of build, test, package and dist java project with maven.

# How?

## Setup

1. Download bindary distribution (apache-maven-3.3.9-bin.zip) from https://maven.apache.org/download.cgi
2. Unzip the zip at C:\bin\apache-maven-3.3.9
3. set MAVEN_HOME=C:\bin\apache-maven-3.3.9
4. set PATH=%MAVEN_HOME%\bin;%PATH%

## Compile
 
```
maven clean package
```

_Note: the artifact should be produced at balionis-java1-maven\target\balionis-java1-maven-1.0-SNAPSHOT.jar_

## Publish

```
gradle install
```

_Note: If C:\Users\%USERNAME%\.m2\settings.xml local repository points to <localRepository>c:/share/repository</localRepository> 
then the balionis-java1-maven-1.0-SNAPSHOT.jar is copied to c:\share\repository\com\balionis\balionis-java1-maven\1.0-SNAPSHOT\balionis-java1-maven-1.0-SNAPSHOT.jar_

## Run

```
mvn exec:java
```

_Note: If all goes well then the command line output should look like this_
```
[INFO] ------------------------------------------------------------------------
[INFO] Building balionis-java1-maven 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- exec-maven-plugin:1.5.0:java (default-cli) @ balionis-java1-maven ---
argument1:argument2
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.859 s
```

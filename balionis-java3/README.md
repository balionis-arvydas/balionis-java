# Why?

This package is meant to provide me with the java stream classes.

# How?

## Setup

see balionis-java1-gradle

## Compile

```
gradle clean build
```

_Note: the artifact should be produced at balionis-java3\build\libs\balionis-javaStream1-1.0-SNAPSHOT.jar_

## Publish

```
gradle install
```

_Note: If LOCAL_MAVEN_REPOSITORY at gradle.properties is left unchanged then the artifact should be produced at C:\share\repository\com\balionis\balionis-java3\1.0-SNAPSHOT_

## Run

```
gradle run1 -Dexec.args="myru1234nner"
```

_Note: If all goes well then the command line output should look like this_
```
C:\com.balionis\dev\balionis-java\balionis-java3>gradle run1 -Dexec.args="myru1234nner"
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run1
Apr 12, 2018 12:41:47 PM com.balionis.java3.MyApp1 main
INFO: main: name=myru1234nner
Apr 12, 2018 12:41:47 PM com.balionis.java3.MyApp1 run
INFO: run: name=myru1234nner, num1=1234 done...
Apr 12, 2018 12:41:47 PM com.balionis.java3.MyApp1 main
INFO: main: done

BUILD SUCCESSFUL

Total time: 2.044 secs
```

# Why?

This package is meant to provide me with the prototype of build, test, package and dist java project with gradle.
This package also uses Mockito to run unit tests.

# How?

## Setup

1. Download bindary distribution (gradle-3.2.1-bin.zip) from https://gradle.org/gradle-download/
2. Unzip the zip at C:\bin\gradle-3.2.1
3. set GRADLE_HOME=C:\bin\gradle-3.2.1
3. set PATH=%GRADLE_HOME%\bin;%PATH%

## Compile

```
gradle clean build
```

_Note: the artifact should be produced at balionis-java1-gradle\build\libs\balionis-java1-gradle-1.0-SNAPSHOT.jar_

## Publish

```
gradle install
```

_Note: If LOCAL_MAVEN_REPOSITORY at gradle.properties is left unchanged then the artifact should be produced at C:\share\repository\com\balionis\balionis-java1-gradle\1.0-SNAPSHOT_

## Run

```
gradle run -Dmyvariable=myvalue -Dexec.args="myarg1 myarg2"
```

_Note: If all goes well then the command line output should look like this_
```
>gradle run -Dmyvariable=myvalue -Dexec.args="myarg1 myarg2"
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run
myarg1:myarg1:myarg2

BUILD SUCCESSFUL

Total time: 1.781 secs
```

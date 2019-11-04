# Why?

This package is meant to provide me with the prototype of build, test, package and dist java project with gradle.
This package also uses Mockito to run unit tests.
This package is meant to provide ICE sandbox. 

# How?

## Compile

```
gradlew clean build
```

_Note: the artifact should be produced at balionis-java4-gradle\build\libs\balionis-java4-gradle-1.0-SNAPSHOT.jar_

## Publish

```
gradle install
```

_Note: If LOCAL_MAVEN_REPOSITORY at gradle.properties is left unchanged then the artifact should be produced at C:\share\repository\com\balionis\balionis-java4-gradle\1.0-SNAPSHOT_

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

# Why?

This package is meant to provide me with the prototype of spring boot (web).

# How?

## Setup


## Compile

```
gradle clean build
```

_Note: the artifact should be produced at balionis-spring5-restful\build\libs\balionis-spring5-restful-1.0-SNAPSHOT.jar_

## Publish

```
gradle install
```

_Note: If LOCAL_MAVEN_REPOSITORY at gradle.properties is left unchanged then the artifact should be produced at C:\share\repository\com\balionis\balionis-spring5-restful\1.0-SNAPSHOT_

## Run

```
gradle run -Dprofile=prod -Dexec.args="--service.name=aService"
```

_Note: If all goes well then the command line output should look like this_
```
> gradle run -Dprofile=prod -Dexec.args="--service.name=aService"
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run
...
BUILD SUCCESSFUL

Total time: 4.477 secs
```

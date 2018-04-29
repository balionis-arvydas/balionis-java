# Why?

This package is meant to provide me with the prototype of spring boot (standalone).

# How?

## Setup


## Compile

```
gradle clean build
```

_Note: the artifact should be produced at balionis-spring5-standalone\build\libs\balionis-spring5-standalone-1.0-SNAPSHOT.jar_

## Publish

```
gradle install
```

_Note: If LOCAL_MAVEN_REPOSITORY at gradle.properties is left unchanged then the artifact should be produced at C:\share\repository\com\balionis\balionis-spring5-standalone\1.0-SNAPSHOT_

## Run

```
gradle run -Dprofile=prod -Dexec.args="--service.name=aService --msg=aMsg"
```

_Note: If all goes well then the command line output should look like this_
```
> gradle run -Dprofile=prod -Dexec.args="--service.name=aService --msg=aMsg"
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run
2018/04/28 18:47:43:194 BST [INFO] MyApp - Starting MyApp on DESKTOP-EA31FOQ with PID 13828 (balionis-spring5-batch\build\classes\main started by arvydas in balionis-spring5-batch)
2018/04/28 18:47:43:194 BST [DEBUG] MyApp - Running with Spring Boot v2.0.1.RELEASE, Spring v5.0.5.RELEASE
2018/04/28 18:47:43:194 BST [INFO] MyApp - No active profile set, falling back to default profiles: default
2018/04/28 18:47:43:304 BST [INFO] AnnotationConfigApplicationContext - Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@67e2d983: startup date [Sat Apr 28 18:47:43 BST 2018]; root of context hierarchy
2018/04/28 18:47:44:601 BST [INFO] AnnotationMBeanExporter - Registering beans for JMX exposure on startup
2018/04/28 18:47:44:632 BST [INFO] MyApp - Started MyApp in 2.282 seconds (JVM running for 2.866)
2018/04/28 18:47:44:632 BST [INFO] MyApp - run: args={arg[msg]=[aMsg], arg[service.name]=[aService]}
2018/04/28 18:47:44:632 BST [INFO] MyApp - run: res=aService@prod:aMsg
2018/04/28 18:47:44:632 BST [INFO] AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@67e2d983: startup date [Sat Apr 28 18:47:43 BST 2018]; root of context hierarchy
2018/04/28 18:47:44:648 BST [INFO] AnnotationMBeanExporter - Unregistering JMX-exposed beans on shutdown

BUILD SUCCESSFUL

Total time: 4.477 secs
```

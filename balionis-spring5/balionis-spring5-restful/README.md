# Why?

This package is meant to provide me with the prototype of spring boot (web).

# How?

## Setup

Text

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
gradle run -Dprofile=prod -Dexec.args="--server.port=8090 --service.name=aService --msg=aMsg"
```

_Note: If all goes well then the command line output should look like this_
```
>gradle run -Dprofile=prod -Dexec.args="--server.port=8090 --service.name=aService --msg=aMsg"
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.1.RELEASE)

2018/04/29 18:23:28:602 BST [INFO] MyApp - Starting MyApp on DESKTOP-EA31FOQ with PID 7652 (C:\com.balionis\dev\balionis-java\balionis-spring5\balionis-spring5-restful\build\classes\main started by arvydas in C:\com.balionis\dev\balionis-java\balionis-spring5\balionis-spring5-restful)
2018/04/29 18:23:28:618 BST [DEBUG] MyApp - Running with Spring Boot v2.0.1.RELEASE, Spring v5.0.5.RELEASE
2018/04/29 18:23:28:618 BST [INFO] MyApp - The following profiles are active: prod
2018/04/29 18:23:28:727 BST [INFO] AnnotationConfigServletWebServerApplicationContext - Refreshing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@7920ba90: startup date [Sun Apr 29 18:23:28 BST 2018]; root of context hierarchy
2018/04/29 18:23:31:287 BST [INFO] TomcatWebServer - Tomcat initialized with port(s): 8090 (http)
<...>
2018/04/29 18:23:32:475 BST [INFO] RequestMappingHandlerMapping - Mapped "{[/echo],methods=[GET]}" onto public com.balionis.spring5.restful.MyMessage com.balionis.spring5.restful.MyController.echo(java.lang.String)
<...>
2018/04/29 18:23:32:902 BST [INFO] TomcatWebServer - Tomcat started on port(s): 8090 (http) with context path ''
2018/04/29 18:23:32:918 BST [INFO] MyApp - Started MyApp in 5.385 seconds (JVM running for 6.106)
> Building 75% > :run
```

## Test

* Open http://localhost:8090/echo?message=aMessage

_Note: If all goes well then the browser should look like this_
```
{"id":2,"content":"aService@prod:aMessage"}
```

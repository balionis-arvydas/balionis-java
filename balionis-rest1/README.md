# Why?

This package is meant to provide me with the prototype of build, test, package and dist the following stack:
1) jersey based restapi
2) packaged with gradle
3) tested with junit
4) runnable as standalone jetty.

# How?

## Setup

see balionis-java1-gradle 

## Compile

```
gradle clean build --info
```

_Note: the artifact should be produced at balionis-rest1\build\libs\balionis-rest1-1.0-SNAPSHOT.jar

## Run

1. Start 'server'
```
gradle run -Dexec.args="8080"
```

2. Open 'chrome' on the url

```
http://localhost:8080/rest/my-service/echo?msg=hello
```

_Note: If all goes well then the command line output should look like this_

1. On 'server' side:
```
> Task :run
2019/04/03 23:05:09:689 BST [DEBUG] MyApp - main: port=8080
2019-04-03 23:05:09.846:INFO::main: Logging initialized @419ms to org.eclipse.jetty.util.log.StdErrLog
2019-04-03 23:05:10.031:INFO:oejs.Server:main: jetty-9.4.15.v20190215; built: 2019-02-15T16:53:49.381Z; git: eb70b240169fcf1abbd86af36482d1c49826fa0b; jvm 1.8.0_161-b12
...
2019-04-03 23:05:11.501:INFO:oejs.AbstractConnector:main: Started ServerConnector@20d7d6fb{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
2019-04-03 23:05:11.520:INFO:oejs.Server:main: Started @2092ms
2019/04/03 23:05:56:963 BST [INFO] MyService - echo: msg=hello
2019/04/03 23:05:56:964 BST [INFO] MyService - echo: res={ status=0, payload=hello, reason=null}
<=========----> 75% EXECUTING [3m 30s]
> :run
```

2. On 'browser' side:
```
{"status":0,"reason":null,"payload":"hello"}
```
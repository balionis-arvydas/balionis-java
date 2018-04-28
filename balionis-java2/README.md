# Why?

Remind myself how to write my own Java8 functions.

# How?

## Setup

Text.

## Compile

```
gradle clean build
```

_Note: Text_

## Publish

```
gradle install
```

_Note: Text_

## Run

```
gradle run -Dmyvariable=myvalue -Dexec.args="myarg1 myarg2 false"
```

_Note: If all goes well then the command line output should look like this_
```
>gradle run -Dmyvariable=myvalue -Dexec.args="myarg1 myarg2 false"
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run
2018/04/28 10:00:58:865 BST [DEBUG] MyApp - echo: name=myarg1, useStatic=false, msg=myarg2
myarg1:myarg2:fun1

BUILD SUCCESSFUL

Total time: 1.894 secs
```

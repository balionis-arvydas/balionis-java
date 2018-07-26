# Why?

The prototype for java app run by python launcher with builds pushed into docker hub.

# How?

## Require

_Note: tested on CentOS 7 only._
- docker
- maven 3

```
$ vi ~/.m2/settings.xml
<settings ...>
  <servers>
      <id>docker.io</id>
      <username>baliarvy</username>
      <password>********</password>
    </server>
  </servers>
</settings>
```

## Compile
 
```
mvn clean package -Pdocker
```

### Verify:
```
$ docker images
REPOSITORY                  TAG                 IMAGE ID            CREATED             SIZE
baliarvy/balionis-docker2   1.0-SNAPSHOT        31a96ad66c60        8 seconds ago       120MB
```

## Publish

```
mvn clean install -Pdocker
```

### Verify:
https://hub.docker.com/r/baliarvy/balionis-docker2/tags/

## Run

docker run --volume /usr/local/jdk1.8.0:/usr/local/jdk1.8.0 baliarvy/balionis-docker2:1.0-SNAPSHOT


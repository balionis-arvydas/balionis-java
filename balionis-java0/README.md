# Build
```
gradle clean jar test tar
```

## docker
### docker build
```
docker build -t balionis-java0 .
docker run -v /usr/local/jdk1.8.0:/usr/local/jdk1.8.0 balionis-java0
```

### docker push
```
docker login
docker tag balionis-java0 baliarvy/balionis-java0:1.0-SNAPSHOT
docker image ls
docker push baliarvy/balionis-java0:1.0-SNAPSHOT
docker tag baliarvy/balionis-java0:1.0-SNAPSHOT baliarvy/balionis-java0:latest
docker push baliarvy/balionis-java0:latest
```

### docker run
```
docker run --volume /usr/local/jdk1.8.0:/usr/local/jdk1.8.0 baliarvy/balionis-java0:1.0-SNAPSHOT
```

### docker clean
```
docker container ls
docker container prune
docker images
docker rmi -f <image-id>
```

```
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker volume rm -f java-volume
```

### docker stack
```
docker swarm init
docker swarm join --token SWMTKN-1-1fcbgtqp7wmtb7frl7a45kkbnt2obtr41bxdyplf6avetw9dc1-chzks0n3nl3sbfbh3q6qsd5c1 192.168.2.14:2377
docker swarm join-token manager
docker stack deploy -c docker-compose.yml balionis-java0-stack
docker service ls
docker service ps balionis-java0-stack_myapp
docker stack rm balionis-java0-stack
docker swarm leave --force
```

or, if images are on a private registry
```
docker login registry.balionis.com
docker stack deploy --with-registry-auth -c docker-compose.yml balionis-java0-stack
```

### docker swarm

> for AWS (non VM based) see https://docs.docker.com/swarm/install-manual/#step-4-set-up-a-discovery-backend

## docker
### docker build
```
docker build -t balionis-java0 .
docker run -v /usr/local/jdk1.8.0:/usr/local/jdk1.8.0 balionis-java0
```
_Note: to fix dial unix /var/run/docker.sock: connect: permission denied_
```
usermod -aG docker arvydas
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

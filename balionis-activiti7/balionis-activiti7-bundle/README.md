#  balionis-activiti7-bundle  

### Prerequisite:
```
$ vi .env
DOCKER_IP=192.168.3.123
```

## Build

```
$ docker build --tag nginx:latest .
```

## Test

### Modeller 
To access modeler please use url
```
http://$DOCKER_IP/modeling
```
You will be redirected to keycloak where you have to use credentials *modeler/password* 

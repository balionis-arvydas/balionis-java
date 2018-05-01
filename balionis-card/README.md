# Why?

### Task:

Implement a very basic virtual cash card in Java.
 
### Requirements:
1. Can withdraw money if a valid pin is supplied. The balance on the card needs to adjust accordingly.
2. Can be topped up any time by an arbitrary amount.
3. The cash card, being virtual, can be used in many places at the same time.
 
### Principles:
1.  Well tested code (test driven would be best)
2.  Write the code as you would write a part of a production grade system
3.  Requirements must be met but please don’t go overboard

# How?

## Assumptions

1. You have gradle 3.2.1 installed.
2. You have java 8 installed.
3. (optional) Your maven repository is LOCAL_MAVEN_REPOSITORY=.\repository if you want to "install" artifact.

## Compile

```
gradle clean test jar
```

_Note: the artifact should be produced at balionis-card\build\libs\balionis-card-1.0-SNAPSHOT.jar_

## Run

```diff
+ THIS IS NOT FOR PRODUCTION ENVIRONMENT. TODO: write launcher script.
```

```
gradle run -Dprofile=prod -Dexec.args="--server.port=8090"
```

_Note: If all goes well then the command line output should look like this_
```
C:\com.balionis\dev\balionis-java\balionis-card>gradle run -Dprofile=prod -Dexec.args="--server.port=8090"
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run
19:27:47.819 TKD [main] DEBUG com.balionis.card.CardApp - main: args=[--server.port=8090]
<...>
19:27:48.900 TKD [main] DEBUG com.balionis.card.CardApp - Running with Spring Boot v2.0.1.RELEASE, Spring v5.0.5.RELEASE
19:27:48.900 TKD [main] INFO  com.balionis.card.CardApp - The following profiles are active: prod
19:27:52.437 TKD [main] DEBUG c.b.c.r.CardRepositoryConfig - init: cardNames=[card1, card2], cardBalances=[1000.0, 100.0]
19:27:52.439 TKD [main] DEBUG c.b.card.security.CardSecurityConfig - init: cardNames=[card1, card2], cardPins.size=2
19:27:52.673 TKD [main] DEBUG c.b.c.s.CardSecurityConfigurer - configureGlobal: cards=[card1, card2]
19:27:54.297 TKD [main] INFO  com.balionis.card.CardApp - Started CardApp in 6.134 seconds (JVM running for 7.166)
> Building 75% > :run
<...>
```

## Test 1 (card1)

* Open http://localhost:8090/withdraw?amount=100
* Enter username: card1, password: 1234
![alt text](docs/images/1_1.png "Test 1 (card1) Step 1")

_Note: If all goes well then the browser should look like this_
```
{"cardName":"card1","balance":900.0,"status":"SUCCESS","reason":null}
```
![alt text](docs/images/1_2.png "Test 1 (card1) Step 2")


## Test 2 (card1, no funds)

* Open http://localhost:8090/withdraw?amount=10000
* Enter username: card1, password: 1234

_Note: If all goes well then the browser should look like this_
```
{"cardName":"card1","balance":1000.0,"status":"FAILURE","reason":"not enough funds"}
```
![alt text](docs/images/2_2.png "Test 2 (card1, no funds) Step 2")

## Test 3 (card1, topup)

* Open http://localhost:8090/topup?amount=100
* Enter username: card1, password: 1234

_Note: If all goes well then the browser should look like this_
```
{"cardName":"card1","balance":1100.0,"status":"SUCCESS","reason":null}
```
![alt text](docs/images/3_2.png "Test 3 (card1, topup) Step 2")

## Test 4 (card1, bogus pin)

* Open http://localhost:8090/withdraw?amount=100
* Enter username: card1, password: 2345 ('Sign in' screen should show up every time you click "Sign in" button)
* Click "Cancel"
![alt text](docs/images/4_1.png "Test 4 (card1, badpin) Step 1")

_Note: If all goes well then the browser should look like this_
```
HTTP ERROR 401 (Unauthorized)
```
![alt text](docs/images/4_2.png "Test 4 (card1, badpin) Step 2")

## Test 5 (badcard)

* Open http://localhost:8090/withdraw?amount=100
* Enter username: boguscard, password: 2345 ('Sign in' screen should show up every time you click "Sign in" button)
* Click "Cancel"
![alt text](docs/images/5_1.png "Test 5 (badcard) Step 1")

_Note: If all goes well then the browser should look like this_
```
HTTP ERROR 401 (Unauthorized)
```
![alt text](docs/images/5_2.png "Test 5 (badcard) Step 2")

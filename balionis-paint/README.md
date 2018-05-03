# Why?

### Task:

Implement a very basic virtual cash card in Java.
 
### Requirement:
Please implement a simple text (ASCII) based drawing program (something similar to a simplified version of Paint). The basic program should allow users to:
1. Create a new canvas;
2. Draw on the canvas using text based commands;
3. Quit the program

Your solution should run 'out of the box' and interactively (i.e. it should respond to commands entered manually, see below). If you want to share comments or instructions, please include them in a README.
 
### Commands:
Example | Description
------- | -----------
C w h | Create a new canvas of width w and height.
L x1 y1 x2 y2 | Draw a new line from coordinates (x1, y1) to (x2, y2) horizontally or vertically. Lines are made up of the x character.
R x1 y1 x2 y2 | Draw a new rectangle, with upper left corner at coordinate (x1, y1) and lower right coordinate at (x2, y2). Lines are made up of the x character.
Q | Quit the program

# How?

## Assumptions

1. You have gradle 4.4.1 installed.
2. You have java 8 installed.
3. (optional) Your maven repository is LOCAL_MAVEN_REPOSITORY=.\repository if you want to "install" artifact.

## Compile

```
gradle --console=plain clean test bootJar
```

_Note: the artifact should be produced at balionis-paint\build\libs\balionis-paint-1.0-SNAPSHOT.jar_

## Run

```
RUNME.bat
```

_Note: If all goes well then the command line output should look like this_
```
>RUNME.bat

enter command:C 20 5

----------------------
|                    |
|                    |
|                    |
|                    |
|                    |
----------------------

enter command:L 1 3 7 3

----------------------
|                    |
|                    |
|xxxxxxx             |
|                    |
|                    |
----------------------

enter command:L 7 1 7 3

----------------------
|      x             |
|      x             |
|xxxxxxx             |
|                    |
|                    |
----------------------

enter command:R 15 2 20 5

----------------------
|      x             |
|      x       xxxxxx|
|xxxxxxx       x    x|
|              x    x|
|              xxxxxx|
----------------------

enter command:Q

>
```

# Why?
It's a 'GS' java algos project using gradle.

1) MyApp1 - what is the least number of ways to get from number n to 1, using operations (divide by) /2, /3, and -1.
2) MyApp2 - invert a string e.g. "cat".
3) MyApp3 - detect a cycle in a linked list.
4) MyApp4 - compute snow between hills.
5) MyApp5 - for n random positive and negative numbers in an array,
            how does one pick a set of numbers that give the largest sum,
            subject to the following constraints: cannot pick numbers which are adjacent in position in the array.
6) MyApp6 - use operators +, - , *, /, and || to get the maximum and minimum of two numbers.
7) MyApp7 - design an algorithm to check if a graph is hamiltonian or not. 
8) MyApp8 - to find the pieces of land (aka islands).
9) MyApp9 - count in how many ways I can add English coins to the desired amount.
10) MyApp10 - depth-first search algorithm to get all leaves


# Build
```
gradle clean test jar
```

# Run
```
gradle run -Dmyvariable=myvalue -Dexec.args="myarg1 myarg2"
```

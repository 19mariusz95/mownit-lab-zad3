# mownit-lab-zad3

# Prepare a file, defining circuit
* In the next lines define a graph using 3 numbers a b c | a,b - numbers of vertices c - resistance
* After a blank line, enter u v e | u,v - numbers of vertices e - SEM
* example file:

```
  1 2 2 
  2 3 4 
  3 4 4 
  4 5 6 
  5 6 2 
  6 7 4 
  7 8 2 
  1 8 4 

  1 2 10
```

# Run
1) mvn package

2) java -jar target/mownit.jar filename

Item 2:With Combiner
====================
### The execution time for 1990.txt is:
 ![image](https://github.com/sliu102/ITMD521/blob/master/week07/Item2/90.jpeg)
### The execution time for 90and92.txt is:
![image](https://github.com/sliu102/ITMD521/blob/master/week07/Item2/9092.jpeg)
### The execution time for 90-93.txt is:
![image](https://github.com/sliu102/ITMD521/blob/master/week07/Item2/90-93.jpeg)

### 
###The amount of resources currently in use is: 82GB

###From the results, we can tell execution time decreased. This is because using MaxTemperatureWithCombiner code will call the combiner function. In combiner phase, outputs from map phase are sorted several rounds in local disk which allows less data write to local disk and transfer to the reducer.
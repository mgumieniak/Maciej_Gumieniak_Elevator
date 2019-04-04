# Maciej_Gumieniak_Elevator

This source code is available for download at https://github.com/mgumieniak/Maciej_Gumieniak_Elevator.
Please, downland program and run Main class or Tests.

# Solution explanation 

I strongly belive in Divide and Conquer algorithm, one of the basic approach to solve problems using recursion. 
The mainly idea behind this algo is divide problem into smaller problems to solve bigger ones. I divided problem into:
* Created Elevator class, which simulate elevator behaviour. It's undoubtedly important to focuse only on elevator and don't 
think about service. To make sure that I correctly finished this task I created unit test to deeply test this class.
* Created data base, which store data about elevator instances with some basic method like findById, addElement or showAllData. In this step, 
I considered only data base and I didn't think about service. Like in Elevator class, created test ensured me that I correctly programmed class.
* The finally step was created Service class. 

Below I'm showing my approach to create each class.

# Elevator Service class

I established some purposes:
* The service will suport multi thread operating. To achive this I used ExecutorService instance which enable create and handle life cycle threads.
* Multi-process synchronization (producer and consumer) problem I solved using BlockingQueue:
**Producer** - put data (Message instance) in queue, simulate pick up elevator or select floor inside elevator.
**Consumer** - take data (Message instance) from queue and **update** Elevator instance in data base. 
* Create Message class - immutable class which contain all information to create Elevator instance.
* Above I've mentioned about update Elevator objects in data base. The most reasonable solution to have concurrent, safety and fast access to objects in data base is ConcurrentHashMap. Moreover all the changes in data base required using *compute()* method. That's way it was so important to programing Elevator class as immutable (each changes return new class instance).

Established purposes and remebered about divided problem into smaller problems I created: Producer, Consumer and ElevatorService class.

# Task ordering

Tasks are represented as Message objects in BlockingQueue. Creating ElevatorSystem object, the third parameter is the object class that implements BlockingQueue interface.

Example:
* *new ElevatorSystem(...,..., new PriorityBlockingQueue<>(20, (first,second)->second.getIdElevator()-first.getIdElevator()));*

Above I used PriorityBlockingQueue class. One of this class constructor as second parameter takes comparator that will be used to order 
priority queue. Therefore class gives discretion to determine order elements in queue. 

More Examples:
* *new ElevatorSystem(...,..., new PriorityBlockingQueue<>(20));* // The head of the queue is that element that has to lower id. 
* *new ElevatorSystem(...,..., new PriorityBlockingQueue<>(20, (first,second)-> Math.abs(data.findObjectById(first.getIdElevator()).getCurrentFloor()data.findObjectById(first.getIdElevator()).getDestinationFloor()) -Math.abs(data.findObjectById(second.getIdElevator()).getCurrentFloor()data.findObjectById(second.getIdElevator()).getDestinationFloor())));*  // The head of the queue is that element that has the biggest way. Reverse the order subtraction cause that the head of the queue is that element that has the lowest way

During testing task ordering in Main class, please don't pay attention on console results. The output is printed by consumer (running threads) and this task isn't connected with which element is on the head. Instead try run messagesInTheQueueWillBeProcessedInOrderSpecifyInComparatorInterface() test in ElevatorSystemTest.

# Other information
* Access to data base using DAO pattern and using interface ElevatoInterface, ElevatorInterfaceConsumer - flexible during future changes.
* Each method is described in src folder.
* Each class was tested (unit test).

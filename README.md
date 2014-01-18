To compile 
----------
*(remember to update it if you add more classes)*

	javac -d . Master/Master.java Slave/Slave.java Slave/Task.java Master/MakefileStruct.java


To execute
-----------

####Start Java RMI registry (you don't need to restart it after recompiling!)

	start rmiregistry

####Start the Slave:

	java Slave

####Start the Master:

	java Master
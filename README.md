To compile 
----------
*(remember to update it if you add more classes)*

This will save all .class files in the root folder:

	javac -d . Master/Master.java Slave/Slave.java Slave/Task.java Master/MakefileStruct.java
	
Remove the "-d . " option if you want to the .class files to be saved in the respective folders.


To execute
-----------

####Start Java RMI registry (you don't need to restart it after recompiling!)

	start rmiregistry

####Start the Slave:

	java Slave

####Start the Master:

	java Master

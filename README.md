To compile 
----------
This will save all .class files in the root folder:

	javac -d . `cat src_names`
	
*Remove the "-d . " option if you want to the .class files to be saved in the respective folders.*


To execute
-----------

####Start Java RMI registry (you don't need to restart it after recompiling!)

	rmiregistry&

####Start the Slave:

	java Slave <slave_id> [<registry_host_addres>]

####Start the Master:

	java Master <number_of_slaves> [<registry_host_addres>]

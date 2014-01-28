To compile 
----------

	make
	
*Make sure to update the file src_names if you add a new Class*


To execute
-----------

####Start Java RMI registry (you don't need to restart it after recompiling!)

	rmiregistry&

####Start the Slave:

	java Slave <slave_id> [<registry_host_addres>]

####Start the Master:

	java Master <makefile_path> <number_of_slaves> [<registry_host_addres>]

To compile 
----------

	make


To execute
-----------

####1. Start Java RMI registry

	rmiregistry&

*You don't need to restart it after recompiling*

####2. Start the Slave(s):

	cd Slave
	java Slave <slave_id> [<registry_host_addres>]

####3. Start the Master:

	cd Master
	java Master <makefile_path> [<rule_name>] < hosts

For each Slave you'll be using, insert it's address in a line in the *hosts* file.
If you are running 3 Slaves locally, your *hosts* file should look like this:

	localhost
	localhost
	localhost

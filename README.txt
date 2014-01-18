To compile:

	javac -d <destDir> Slave.java Master.java

Start Java RMI registry:

	start rmiregistry <PortNumber>

Start the Slave:

	start java -classpath <classDir> -Djava.rmi.slave.codebase=file:<classDir>/ Slave

Start the Master:

	java  -classpath <classDir> Master
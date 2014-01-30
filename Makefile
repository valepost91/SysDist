SRC_FILES = Master/Master.java Slave/Slave.java Slave/SlaveStub.java Master/MakefileStruct.java Master/Rule.java Master/RuleRunner.java Master/Machine.java Master/MachinesList.java

all:
	javac $(SRC_FILES)
	cp Slave/SlaveStub.class Master/SlaveStub.class

clean:
	rm Master/*.class -f
	rm Slave/*.class -f
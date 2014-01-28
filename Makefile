all:
	javac -d . `cat src_names`

clean:
	rm *.class
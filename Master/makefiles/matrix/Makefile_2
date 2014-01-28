all: c

check:	a b
	 perl multiply check a b
c:	c-1-1 c-1-2 c-2-1 c-2-2
	perl fuse c 2 2 c-1-1 c-1-2 c-2-1 c-2-2

#
c-1-1:	p-1-1-1-1 p-1-2-2-1
	perl sum c-1-1 p-1-1-1-1 p-1-2-2-1
c-1-2:	p-1-1-1-2 p-1-2-2-2
	perl sum c-1-2 p-1-1-1-2 p-1-2-2-2
c-2-1:	p-2-1-1-1 p-2-2-2-1
	perl sum c-2-1 p-2-1-1-1 p-2-2-2-1
c-2-2:	p-2-1-1-2 p-2-2-2-2
	perl sum c-2-2 p-2-1-1-2 p-2-2-2-2

#
p-1-1-1-1:	a-1-1 b-1-1
	perl multiply p-1-1-1-1 a-1-1 b-1-1
p-1-2-2-1:	a-1-2 b-2-1
	perl multiply p-1-2-2-1 a-1-2 b-2-1
p-1-1-1-2:	a-1-1 b-1-2
	perl multiply p-1-1-1-2 a-1-1 b-1-2
p-1-2-2-2:	a-1-2 b-2-2
	perl multiply p-1-2-2-2 a-1-2 b-2-2
p-2-1-1-1:	a-2-1 b-1-1
	perl multiply p-2-1-1-1 a-2-1 b-1-1
p-2-2-2-1:	a-2-2 b-2-1
	perl multiply p-2-2-2-1 a-2-2 b-2-1
p-2-1-1-2:	a-2-1 b-1-2
	perl multiply p-2-1-1-2 a-2-1 b-1-2
p-2-2-2-2:	a-2-2 b-2-2
	perl multiply p-2-2-2-2 a-2-2 b-2-2

#
a-1-1:	a
	perl split a-1-1 a 2 2 1 1
a-1-2:	a
	perl split a-1-2 a 2 2 1 2
a-2-1:	a
	perl split a-2-1 a 2 2 2 1
a-2-2:	a
	perl split a-2-2 a 2 2 2 2

#
b-1-1:	b
	perl split b-1-1 b 2 2 1 1
b-1-2:	b
	perl split b-1-2 b 2 2 1 2
b-2-1:	b
	perl split b-2-1 b 2 2 2 1
b-2-2:	b
	perl split b-2-2 b 2 2 2 2
clean:
	rm -f a-1-1 a-1-2 a-2-1 a-2-2 b-1-1 b-1-2 b-2-1 b-2-2 p-1-1-1-1 p-1-2-2-1 p-1-1-1-2 p-1-2-2-2 p-2-1-1-1 p-2-2-2-1 p-2-1-1-2 p-2-2-2-2 c-1-1 c-1-2 c-2-1 c-2-2 c check

all: c

check:	a b
	perl multiply check a b
c:	c-1-1 c-1-2 c-1-3 c-1-4 c-2-1 c-2-2 c-2-3 c-2-4 c-3-1 c-3-2 c-3-3 c-3-4 c-4-1 c-4-2 c-4-3 c-4-4
	perl fuse c 4 4 c-1-1 c-1-2 c-1-3 c-1-4 c-2-1 c-2-2 c-2-3 c-2-4 c-3-1 c-3-2 c-3-3 c-3-4 c-4-1 c-4-2 c-4-3 c-4-4

#
c-1-1:	p-1-1-1-1 p-1-2-2-1 p-1-3-3-1 p-1-4-4-1
	perl sum c-1-1 p-1-1-1-1 p-1-2-2-1 p-1-3-3-1 p-1-4-4-1
c-1-2:	p-1-1-1-2 p-1-2-2-2 p-1-3-3-2 p-1-4-4-2
	perl sum c-1-2 p-1-1-1-2 p-1-2-2-2 p-1-3-3-2 p-1-4-4-2
c-1-3:	p-1-1-1-3 p-1-2-2-3 p-1-3-3-3 p-1-4-4-3
	perl sum c-1-3 p-1-1-1-3 p-1-2-2-3 p-1-3-3-3 p-1-4-4-3
c-1-4:	p-1-1-1-4 p-1-2-2-4 p-1-3-3-4 p-1-4-4-4
	perl sum c-1-4 p-1-1-1-4 p-1-2-2-4 p-1-3-3-4 p-1-4-4-4
c-2-1:	p-2-1-1-1 p-2-2-2-1 p-2-3-3-1 p-2-4-4-1
	perl sum c-2-1 p-2-1-1-1 p-2-2-2-1 p-2-3-3-1 p-2-4-4-1
c-2-2:	p-2-1-1-2 p-2-2-2-2 p-2-3-3-2 p-2-4-4-2
	perl sum c-2-2 p-2-1-1-2 p-2-2-2-2 p-2-3-3-2 p-2-4-4-2
c-2-3:	p-2-1-1-3 p-2-2-2-3 p-2-3-3-3 p-2-4-4-3
	perl sum c-2-3 p-2-1-1-3 p-2-2-2-3 p-2-3-3-3 p-2-4-4-3
c-2-4:	p-2-1-1-4 p-2-2-2-4 p-2-3-3-4 p-2-4-4-4
	perl sum c-2-4 p-2-1-1-4 p-2-2-2-4 p-2-3-3-4 p-2-4-4-4
c-3-1:	p-3-1-1-1 p-3-2-2-1 p-3-3-3-1 p-3-4-4-1
	perl sum c-3-1 p-3-1-1-1 p-3-2-2-1 p-3-3-3-1 p-3-4-4-1
c-3-2:	p-3-1-1-2 p-3-2-2-2 p-3-3-3-2 p-3-4-4-2
	perl sum c-3-2 p-3-1-1-2 p-3-2-2-2 p-3-3-3-2 p-3-4-4-2
c-3-3:	p-3-1-1-3 p-3-2-2-3 p-3-3-3-3 p-3-4-4-3
	perl sum c-3-3 p-3-1-1-3 p-3-2-2-3 p-3-3-3-3 p-3-4-4-3
c-3-4:	p-3-1-1-4 p-3-2-2-4 p-3-3-3-4 p-3-4-4-4
	perl sum c-3-4 p-3-1-1-4 p-3-2-2-4 p-3-3-3-4 p-3-4-4-4
c-4-1:	p-4-1-1-1 p-4-2-2-1 p-4-3-3-1 p-4-4-4-1
	perl sum c-4-1 p-4-1-1-1 p-4-2-2-1 p-4-3-3-1 p-4-4-4-1
c-4-2:	p-4-1-1-2 p-4-2-2-2 p-4-3-3-2 p-4-4-4-2
	perl sum c-4-2 p-4-1-1-2 p-4-2-2-2 p-4-3-3-2 p-4-4-4-2
c-4-3:	p-4-1-1-3 p-4-2-2-3 p-4-3-3-3 p-4-4-4-3
	perl sum c-4-3 p-4-1-1-3 p-4-2-2-3 p-4-3-3-3 p-4-4-4-3
c-4-4:	p-4-1-1-4 p-4-2-2-4 p-4-3-3-4 p-4-4-4-4
	perl sum c-4-4 p-4-1-1-4 p-4-2-2-4 p-4-3-3-4 p-4-4-4-4

#
p-1-1-1-1:	a-1-1 b-1-1
	perl multiply p-1-1-1-1 a-1-1 b-1-1
p-1-2-2-1:	a-1-2 b-2-1
	perl multiply p-1-2-2-1 a-1-2 b-2-1
p-1-3-3-1:	a-1-3 b-3-1
	perl multiply p-1-3-3-1 a-1-3 b-3-1
p-1-4-4-1:	a-1-4 b-4-1
	perl multiply p-1-4-4-1 a-1-4 b-4-1
p-1-1-1-2:	a-1-1 b-1-2
	perl multiply p-1-1-1-2 a-1-1 b-1-2
p-1-2-2-2:	a-1-2 b-2-2
	perl multiply p-1-2-2-2 a-1-2 b-2-2
p-1-3-3-2:	a-1-3 b-3-2
	perl multiply p-1-3-3-2 a-1-3 b-3-2
p-1-4-4-2:	a-1-4 b-4-2
	perl multiply p-1-4-4-2 a-1-4 b-4-2
p-1-1-1-3:	a-1-1 b-1-3
	perl multiply p-1-1-1-3 a-1-1 b-1-3
p-1-2-2-3:	a-1-2 b-2-3
	perl multiply p-1-2-2-3 a-1-2 b-2-3
p-1-3-3-3:	a-1-3 b-3-3
	perl multiply p-1-3-3-3 a-1-3 b-3-3
p-1-4-4-3:	a-1-4 b-4-3
	perl multiply p-1-4-4-3 a-1-4 b-4-3
p-1-1-1-4:	a-1-1 b-1-4
	perl multiply p-1-1-1-4 a-1-1 b-1-4
p-1-2-2-4:	a-1-2 b-2-4
	perl multiply p-1-2-2-4 a-1-2 b-2-4
p-1-3-3-4:	a-1-3 b-3-4
	perl multiply p-1-3-3-4 a-1-3 b-3-4
p-1-4-4-4:	a-1-4 b-4-4
	perl multiply p-1-4-4-4 a-1-4 b-4-4
p-2-1-1-1:	a-2-1 b-1-1
	perl multiply p-2-1-1-1 a-2-1 b-1-1
p-2-2-2-1:	a-2-2 b-2-1
	perl multiply p-2-2-2-1 a-2-2 b-2-1
p-2-3-3-1:	a-2-3 b-3-1
	perl multiply p-2-3-3-1 a-2-3 b-3-1
p-2-4-4-1:	a-2-4 b-4-1
	perl multiply p-2-4-4-1 a-2-4 b-4-1
p-2-1-1-2:	a-2-1 b-1-2
	perl multiply p-2-1-1-2 a-2-1 b-1-2
p-2-2-2-2:	a-2-2 b-2-2
	perl multiply p-2-2-2-2 a-2-2 b-2-2
p-2-3-3-2:	a-2-3 b-3-2
	perl multiply p-2-3-3-2 a-2-3 b-3-2
p-2-4-4-2:	a-2-4 b-4-2
	perl multiply p-2-4-4-2 a-2-4 b-4-2
p-2-1-1-3:	a-2-1 b-1-3
	perl multiply p-2-1-1-3 a-2-1 b-1-3
p-2-2-2-3:	a-2-2 b-2-3
	perl multiply p-2-2-2-3 a-2-2 b-2-3
p-2-3-3-3:	a-2-3 b-3-3
	perl multiply p-2-3-3-3 a-2-3 b-3-3
p-2-4-4-3:	a-2-4 b-4-3
	perl multiply p-2-4-4-3 a-2-4 b-4-3
p-2-1-1-4:	a-2-1 b-1-4
	perl multiply p-2-1-1-4 a-2-1 b-1-4
p-2-2-2-4:	a-2-2 b-2-4
	perl multiply p-2-2-2-4 a-2-2 b-2-4
p-2-3-3-4:	a-2-3 b-3-4
	perl multiply p-2-3-3-4 a-2-3 b-3-4
p-2-4-4-4:	a-2-4 b-4-4
	perl multiply p-2-4-4-4 a-2-4 b-4-4
p-3-1-1-1:	a-3-1 b-1-1
	perl multiply p-3-1-1-1 a-3-1 b-1-1
p-3-2-2-1:	a-3-2 b-2-1
	perl multiply p-3-2-2-1 a-3-2 b-2-1
p-3-3-3-1:	a-3-3 b-3-1
	perl multiply p-3-3-3-1 a-3-3 b-3-1
p-3-4-4-1:	a-3-4 b-4-1
	perl multiply p-3-4-4-1 a-3-4 b-4-1
p-3-1-1-2:	a-3-1 b-1-2
	perl multiply p-3-1-1-2 a-3-1 b-1-2
p-3-2-2-2:	a-3-2 b-2-2
	perl multiply p-3-2-2-2 a-3-2 b-2-2
p-3-3-3-2:	a-3-3 b-3-2
	perl multiply p-3-3-3-2 a-3-3 b-3-2
p-3-4-4-2:	a-3-4 b-4-2
	perl multiply p-3-4-4-2 a-3-4 b-4-2
p-3-1-1-3:	a-3-1 b-1-3
	perl multiply p-3-1-1-3 a-3-1 b-1-3
p-3-2-2-3:	a-3-2 b-2-3
	perl multiply p-3-2-2-3 a-3-2 b-2-3
p-3-3-3-3:	a-3-3 b-3-3
	perl multiply p-3-3-3-3 a-3-3 b-3-3
p-3-4-4-3:	a-3-4 b-4-3
	perl multiply p-3-4-4-3 a-3-4 b-4-3
p-3-1-1-4:	a-3-1 b-1-4
	perl multiply p-3-1-1-4 a-3-1 b-1-4
p-3-2-2-4:	a-3-2 b-2-4
	perl multiply p-3-2-2-4 a-3-2 b-2-4
p-3-3-3-4:	a-3-3 b-3-4
	perl multiply p-3-3-3-4 a-3-3 b-3-4
p-3-4-4-4:	a-3-4 b-4-4
	perl multiply p-3-4-4-4 a-3-4 b-4-4
p-4-1-1-1:	a-4-1 b-1-1
	perl multiply p-4-1-1-1 a-4-1 b-1-1
p-4-2-2-1:	a-4-2 b-2-1
	perl multiply p-4-2-2-1 a-4-2 b-2-1
p-4-3-3-1:	a-4-3 b-3-1
	perl multiply p-4-3-3-1 a-4-3 b-3-1
p-4-4-4-1:	a-4-4 b-4-1
	perl multiply p-4-4-4-1 a-4-4 b-4-1
p-4-1-1-2:	a-4-1 b-1-2
	perl multiply p-4-1-1-2 a-4-1 b-1-2
p-4-2-2-2:	a-4-2 b-2-2
	perl multiply p-4-2-2-2 a-4-2 b-2-2
p-4-3-3-2:	a-4-3 b-3-2
	perl multiply p-4-3-3-2 a-4-3 b-3-2
p-4-4-4-2:	a-4-4 b-4-2
	perl multiply p-4-4-4-2 a-4-4 b-4-2
p-4-1-1-3:	a-4-1 b-1-3
	perl multiply p-4-1-1-3 a-4-1 b-1-3
p-4-2-2-3:	a-4-2 b-2-3
	perl multiply p-4-2-2-3 a-4-2 b-2-3
p-4-3-3-3:	a-4-3 b-3-3
	perl multiply p-4-3-3-3 a-4-3 b-3-3
p-4-4-4-3:	a-4-4 b-4-3
	perl multiply p-4-4-4-3 a-4-4 b-4-3
p-4-1-1-4:	a-4-1 b-1-4
	perl multiply p-4-1-1-4 a-4-1 b-1-4
p-4-2-2-4:	a-4-2 b-2-4
	perl multiply p-4-2-2-4 a-4-2 b-2-4
p-4-3-3-4:	a-4-3 b-3-4
	perl multiply p-4-3-3-4 a-4-3 b-3-4
p-4-4-4-4:	a-4-4 b-4-4
	perl multiply p-4-4-4-4 a-4-4 b-4-4

#
a-1-1:	a
	perl split a-1-1 a 4 4 1 1
a-1-2:	a
	perl split a-1-2 a 4 4 1 2
a-1-3:	a
	perl split a-1-3 a 4 4 1 3
a-1-4:	a
	perl split a-1-4 a 4 4 1 4
a-2-1:	a
	perl split a-2-1 a 4 4 2 1
a-2-2:	a
	perl split a-2-2 a 4 4 2 2
a-2-3:	a
	perl split a-2-3 a 4 4 2 3
a-2-4:	a
	perl split a-2-4 a 4 4 2 4
a-3-1:	a
	perl split a-3-1 a 4 4 3 1
a-3-2:	a
	perl split a-3-2 a 4 4 3 2
a-3-3:	a
	perl split a-3-3 a 4 4 3 3
a-3-4:	a
	perl split a-3-4 a 4 4 3 4
a-4-1:	a
	perl split a-4-1 a 4 4 4 1
a-4-2:	a
	perl split a-4-2 a 4 4 4 2
a-4-3:	a
	perl split a-4-3 a 4 4 4 3
a-4-4:	a
	perl split a-4-4 a 4 4 4 4

#
b-1-1:	b
	perl split b-1-1 b 4 4 1 1
b-1-2:	b
	perl split b-1-2 b 4 4 1 2
b-1-3:	b
	perl split b-1-3 b 4 4 1 3
b-1-4:	b
	perl split b-1-4 b 4 4 1 4
b-2-1:	b
	perl split b-2-1 b 4 4 2 1
b-2-2:	b
	perl split b-2-2 b 4 4 2 2
b-2-3:	b
	perl split b-2-3 b 4 4 2 3
b-2-4:	b
	perl split b-2-4 b 4 4 2 4
b-3-1:	b
	perl split b-3-1 b 4 4 3 1
b-3-2:	b
	perl split b-3-2 b 4 4 3 2
b-3-3:	b
	perl split b-3-3 b 4 4 3 3
b-3-4:	b
	perl split b-3-4 b 4 4 3 4
b-4-1:	b
	perl split b-4-1 b 4 4 4 1
b-4-2:	b
	perl split b-4-2 b 4 4 4 2
b-4-3:	b
	perl split b-4-3 b 4 4 4 3
b-4-4:	b
	perl split b-4-4 b 4 4 4 4
clean:
	rm -f a-1-1 a-1-2 a-1-3 a-1-4 a-2-1 a-2-2 a-2-3 a-2-4 a-3-1 a-3-2 a-3-3 a-3-4 a-4-1 a-4-2 a-4-3 a-4-4 b-1-1 b-1-2 b-1-3 b-1-4 b-2-1 b-2-2 b-2-3 b-2-4 b-3-1 b-3-2 b-3-3 b-3-4 b-4-1 b-4-2 b-4-3 b-4-4 p-1-1-1-1 p-1-2-2-1 p-1-3-3-1 p-1-4-4-1 p-1-1-1-2 p-1-2-2-2 p-1-3-3-2 p-1-4-4-2 p-1-1-1-3 p-1-2-2-3 p-1-3-3-3 p-1-4-4-3 p-1-1-1-4 p-1-2-2-4 p-1-3-3-4 p-1-4-4-4 p-2-1-1-1 p-2-2-2-1 p-2-3-3-1 p-2-4-4-1 p-2-1-1-2 p-2-2-2-2 p-2-3-3-2 p-2-4-4-2 p-2-1-1-3 p-2-2-2-3 p-2-3-3-3 p-2-4-4-3 p-2-1-1-4 p-2-2-2-4 p-2-3-3-4 p-2-4-4-4 p-3-1-1-1 p-3-2-2-1 p-3-3-3-1 p-3-4-4-1 p-3-1-1-2 p-3-2-2-2 p-3-3-3-2 p-3-4-4-2 p-3-1-1-3 p-3-2-2-3 p-3-3-3-3 p-3-4-4-3 p-3-1-1-4 p-3-2-2-4 p-3-3-3-4 p-3-4-4-4 p-4-1-1-1 p-4-2-2-1 p-4-3-3-1 p-4-4-4-1 p-4-1-1-2 p-4-2-2-2 p-4-3-3-2 p-4-4-4-2 p-4-1-1-3 p-4-2-2-3 p-4-3-3-3 p-4-4-4-3 p-4-1-1-4 p-4-2-2-4 p-4-3-3-4 p-4-4-4-4 c-1-1 c-1-2 c-1-3 c-1-4 c-2-1 c-2-2 c-2-3 c-2-4 c-3-1 c-3-2 c-3-3 c-3-4 c-4-1 c-4-2 c-4-3 c-4-4 c check

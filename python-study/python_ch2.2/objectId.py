import sys

i1 = 10
i2 = 10
print(hex(id(i1)), hex(id(i2)))

i3 = 12345568789
print(hex(id(i3)))

i4 = 11
i5 = i1 + 1
print(hex(id(i4)), hex(id(i5)))

l1 = [10, 11, 12]
print(hex(id(l1[0])), hex(id(l1[1])), hex(id(l1[2])))

s1 = "hello"
s2 = "hello"

print(hex(id(s1)), hex(id(s2)))

t1 = (1, 2, 3)
t2 = (1, 2, 3)
print(t1 is t2)
print(t1 == t2)
print(sys.getrefcount(t1), sys.getrefcount(t2))

t3 = tuple(range(1, 4))
print(t3, type(t3), sys.getrefcount(t3))
print(t1 == t3)
print(t1 is t3)

t4 = (0,1,2,3)[1:]
print (t1 == t4)
print (t4 is t1)
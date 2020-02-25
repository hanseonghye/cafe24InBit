a = 23
print(a, type(a))
print(a.__float__(), type(a.__float__()))
print(isinstance(a, int))

b = 0b1101
c = 0o23
d = 0x23
print(b, c, d, sep=' ')

print(oct(38))
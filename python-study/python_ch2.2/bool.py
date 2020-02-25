a = 1
b = a > 10
print(type(b))

b = a == 1
print(type(b))
print(b.__int__())
print(b * 10)

a = 1
b = a == 1
print(b + 10)

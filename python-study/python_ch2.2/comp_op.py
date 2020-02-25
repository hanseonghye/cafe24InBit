print(1 > 3)
print(3 >= 3)

# 복합 관계형
a = 6
print(0 > a and a < 10)
print(-5 < a < 10)

# 수치형 이외의 객체 비교

print('abcd' > 'abd')
print((1, 2, 4) < (1, 2, 5))
print([1, 2, 4] < [1, 2, 5])
print([1, 2, 4] < list((1, 2, 0)))

# == 와 is
a = 10
b = 10
c = a
l1 = [10, 20]

print(a is l1[0])
print(a == l1[0])
print(id(a), id(b), id(c))
print(a == b)
print(a is b)
print(a == c)
print(a is c)

l1 = [1, 2, 3]
l2 = [1, 2, 3]

print(l1 == l2)
print(l1 is l2)

print('logical' and 'operator')
print('logical' or 'operator')


def f(msg):
    print("f function")
    msg and print(msg)


f("")
f("hello")

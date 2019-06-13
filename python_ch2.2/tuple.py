t = ()  # 빈 튜플
t = (1,)  # (1)과 다르다. 항목이 하나일 때는 반드시 , 를 찍어야 한다.
t = (1, 2, 3)

print(t, type(t))
print(t[0], t[-2])
print(t * 2)
print(t + (4,))
print(len(t))
print(4 not in t)

# tuple은 immutable이다
# t[0] = 10

# 여러개 값 대입
x, y, z = t
print(x, y, z)

x, y = 10, 20
print(type((x, y)))
x, y = y, x
print(x, y)

# 객체함수 : immutable 이기 때문에 많지 않다.
t = (20, 30, 10, 60, 20)
print(t.index(10))
print(t.count(20))

t = (1, 2, 3, 3)
# tuple <--> set
s = set(t)
print(s)
print(tuple(s))

# tuple <--> list
l = list(t)
print(l)
print(tuple(l))

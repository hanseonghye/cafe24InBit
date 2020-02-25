# 치환문 예
a = 1
b = a + 1

print(a, b, sep=' ')

e = 3.5; f = 3.5
print(e, f)

e, f = 1.5, 2.5
print(e, f, sep=' ')

# 같은 값 한번에 대입
x = y = z = 10
print(x, y, z, sep='  ')

# 동적 타이핑
a = 1
print(type(a))
a = "hello"
print(type(a))

# 확장 치환문
a = 10
a += 10
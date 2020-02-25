print(2 * 3)
print(2 - 3)
print(2 % 3)
print(3 // 3)
print(2 / 3)
print(2 / 3.0)
print(2.0 / 3.0)

# 몫, 나머지 동시에 값을 반환
result, last = divmod(2, 3)
print(result, last)
t = divmod(3, 3)
print(t, type(t))

# 연산자 우선 순위
print((2 ** 3) ** 4)
print(2 ** 3 ** 4)
print(2 ** (3 ** 4))

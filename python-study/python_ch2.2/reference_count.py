import sys

x = object()
print(x, type(x))

print(sys.getrefcount(x))

y=x
print(sys.getrefcount(x))

# 레퍼런스 값 줄이기
# del을 사용하자!
print(sys.getrefcount(y))
del x
print(sys.getrefcount(y))
print(globals())
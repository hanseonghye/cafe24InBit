# sys.path.append("path~")
m = __import__('mymath') #  동적 import를 쓸때는 위의 코드와 함께 쓰는 경우가 많다.

print(m.pi)
print(m.add(10,20))

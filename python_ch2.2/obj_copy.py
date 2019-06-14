import copy
# 레퍼런스 복사
a = 1
b = 1

a = [1, 2, 3]
b = [4, 5, 6]
x = [a,b,100]
y = x

y.append([7,8,9])
print (x)


a=["hello","world"]
b=copy.copy(a)
print (a is b)
print (a[0] is b[0])
c=copy.deepcopy(a)
print (a is c)
print (a[0] is c[0])
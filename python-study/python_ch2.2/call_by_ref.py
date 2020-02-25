# call by reference 이지만
# 내부에서 변경하더라도 변경되지 않는다. (immutable)
def f(i) :
    i = 20

# mmutable type의 경우 변경가능.
def f2(seq):
    seq[0]=0

a=10
f(a)
print(a)

a=[1,2,3]
f2(a)
print(a)
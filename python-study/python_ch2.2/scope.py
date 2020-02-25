x = 1 # func함수가 실행되기 전에 선언돼야함.

def func(a):
    return a + x

# 로컬에서 글로벌 선언. 안좋다.
def func3(a):
    global g
    g=3
    return a+g


print(func(1))
print(func3(1),g, sep=',')
print(dir('__builtins__'))
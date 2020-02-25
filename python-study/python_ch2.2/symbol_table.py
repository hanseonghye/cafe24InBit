def f():
    l_a = 2
    l_b = '마이콜'
    print(locals())


class MyClass:
    x = 10
    y = 20


g_a = 1
g_b = '둘리'

# 1. 정의된 함수
f.l_c = 3
print(type(f))
print(f.__dict__)
f()

# 2. 클래스 객체
MyClass.z = 30
print(MyClass.__dict__)

# 2-1. 내장 함수는 symbol table이 없다. --> 확장 x
# print(print.__dict__)

# 2-2. 내장 클래스는 symbol table은 있으나 확장할 수 없다.
# str.z = 30
# print(str.__dict__)

# 2-3. 사용자 정의된 클래스로 생성된 객체
o = MyClass()
o.z = 30
print(o.x)
print(o.__dict__)

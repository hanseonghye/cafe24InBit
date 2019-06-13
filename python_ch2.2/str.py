# 한 줄 문자열 정의
a = ''
str1 = 'hello world'
print(type(a), type(str1))
print(isinstance(str1, str))

# 여러줄 문자열
str2 = '''
        abc
        1234
5678
'''

print(str2)

'''
주석으로도 쓸수 있다.
'''

print("hello\nhello\n\"는 이렇게 출력")

# 문자열 연산
str1 = 'First String'
str2 = 'Second String'

# 1. 인덱싱
print(str1[0], str1[-1])

# 2. slicing str[start:stop:step]
str3 = str2[2:9:2]
print(str3)
print(str2[2:], str2[2:len(str2)])

str4 = "python"
print(str4[-1:])  # 마지막 문자
print(str4[-2:])  # 마지막 두개 문자
print(str4[:-2])  # 마지막 두개 문자 제외한 전체 문자열
print(str4[::2])
print(str4[::-1])  # reverse
print(str4[1::-1])  # 1,2번째 문자
print(str4[1:0])
print(str4[1:0:-1])
print(str4[:-3:-1])

# in, not in 연산
str5 = "ABCDEFG"
print('F' in str5)
print('s' not in str5)

name = "길동"
age = 40
print("name:" + name + ",age:" + format(age, "d"))
print("name:" + format(name, "s") + ",age:" + format(age, "d"))

f = 'name: %s, age: %d'
print(f % (name, age))

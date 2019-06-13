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

# 튜플을 이용한 문자열 포매팅
f = 'name: %s, age: %d'
print(f % (name, age))

# 딕션너리를 이용한 포매팅
f = "name: %(n)s, age:%(a)d"
print(f % {'n': '둘리', 'a': 10})
print(f % {'n': 20, 'a': 10})

# 대소문자 관련
s = "i like Python"
print(s.upper())
print(s.lower())
print(s.swapcase())
print(s.capitalize())
print(s.title())

# 검색
s = 'I Like Python. Like also JAVA'
print(s.count("Like"))
print(s.find("Like"))  # index
print(s.find("Like", 5))
print(s.find("Javascript", 5))
print(s.rfind('Like'))

print(s.index("Like"))
# print(s.index("Javascript")) # 위험~ 없는 경우를 고려하는 try catch가 있어야함.
print(s.rindex("Like"))
print(s.startswith("I Like"))
print(s.startswith("Like", 2))
print(s.endswith("JAVA"))
print(s.endswith("also", 0, 20))
print(s.endswith("also", 0, 24))

# 편집과 치환
s = '    spam and ham  '
print('--', s.strip(), '--')
print('--', s.rstrip(), '--')
print('--', s.lstrip(), '--')

s = '<><abc><><defg><><>'
print('--', s.strip('<>'), '--')
print('--', s.strip('><'), '--')

s = 'Hello Python'
print(s, s.replace('Python', 'Java'))

# 분리와 결합
s = 'spam and ham'
print(s, s.split('and'))

s2 = ':'.join(s)
print(s2)

s3 = 'one:two:three:four:five'
print(s3.split(':'))
print(s3.split(':', 2))
print(s3.rsplit(':', 2))

lines = '''1st line
2nd line

3rd line
4th line
'''
print(lines)
print(lines.split('\n'))
print(lines.splitlines())

#판별
print('1234'.isdigit())
print('abcd'.isalpha())
print('1234a'.isdigit())
print('abcd1'.isalpha())

# 0 채우기
print('20'.zfill(5))
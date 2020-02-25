import re


# 기본 인수값

def incr(a, step=1):
    return a + step


print(incr(1))
print(incr(10, step=10))


# 키워드 인수
def area(width, height):
    return width * height


print(area(width=10, height=20))


# 가변 인수
def vargs(a, *args):
    print(a, args)


vargs(1, [2, 3], 4)


def _print(end='..', *args):
    print(args, sep=end)


_print("...", "hi", "hello")


def _print(*args, end="\n"):
    print(args, end)


_print(10, 20, 30)
_print(10, end='\t')
_print(10, '\t')


# 튜플/dict 파라미터

def h(name, age, height):
    print(name, age, height)


t = ('둘리', 10, 150)
h(*t)

d = {"name": "둘리", "height": 150, "age": 5}
h(**d)

# 문자열 데이터를 분석하기 전에 처리함수 만들기

# 1, 공백제거
# 2. 필요없는 문자 부호 제거
# 3. 대소문자 정리

states = ['Alabama', ' Georgia!', 'Georgia ', 'georgia', 'FlOrIda', 'south carolina   ', 'West virginia?']


def clean_string(strings):
    results = []
    for s in strings:
        s = s.strip()  # 공백제거
        s = re.sub('[!#?]', '', s)  # 필요없는 문자 제거
        s = s.title()
        results.append(s)

    return results


def clean_string(strings, *funcs):
    results = []
    for s in strings:
        for func in funcs:
            s = func(s)
        results.append(s)
    return results

def remove_special(s):
    return re.sub('[!#?]','',s)

results = clean_string(states, str.strip, str.title, remove_special)
print(results)

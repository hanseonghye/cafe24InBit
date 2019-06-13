# 생성
d = {'basketball': 5, 'baseball': 9}

# 접근
print(d['basketball'])  # key로 접근

# 추가
d['valleyball'] = 12

# 반복, 연결 지원하지 않음

# key 를 통한 원소 확인 ( in, not in )
print('baseball' in d)
print('football' in d)

# 다양한 생성 방법
d = {}
print(type(d))
s = {1.}
print(type(s))

d = dict()
print(type(d))

d = dict(one=1, two=2, three=3)
print(d)

d = dict([('one', 1), ('two', 2)])
print(d)

keys = ('one', 'two', 'three')
values = (1, 2, 3)  # set은 원하는 결과를 주지 않는다. 순서가 없으니까.
d = dict(zip(keys, values))
print(d)

# 다양한 key 타입
d = {}
d[True] = 'true'
d[10] = '10'
d[2] = 1
d[1] = 2  # 안먹힌다.
d[(1, 2, 3)] = 6
print(d)

# key객체
keys = d.keys()
print(keys, type(keys))
for key in keys:
    print(key, d[key], sep=" : ")

# value 객체
values = d.values()
print(values, type(values))

items = d.items()
print(items, type(items))
for (key, item) in items:
    print(key, item, sep=" : ")

phone = {
    '둘리': '010-1111-1111',
    '또치': '010-2222-2222',
}

p = phone
p['희동이'] = '010-4444-4444'
print(p, phone)

p = phone.copy()
p['희동이'] = '010-4444-4444'
print(p, phone)

# get() 함수
print(phone.get('둘리'))
print(phone.get('도우너'))

# setDefault
print(phone.setdefault('둘리', '010-000'))
print(phone.setdefault('도우너', '010-7777-6666'))
print(phone)

# 업데이트
phone.update({
    '고길동': '010-7777-7777',
    '또치': '010-8888-8888'
})

print(phone)

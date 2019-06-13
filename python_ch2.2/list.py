# 리스트 생성

l = [1, 2, 'python']
print(l, type(l))
print(l[-3], type(l[-3]))

l2 = l[0:2]
print(l2)
print(id(l2[0]), id(l[0]))

# 반복
l2 = l * 2
print(l2)

# 연결
l3 = l + [2, 4, 5]
print(l3)

# 확인
print(5 in l3)

# 원소 삭제
del l3[0]
print(l3)

del l3
# print(l3) ==> l3라는 객체는 없다.

# 슬라이싱을 통한 치환
a = [1, 2, 3, 4]
a[0:2] = [10, 20]
print(a)
a[0:2] = [10]
print(a)
a[0:1] = [10, 20]
print(a)

# 슬라이싱을 통한 삭제
a = [1, 12, 123, 1234]
a[1:2] = []
a[0:] = []
a = []
print(a)

# 슬라이싱을 통한 삽입
a = [1, 12, 123, 1234]
a[1:1] = ['a']
a[:0] = ['a']  # 처음
a[len(a):] = ['a']  # 마지막
print(a)

a[:0] = [0, 0, 0]  # 여러개 삽입
print(a)

a = [1, 12, 123, 1234]
a.insert(1, 'a')
a.append(12345)  # 맨뒤에
a.insert(0, 0)  # 맨 앞에

a.reverse()
print(a)
a.reverse()

# 제거
a.remove(0)
print(a)  # 값 제거

# 확장
a.extend([-1, -2, -3])
print(a)

# 스택으로 사용하기
stack = []
stack.append(10)
stack.append(20)
stack.append(30)
stack.append(40)
print(stack)
print(stack.pop())
print(stack)

# 큐로 사용하기
q = [1, 2]
q.append(3)
q.append(4)
q.append(5)
print(q.pop(0))
print(q)

# sort() 내장된 소팅 알고리즘
l3 = [1, 5, 2, 4, 56, 1, 3]
l3.sort();
print(l3)
l3.sort(reverse=True)
print(l3)

# 내장함수 sorted
l3 = [10, 23, 13, 41, 2, 4, 1]
l4 = sorted(l3, reverse=True)


def f(n):
    return n % 10


l4 = sorted(l3, key=f)
print(l4)
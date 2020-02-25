# 문제1. 파이썬 경로명 s = '/usr/local/bin/python' 에서 각각의 디렉토리 경로명을 분리하여 출력하세요.

s = '/usr/local/bin/python'
split_list = s.split('/')
print(split_list[1:])

split_list2 = s.rsplit('/', 1)
print(split_list2)

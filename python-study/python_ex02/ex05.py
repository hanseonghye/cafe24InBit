# 함수 sum 을 만드세요. 이 함수는 임의의 개수의 인수를 받아서 그 합을 계산합니다.
import sys


def my_sum(num_list):
    s = 0
    for n in num_list:
        s += int(n)
    return s


argv = sys.argv
num = argv[1:]
print(my_sum(num))

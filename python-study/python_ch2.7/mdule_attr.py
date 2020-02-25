# 파이썬 변수, 함수, 클래스는 각각 자신이 정의된 모듈의 이름이 저장된 __module__ 속성을 가지고 있다.

from math import sin
from mymath import add
from cmd import Cmd

print(sin.__module__)
print(add.__module__)
print(Cmd.__module__)
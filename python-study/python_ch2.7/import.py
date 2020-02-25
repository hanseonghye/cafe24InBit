from math import sin, cos, pi

print(pi)
print(sin(pi / 6), cos(pi / 4))
#
# from mymath import pi
#
# print(pi)
# print(sin(pi / 6), cos(pi / 4))  # 최근껄로 덮어 버린다.

from mymath import pi as mypi

print(pi, mypi)
print(sin(pi / 6), cos(pi / 4))

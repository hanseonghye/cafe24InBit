# 다음 문자열을 모든 소문자를 대문자로 변환하고, 문자 ',', '.','\n'를 없앤 후에 중복
# 없이 각 단어를 순서대로 출력하세요.
import re


def f1(x):
    return x[0]


s = """We encourage everyone to contribute to Python. If you still have questions after reviewing the material
in this guide, then the Python Mentors group is available to help guide new contributors through the process."""
s = s.lower()
str_list = re.sub('[,.\n]', '', s).split(' ')

print(sorted(set(str_list)))
str_dict = {str: str_list.count(str) for str in str_list}
print(sorted(str_dict.items(), key=f1))

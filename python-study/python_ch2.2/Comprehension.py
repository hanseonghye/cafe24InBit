results = []
for n in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]:
    result = n * n
    results.append(result)
print(results)

results = [num * num for num in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]]
print(results)

# 문자열 리스트에서 길이가 2이하인 문자열만
str_list = ['a', 'ab', 'python', 'ab']
results = [s for s in str_list if len(s) <= 2]
print(results)
results = {s for s in str_list if len(s) <= 2}
print(results)

results = [n for n in range(1, 101) if n % 2 == 0]
print(results)
strings = ['a', 'bar', 'cat', 'dove', 'as', 'python']
d = {s: len(s) for s in strings}
print(d)

clab = [(n, '짝' * (str(n).count('3') + str(n).count('6') + str(n).count('9'))) for n in range(1, 101) if
        '3' in str(n) or '6' in str(n) or '9' in str(n)]
print(clab)

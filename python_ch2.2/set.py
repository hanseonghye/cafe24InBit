s = {0, }
s.add(2)
s.discard(2)
s.update({1, 8, 9})
s.clear()

s1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
s2 = {10, 20, 30}
s3 = s1.intersection(s2)
print(s1.union(s2))
print(s3)
print(s1.symmetric_difference(s2))

print(s3.issuperset(s1))
print(s1.issuperset(s3))
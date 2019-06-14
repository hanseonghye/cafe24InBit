s = [5, 9, 3, 8, 60, 20, 1]

for i in range(len(s)):
    for j in range(len(s)):
        if s[i] > s[j]:
            temp = s[i]
            s[i] = s[j]
            s[j] = temp

print(s)

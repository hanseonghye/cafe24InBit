s = [5, 9, 3, 8, 60, 20, 1]

print ("before : ",s)

for i in range(len(s)):
    for j in range(len(s)-1):
        if s[j] < s[j+1]:
            s[j], s[j+1] = s[j+1], s[j]

print("after : ",s)

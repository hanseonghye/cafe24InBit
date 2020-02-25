count = 1
while count < 11:
    print(count)
    count += 1
else:
    print('ok')

print("------------------------------")

count = 1
while count < 11:
    print(count)
    count += 1
    break
else:
    print('ok')

print ("------------------------------")
# break, continue, else
i = 0
while i < 10:
    i+=1
    if i<5:
        continue
    print(i)
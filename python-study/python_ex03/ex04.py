import random


def get_random(max, min):
    return random.randrange(max) + min


max, min = 9, 1

num1, num2 = get_random(max, min), get_random(max, min)
sol = num1 * num2
print(str(num1), " * ", str(num2), " = ?")
random_set = {sol}

while True:
    if len(random_set) == 9:
        break
    random_set.add(get_random(81, 1))

random_set = list(random_set)

for i in range(3):
    print()
    for j in range(3):
        print(random_set[i * 3 + j], end="\t")

print()
if str(sol) == input("answer : "):
    print("정답")
else:
    print("실패")

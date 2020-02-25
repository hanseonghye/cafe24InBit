import random

min, max = 1, 100

while True:
    n = random.randrange(max) + min
    print(n)

    if 'y' == input("inpuy y/n"):
        break;

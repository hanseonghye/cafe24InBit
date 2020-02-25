def reverses(msg):
    new_str = ""
    for i in range(len(msg) - 1, -1, -1):
        new_str += msg[i]
    return new_str


my_str = input("입력> ")
print("결과>", reverses(my_str))

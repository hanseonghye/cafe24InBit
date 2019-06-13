number_list = []

print("enter 5 number")
for i in range(0, 5):
    while True:
        n = input()
        if n.isnumeric():
            number_list.append(int(n))
            break;
        print("숫자를 입력하세요")

print("평균 : ", sum(number_list) / len(number_list))

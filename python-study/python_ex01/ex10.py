number = input('수를 입력하세요: ')

if not number.isnumeric():
    print("정수가 아닙니다.")
    exit(0)

number = int(number)
total = 0
if number % 2 == 0:
    total = sum(n for n in range(1, number+1) if n % 2 == 0)
else:
    total = sum(n for n in range(1, number+1) if n % 2 == 1)

print("결과값 : ", total)

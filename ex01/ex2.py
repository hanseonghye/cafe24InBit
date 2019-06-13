# 문제2. 키보드로 정수 수치를 입력 받아 짝수인지 홀수 인지 판별하는 코드를 작성하세요.
number = input('수를 입력하세요: ')

if not number.isnumeric():
    print("정수가 아닙니다.")
    exit(0)

number = int(number)

if number % 2 == 0:
    print("짝수")
else:
    print("홀수")

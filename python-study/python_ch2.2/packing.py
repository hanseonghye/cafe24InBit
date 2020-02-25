# packing
t = 10, 20, 30, 'python'
print(t, type(t))

# unpacking
a, b, c, d = t  # 개수가 맞아야 한다.

t = (1, 2, 3, 4)
a, *b, c = t
print(a, b, c)

a, b = 1, (2, 3, 4)
print(a, b)


def my_sum(*num):
    sum = 0;
    for i in num:
        sum += i
    return sum;


print(my_sum(1, 2, 3))


def printf(*req):
    print(req[0] % req[1:])


# c의 printf 흉내내기
printf("name: %s, age: %d", "둘리", 10);

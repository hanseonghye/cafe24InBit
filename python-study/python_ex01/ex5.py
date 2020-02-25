my_list = [1, 2, 3]

new_list = list([i for i in my_list if i % 3 == 0])
print("주어진 리스트에서 3의 배수의 개수 => ", len(new_list))
print("주어진 리스트에서 3의 배수의 합 => ", sum(new_list))

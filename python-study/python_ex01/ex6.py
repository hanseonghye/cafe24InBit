won_list = [50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1]
money = 67879
won_dict = {}

for won in won_list:
    if money > won:
        won_dict[won] = int(money / won)
        money = money - won * won_dict[won]

print(won_dict)

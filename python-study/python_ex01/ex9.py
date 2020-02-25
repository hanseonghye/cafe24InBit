my_menu = {"오뎅": 300, "순대": 400, "만두": 500}
input_menu = input("메뉴 : ")

if input_menu not in my_menu:
    print("없는 메뉴 입니다.")
else:
    print(input_menu, "의 가격은 ", my_menu.get(input_menu))


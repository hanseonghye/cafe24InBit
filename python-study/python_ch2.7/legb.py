# 1. Local
# 2. Enclosing Function(내포한 함수)
# 3. Global
# 4. Built-in

a = 1  # 3. G


def f():
    e = 200  # 2. E

    def g():
        l = 100  # 1. L
        print(l)
        print(e)
        print(a)

        print(__name__)  # 4. B

    g()


if __name__ == "__main__":
    f()

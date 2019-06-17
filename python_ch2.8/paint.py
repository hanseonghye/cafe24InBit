from point import Point


def test_unbound_instance_method():
    p = Point()
    p.setx(10)
    p.sety(20)
    p.show()


# 안쓰는 방법
def test_unbound_class_method():
    p = Point()
    Point.setx(p, 10)
    Point.sety(p, 20)
    Point.show(p)


def test_other_method():
    # print(Point.class_method())
    print(Point.static_mothod())


def main():
    # test_unbound_instance_method()
    # test_unbound_class_method()
    test_other_method()


if __name__ == "__main__":
    main()

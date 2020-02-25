from point import Point
from rect import Rect


def test_bound_class_method():
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


def test_member():
    p = Point()
    p.setx(10)
    p.sety(10)
    print(f'{p.x}, {p.y}, {p.count_of_instance}, {Point.count_of_instance}')


def test_constructor():
    p1 = Point(10, 20)
    print(f'{p1.x}, {p1.y}, {p1.count_of_instance}, {Point.count_of_instance}')
    p2 = Point(10, 20)
    print(f'{p2.x}, {p2.y}, {p2.count_of_instance}, {Point.count_of_instance}')

    del p1
    del p2
    print(Point.count_of_instance)


def test_to_string():
    p = Point()
    print(p)
    print(repr(p))


def test_class_rect():
    r1 = Rect(10, 10, 50, 50)
    r2 = eval(repr(r1))

    print(r1, str(r1.area()), sep=':')
    print(r2, str(r2.area()), sep=':')

    r1.draw()
    r2.draw()


def test_oerator_overloading():
    p1 = Point(100, 200)
    p2 = Point(50, 50)
    p3 = p1 + p2
    p4 = p1 - p2

    print(p3)
    print(p4)


def test_oerator_overloading2():
    p3, p4 = Point(), Point()
    p3 += Point(-10, -10)
    p4 -= Point(-10, -10)
    print(p3)
    print(p4)
    print(Rect(10, 20) == Rect(20, 10))
    print(Rect(10, 10) > Rect(10, 5))
    print(Rect(10, 20) < Rect(20, 10))


def main():
    # test_bound_class_method()
    # test_unbound_class_method()
    # test_other_method()
    # test_member()
    # test_constructor()
    # test_to_string()
    # test_class_rect()
    test_oerator_overloading2()


if __name__ == "__main__":
    main()

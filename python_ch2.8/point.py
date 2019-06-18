class Point:
    count_of_instance = 0

    def __init__(self, x=0, y=0):
        self.x, self.y = x, y
        Point.count_of_instance += 1

    def __del__(self):
        Point.count_of_instance -= 1

    def __str__(self):
        return str(type(self))+" Point ({0}, {1})".format(self.x, self.y)
        # return str.__repr__(self)

    def __repr__(self):  # 위처럼 + 를 사용하여 return 할 수 없다.
        return "Point ({0}, {1})".format(self.x, self.y)
        # return " Point ({0}, {1})".format(self.x, self.y)

    def __add__(self, other):
        return Point(self.x + other.x, self.y + other.y)

    def __sub__(self, other):
        return Point(self.x - other.x, self.y - other.y)

    def __iadd__(self, other):
        return Point(self.x + other.x, self.y + other.y)

    def __isub__(self, other):
        return Point(self.x - other.x, self.y - other.y)

    def setx(self, x):
        self.x = x

    def sety(self, y):
        self.y = y

    def getx(self):
        return self.x

    def gety(self):
        return self.y

    def show(self):
        print(f'점 [{self.x}, {self.y}]를 그림')

    @classmethod
    def class_method(cls):
        return cls.count_of_instance

    @staticmethod
    def static_mothod():
        print("static method called")

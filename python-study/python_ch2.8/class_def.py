class MyString:
    pass

s= MyString()
print(type(s), s.__dict__)
print(MyString.__bases__)

s2 = str()
print(type(s2))
print(str.__bases__)


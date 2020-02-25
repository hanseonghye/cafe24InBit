f = open('test.txt', 'rt', encoding='utf-8')
text = f.read()
print(text)

text = f.read()
print('--', text, '--')

# position 단위는 byte
pos = f.tell()
print(pos)

# seek
f.seek(20)
text = f.read()
print(text)

# line 단위로 읽기
f2 = open('fileIO2.py', 'rt', encoding='utf-8')
line_num = 0
while True:
    line = f2.readline()
    if line == '':
        break
    line_num += 1
    print('{0} : {1} '. format(line_num, line), end='')

f3 = open('fileIO2.py','rt',encoding='utf-8')
lines = f3.readlines()
for line_num, line in enumerate(lines):
    print('{0} : {1} '. format(line_num, line), end='')


# with ~ as
with open('fileIO2.py','rt',encoding='utf-8') as f4:
    for line_num , line in enumerate(f4.readlines()):
        print('{0} : {1} '.format(line_num, line), end='')
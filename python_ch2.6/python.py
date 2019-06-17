import sys

x = 0.2
s = 'hello'
print(str(x) + s, sep=' ')
print(x, s, sep=' ')

print('hello world', file=sys.stdout)
print('error: hello world', file=sys.stderr)

f = open('hello.txt', 'w')
print(type(f))
print('hello world',  file=f)
f.close()

sys.stdout.write('hwllo world!!')
seq1 = {'foo', 'bar', 'baz'}
seq2 = {'one', 'two', 'three'}

z = zip(seq1, seq2)
print(z, type(z))

for i in z:
    print(i, type(i))

t1 = [('foo', 'one'), ('bar', 'two'), ('baz', 'three')]
seq1, seq2 = zip(*t1)
print(seq1, seq2)

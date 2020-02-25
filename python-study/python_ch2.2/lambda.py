import re


def f(x):
    return x * 2


for i in range(10):
    print(i, f(i), sep="\t")
else:
    print()

for i in range(10):
    print(i, (lambda x: x * 2)(i), sep="\t")
else:
    print()

states = ['Alabama', ' Georgia!', 'Georgia ', 'georgia', 'FlOrIda', 'south carolina   ', 'West virginia?']


def clean_string(strings, *funcs):
    results = []
    for s in strings:
        for func in funcs:
            s = func(s)
        results.append(s)
    return results


def remove_special(s):
    return re.sub('[!#?]', '', s)


results = clean_string(states, str.strip, str.title, lambda s: re.sub('[!#?]', '', s))
print(results)

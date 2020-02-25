subs = ['I', 'You']
verbs = ['Play', 'Love']
objs = ['Hockey', 'Football']

for result in zip(subs, verbs, objs):
    print(result)

results = []

for sub, verb, obj in zip(subs, verbs, objs):
    s = sub + " " + verb + " " + obj
    results.append(s)

print(results)

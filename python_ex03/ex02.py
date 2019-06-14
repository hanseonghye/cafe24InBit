def frange(val, base=0.0, step=0.1):
    start, end = base, val
    if val < base:
        end = base
        start = val
    results = []
    while True:
        if start >= end:
            break
        results.append(format(start, ".1f"))
        start += step
    return results


print(frange(2))
print(frange(1.0, 2.0))
print(frange(1.0, 3.0, 0.5))

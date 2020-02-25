import sys

import mod_a

print('import infinitely')

for key in sys.modules.keys():
    print(key)
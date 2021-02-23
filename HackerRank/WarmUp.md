# WarmUp 1.

- HackerRank Problem : https://www.hackerrank.com/challenges/sock-merchant/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup   
```
#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the sockMerchant function below.
def sockMerchant(n, ar):
    stack = []
    pair_cnt = 0
    
    ar.sort()
    for num in ar:
        if num not in stack:
            stack.append(num)
        else:
            if num == stack[-1]:
                stack.pop()
                pair_cnt += 1
        
    return pair_cnt

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    ar = list(map(int, input().rstrip().split()))

    result = sockMerchant(n, ar)

    fptr.write(str(result) + '\n')

    fptr.close()
```

- repl.it  
```
# Complete the sockMerchant function below.
def sockMerchant(n, ar):
    stack = []
    pair_cnt = 0
    
    ar.sort()
    for num in ar:
        if num not in stack:
            stack.append(num)
        else:
            if num == stack[-1]:
                stack.pop()
                pair_cnt += 1
        
    return pair_cnt

n = int(input())
ar = list(map(int, input().rstrip().split()))
result = sockMerchant(n, ar)
print(result)

# 5
# 1 1 2 2 3
# 2
```

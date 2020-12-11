# stack = FILO
stack = []
out_list = []

stack.append(1)
stack.append(2)
stack.append(3)
stack.append(4)

out_list.append(stack.pop())
out_list.append(stack.pop())

stack.append(5)
stack.append(6)

print(stack)
print(out_list)

# queue = FIFO
from collections import deque

queue = deque()
out_list = []

queue.append(1)
queue.append(2)
queue.append(3)
queue.append(4)

out_list.append(queue.popleft())
out_list.append(queue.popleft())

queue.append(5)
queue.append(6)

print(queue)
print(out_list)

#recursive
def recurcive_fun(count):
  if count == 5 : return
  print('재귀함수를 호출합니다. count(',count,")")
  recurcive_fun(count + 1)
  print('재귀함수를 종료합니다. count(',count,")")

recurcive_fun(1)




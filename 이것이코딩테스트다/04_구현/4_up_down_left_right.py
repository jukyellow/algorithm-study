#문) 첫줄에 공간의 크기 N을 입력받고, 둘째줄에 이동할 계획(U/D/L/R)을 입력받아 최종 도착지점을 좌표(X,Y)로 출력하시오.

n = int(input())
x, y = 1, 1
plans = input().split()

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
move_types = ['L','R','U','D']
m_type_size = len(move_types)

for plan in plans:
  for i in range(m_type_size):
    if plan == move_types[i]:
      nx = x + dx[i]
      ny = y + dy[i]
      break
  # 공간을 벗어나는 경우 무시
  if nx<1 or ny<1 or nx > n or ny > n: continue
  x,y = nx, ny

print(x, y)


# 문) N,M,K를 입력받아(N=수열의 숫자, M는 최대 더하기 횟수, K는 가장큰수의 반복횟수) 더해진 답을 출력하라.

#풀이1) 이중 loop
n, m, k = map(int, input().split())
data = list(map(int, input().split()))

data.sort()
print('data:', data)
first = data[n-1]
second = data[n-2]
print('first:',first, ',second:',second)

result = 0
result_list = []
while True:
  for i in range(k): #k만큼 반복 더하기
    if m == 0 : break
    result += first #첫번째 수 더하기
    result_list.append(first)
    m -= 1
  
  if m == 0: break
  result += second #두번째 수 더하기
  result_list.append(second)
  m -= 1

print()
print(result_list)
print(result)


#풀이2) 수열
n, m, k = map(int, input().split())
data = list(map(int, input().split()))

data.sort()
print('data:', data)
first = data[n-1]
second = data[n-2]
print('first:',first, ',second:',second)

# 가장 큰수가 더해지는 횟수
plus_cnt = int(m / (k+1)) * k
plus_cnt += m % (k + 1)

result = 0
result += (plus_cnt * first)
result += (m - plus_cnt) * second 

print()
print(result)

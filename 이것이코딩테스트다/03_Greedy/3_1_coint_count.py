# 문제: 돈이 주어졌을때, 동전으로 거슬러주는 동전 최소값은?
print("input money:")
money = int(input())
coin_cnt = 0

coin_types = [500, 100, 50, 10]
coin_type_cnt = {} # 동전별 갯수

mok = 0
for coin in coin_types:
  mok = (money // coin) # // : 나눈후 몫의 정수 부분
  coin_type_cnt[str(coin)] = mok
  coin_cnt += mok

  money %= coin # % : 나눈 후 나머지

  if money <= 0 : break

print("minimum coin_cnt:", coin_cnt)
print("number of coin types:", coin_type_cnt)

### 1. Recursive(재귀호출)
- 자기 자신을 반복해서 호출하는 함수  
- 필수조건: 종료조건이 있어야함   
![image](https://user-images.githubusercontent.com/45334819/71771754-e2c4f000-2f83-11ea-9715-297d92dd2c48.png)

#### 1-1. 관련문제: Backtracking
- 깊이우선 탐색 : 모든 노드 방문하여 해를 찾을때까지 탐색하는 방식
- Backtraking: 해를 찾지 못하면 다시 거슬러 올라와서 탐색을 시작하는 방식  
![image](https://user-images.githubusercontent.com/45334819/71771759-e9ebfe00-2f83-11ea-95f2-f2a46cce93ac.png)

-Sudo code: 해당 노드가 promising한지 검사-> 찾는해와 동일한지 검사-> 아니라면 하위노드 방문(재귀호출)  
![image](https://user-images.githubusercontent.com/45334819/71771755-e5bfe080-2f83-11ea-8962-dcfa8396b807.png)

![image](https://user-images.githubusercontent.com/45334819/71771762-ef494880-2f83-11ea-864b-adbde815f847.png)
![image](https://user-images.githubusercontent.com/45334819/71771774-17d14280-2f84-11ea-8bf1-9ce6060e40a1.png)

#### 1-2. LeetCode: https://leetcode.com/problems/letter-tile-possibilities/


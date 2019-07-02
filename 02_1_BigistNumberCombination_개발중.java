package com.juk.algo.A0702;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

//0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
//예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
//0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
//제한 사항
//numbers의 길이는 1 이상 100,000 이하입니다.
//numbers의 원소는 0 이상 1,000 이하입니다.
//정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
//numbers			return
//[6, 10, 2]		6210
//[3, 30, 34, 5, 9]	9534330
public class BigistNumberCombination {
	public static void main(String[] args) {
		int[] numbers = {3, 30, 34, 5, 9};
		String result = (new BigistNumberCombination()).solution(numbers);
		System.out.println("result:"+ result);
	}
	
    public String solution(int[] numbers) {
    	String answer = "";
        int arrLen = numbers.length;
        Deque<Integer> deque =new ArrayDeque<Integer>(arrLen);
        //Integer[] newNumbers = Arrays.stream( numbers ).boxed().toArray( Integer[]::new );
        
        
    	while(true) {	 
    		int[] max = {0};
    		int[] min = {1000};
	    	getMaxMin(numbers, max, min);
	    	deque.addFirst(max[0]);	  
	    	System.out.println("return max:"+max[0]);
	    	
	    	//if(deque.size()>=arrLen) { break;}
	    	//deque.add(min[0]);	
	    	//System.out.println("return min:"+min[0]);	    	
	    	    	
	    	if(deque.size()>=arrLen) {
	    		break;
	    	}
    	}    	
    	
    	Iterator it = deque.descendingIterator();//큰값은 들어간값의 거꿀로 출력해야함
    	while(it.hasNext()) {
    		int num = (int) it.next();
    		answer += String.valueOf(num);
    	}
    	
    	return answer;
    }
    
    public void getMaxMin(int[] numbers, int[] max, int[] min) {
    	int maxIndex = 0;
    	int minIndex = 0;
    	
    	for(int i=0; i<numbers.length; ++i) {
    		if(numbers[i]==-1) continue; //사용값은 제외
    		if(max[0]==0) { 
    			max[0] = numbers[i]; 
    			min[0] = numbers[i]; 
    		} //초기화
    		
    		int num = numbers[i];
    		System.out.println("max:"+max[0]+",min:"+min[0]+", num:"+num);
    		
    		//숫자의 자리수 계산
    		int maxLen = (int)(Math.log10(max[0])+1);
    		int minLen = (int)(Math.log10(min[0])+1);
    		int numLen = (int)(Math.log10(num)+1);
    		//System.out.println("maxLen:"+maxLen+", numLen:"+numLen);
    		
    		//자리수가 같은경우 큰수가 max
    		if(maxLen==numLen) {
    			if(num>=max[0]) { 
    				max[0] = num; 
    				maxIndex = i;
    				System.out.println(">>>> num:"+num);
    			}
    		}else {
    			//자리수가 다른경우 최대자리수 만큼 1을 채워서 비교, ex) 34, 30, 3 => 34, 30, 31 => max(34), min(30)
    			int gapSize = Math.abs(maxLen - numLen);
    			//System.out.println(">gapSize:"+gapSize);
    			
    			StringBuffer appendOne = new StringBuffer();
				for(int j=0; j<gapSize; ++j) { appendOne.append("1"); }
				System.out.println(">appendOne:"+appendOne);
				
    			if(max[0]>num) {    				
    				String newNum = String.valueOf(num) + appendOne.toString();
    				int newNumInt = Integer.parseInt(newNum);
    				if(max[0] <= newNumInt) { 
    					max[0] = num;
    					maxIndex = i;
    				} //실제 값은 num로 set
    			}else {
    				String newMaxStr = String.valueOf(max[0]) + appendOne.toString();
    				int newMaxInt = Integer.parseInt(newMaxStr);
    				System.out.println(">newMaxInt:"+newMaxInt);
    				if(num >= newMaxInt) { 
    					max[0] = num; 
    					System.out.println(">>>max:"+num);
    					maxIndex = i; 
    				} //실제 값은 num로 set
    			}
    		}    		
    		System.out.println(">max:"+max[0]);
    		
//    		if(minLen==numLen) {
//    			if(num<=min[0]) { 
//    				min[0] = num; 
//    				minIndex = i;
//    				System.out.println(">>>> num:"+num);
//    			}
//    		}else {
//    			//자리수가 다른경우 최대자리수 만큼 1을 채워서 비교, ex) 34, 30, 3 => 34, 30, 31 => max(34), min(30)
//    			int gapSize = Math.abs(minIndex - numLen);
//    			//System.out.println(">gapSize:"+gapSize);
//    			
//    			StringBuffer appendOne = new StringBuffer();
//    			for(int j=0; j<gapSize; ++j) { appendOne.append("1"); }
//    			System.out.println(">appendOne:"+appendOne);
//    			
//    			if(min[0]>num) {    				
//    				String newNum = String.valueOf(num) + appendOne.toString();
//    				int newNumInt = Integer.parseInt(newNum);
//    				if(min[0] >= newNumInt) { 
//    					min[0] = num;
//    					minIndex = i;
//    				} //실제 값은 num로 set
//    			}else {
//    				String newMaxStr = String.valueOf(min[0]) + appendOne.toString();
//    				int newMaxInt = Integer.parseInt(newMaxStr);
//    				System.out.println(">newMinInt:"+newMaxInt);
//    				if(num <= newMaxInt) { 
//    					min[0] = num; 
//    					System.out.println(">>>min:"+num);
//    					minIndex = i; 
//    				} //실제 값은 num로 set
//    			}
//    		}        	
    	}
    	
    	numbers[maxIndex] = -1; //사용한 숫자 표시
    	//numbers[minIndex] = -1; //사용한 숫자 표시
    	
    	//return max;
    }
    
    public int getMin(int[] numbers) {
    	int min=1000;
    	int minIndex = 0;
    	
    	for(int i=0; i<numbers.length; ++i) {
    		if(numbers[i]==-1) continue; //사용값은 제외
    		if(min==1000) { min = numbers[i]; } //초기화
    		
    		int num = numbers[i];
    		System.out.println("max:"+min+", num:"+num);
    		
    		//숫자의 자리수 계산
    		int minLen = (int)(Math.log10(min)+1);
    		int numLen = (int)(Math.log10(num)+1);
    		//System.out.println("maxLen:"+maxLen+", numLen:"+numLen);
    		
    		//자리수가 같은경우 큰수가 max
    		if(minLen==numLen) {
    			if(num<=min) { 
    				min = num; 
    				minIndex = i;
    				System.out.println(">>>> num:"+num);
    			}
    		}else {
    			//자리수가 다른경우 최대자리수 만큼 1을 채워서 비교, ex) 34, 30, 3 => 34, 30, 31 => max(34), min(30)
    			int gapSize = Math.abs(minLen - numLen);
    			//System.out.println(">gapSize:"+gapSize);
    			
    			StringBuffer appendOne = new StringBuffer();
				for(int j=0; j<gapSize; ++j) { appendOne.append("1"); }
				System.out.println(">appendOne:"+appendOne);
				
    			if(min>num) {    				
    				String newNum = String.valueOf(num) + appendOne.toString();
    				int newNumInt = Integer.parseInt(newNum);
    				if(min >= newNumInt) { 
    					min = num; //실제 값은 num로 set
    					minIndex = i;
    				} 
    			}else {
    				String newMaxStr = String.valueOf(min) + appendOne.toString();
    				int newMaxInt = Integer.parseInt(newMaxStr);
    				System.out.println(">newMaxInt:"+newMaxInt);
    				if(num <= newMaxInt) { 
    					min = num;  //실제 값은 num로 set
    					System.out.println(">>>min:"+num);
    					minIndex = i; 
    				} 
    			}
    		}
    		
    		System.out.println(">min:"+min);
    	}
    	
    	numbers[minIndex] = -1; //사용한 숫자 표시
    	
    	return min;
    }
}

//가장큰수 확인방법:
//1.같은 자리수인경우, 단순 비교
//2.다른 자리수인경우, n/m이라고 할때 n과 m+1을 비교해서 큰게 큰숫자, 작은게 작은숫자

//알고리즘
//숫자만큼 array를 만들어놓고, loop를 돌면서 가장 큰수와/가장작은수를 찾아서 큰수는 첨부터, 작은수는 마지막부터 입력
//=> 원형 큐사용?
//=> 사용후 -1로 채우면 n*n의 복잡도를 1/2로 줄일수 있을듯

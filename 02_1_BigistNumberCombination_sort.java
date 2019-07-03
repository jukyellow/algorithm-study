package com.juk.algo.A0702;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

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
public class BigistNumberCombination2 {
	public static void main(String[] args) {
		int[] numbers = {9, 30, 34, 5, 3};
		String result = (new BigistNumberCombination2()).solution(numbers);
		System.out.println("result:"+ result);
	}
	
	public String solution(int[] numbers) {
    	String answer = "";    	
    	
    	List<String> list = new ArrayList<String>();
    	for(int i=0; i<numbers.length; ++i) {
    		list.add(String.valueOf(numbers[i]));
    	}
    	//System.out.println(">>"+list);
    	
    	Collections.sort(list, new Comparator<Object>() {     
            public int compare(Object o1,Object o2) {                
            	String num1 = (String)o1;
            	String num2 = (String)o2;
            	
            	Integer num1Int = Integer.parseInt(num1 + num2);
            	Integer num2Int = Integer.parseInt(num2 + num1);            	
            	
             	if(num1Int > num2Int) { return -1; }
             	else if (num1Int == num2Int) { return 0; }
             	else { return 1; }
            }
        });
    	
    	Iterator<String> it = list.iterator();
    	while(it.hasNext()) {
    		String num = (String) it.next();
    		answer += num;
    	}
    	
    	return answer;
    }
	
    public String solution2(int[] numbers) {
    	String answer = "";
    	Integer[] newNumbers = Arrays.stream( numbers ).boxed().toArray( Integer[]::new );
    	List<Integer> list = Arrays.asList(newNumbers);    	
    	
    	//answer = Arrays.deepToString(list.toArray());
    	//System.out.println("answer:"+ answer);
    	
    	Collections.sort(list, new Comparator() {     
            public int compare(Object o1,Object o2) {
            	int num1 = (Integer)o1;
             	int num2 = (Integer)o2;
            	
             	//숫자의 자리수 계산
        		int num1Len = (int)(Math.log10(num1)+1);
        		int num2Len = (int)(Math.log10(num2)+1);
        		//System.out.println("maxLen:"+maxLen+", numLen:"+numLen);
        		
        		//자리수가 같은경우 큰수가 max
        		if(num1Len==num2Len) {
        			if(num1>num2) { return -1; }
        			else if(num1==num2) { return 0; }
        			else { return 1; }
        		}else {
        			//자리수가 다른경우 최대자리수 만큼 1을 채워서 비교, ex) 34, 30, 3 => 34, 30, 31 => max(34), min(30)
        			int gapSize = Math.abs(num1Len - num2Len);
        			//System.out.println(">gapSize:"+gapSize);
        			
        			StringBuffer appendOne = new StringBuffer();
    				for(int j=0; j<gapSize; ++j) { appendOne.append("1"); }
    				//System.out.println(">appendOne:"+appendOne);
    				
        			if(num1>num2) {    				
        				String newNumStr2 = String.valueOf(num2) + appendOne.toString();
        				int newNumInt2 = Integer.parseInt(newNumStr2);
        				if(num1>newNumInt2) { return -1; }
            			else if(num1==newNumInt2) { return 0; }
            			else { return 1; }
        			}else {
        				String newMaxStr1 = String.valueOf(num1) + appendOne.toString();
        				int newMaxInt1 = Integer.parseInt(newMaxStr1);
        				//System.out.println(">newMaxInt:"+newMaxInt);
        				if(newMaxInt1>num2) { return -1; }
            			else if(newMaxInt1==num2) { return 0; }
            			else { return 1; }
        			}
        		}    	
            }
        });
    	
    	Iterator it = list.iterator();
    	while(it.hasNext()) {
    		int num = (int) it.next();
    		answer += String.valueOf(num);
    	}
    	
    	return answer;
    }
}

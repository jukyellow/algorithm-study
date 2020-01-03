package com.juk.algo.A0702;

import java.util.ArrayList;
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
		int[] numbers = {3, 30, 34, 5, 9};
		String result = (new BigistNumberCombination2()).solution(numbers);
		System.out.println("result:"+ result);
	}
	
	public String solution(int[] numbers) {
    	String answer = "";    	
    	
    	List<Integer> list = new ArrayList<Integer>();
    	for(int i=0; i<numbers.length; ++i) {
    		list.add(numbers[i]);
    	}
    	//System.out.println(">>"+list);
    	
    	Collections.sort(list, new Comparator<Object>() {     
            public int compare(Object o1,Object o2) {                
            	Integer num1 = (Integer)o1;
            	Integer num2 = (Integer)o2;
            	
            	String newNum1 = String.valueOf(num1) + String.valueOf(num2);
            	String newNum2 = String.valueOf(num2) + String.valueOf(num1);
            	
            	return -Integer.compare(Integer.parseInt(newNum1), Integer.parseInt(newNum2));
            }
        });
    	
    	Iterator<Integer> it = list.iterator();
    	while(it.hasNext()) {
    		String num = String.valueOf(it.next());
    		answer += num;
    	}
    	
    	return answer;
    }
}

package com.juk.algo.A0702;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

//0 �Ǵ� ���� ������ �־����� ��, ������ �̾� �ٿ� ���� �� �ִ� ���� ū ���� �˾Ƴ� �ּ���.
//���� ���, �־��� ������ [6, 10, 2]��� [6102, 6210, 1062, 1026, 2610, 2106]�� ���� �� �ְ�, ���� ���� ū ���� 6210�Դϴ�.
//0 �Ǵ� ���� ������ ��� �迭 numbers�� �Ű������� �־��� ��, ������ ���ġ�Ͽ� ���� �� �ִ� ���� ū ���� ���ڿ��� �ٲپ� return �ϵ��� solution �Լ��� �ۼ����ּ���.
//���� ����
//numbers�� ���̴� 1 �̻� 100,000 �����Դϴ�.
//numbers�� ���Ҵ� 0 �̻� 1,000 �����Դϴ�.
//������ �ʹ� Ŭ �� ������ ���ڿ��� �ٲپ� return �մϴ�.
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

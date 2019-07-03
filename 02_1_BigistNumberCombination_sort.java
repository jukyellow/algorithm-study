package com.juk.algo.A0702;

import java.util.ArrayList;
import java.util.Arrays;
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
            	
             	//������ �ڸ��� ���
        		int num1Len = (int)(Math.log10(num1)+1);
        		int num2Len = (int)(Math.log10(num2)+1);
        		//System.out.println("maxLen:"+maxLen+", numLen:"+numLen);
        		
        		//�ڸ����� ������� ū���� max
        		if(num1Len==num2Len) {
        			if(num1>num2) { return -1; }
        			else if(num1==num2) { return 0; }
        			else { return 1; }
        		}else {
        			//�ڸ����� �ٸ���� �ִ��ڸ��� ��ŭ 1�� ä���� ��, ex) 34, 30, 3 => 34, 30, 31 => max(34), min(30)
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

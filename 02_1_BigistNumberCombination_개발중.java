package com.juk.algo.A0702;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

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
    	
    	Iterator it = deque.descendingIterator();//ū���� ������ �Ųܷ� ����ؾ���
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
    		if(numbers[i]==-1) continue; //��밪�� ����
    		if(max[0]==0) { 
    			max[0] = numbers[i]; 
    			min[0] = numbers[i]; 
    		} //�ʱ�ȭ
    		
    		int num = numbers[i];
    		System.out.println("max:"+max[0]+",min:"+min[0]+", num:"+num);
    		
    		//������ �ڸ��� ���
    		int maxLen = (int)(Math.log10(max[0])+1);
    		int minLen = (int)(Math.log10(min[0])+1);
    		int numLen = (int)(Math.log10(num)+1);
    		//System.out.println("maxLen:"+maxLen+", numLen:"+numLen);
    		
    		//�ڸ����� ������� ū���� max
    		if(maxLen==numLen) {
    			if(num>=max[0]) { 
    				max[0] = num; 
    				maxIndex = i;
    				System.out.println(">>>> num:"+num);
    			}
    		}else {
    			//�ڸ����� �ٸ���� �ִ��ڸ��� ��ŭ 1�� ä���� ��, ex) 34, 30, 3 => 34, 30, 31 => max(34), min(30)
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
    				} //���� ���� num�� set
    			}else {
    				String newMaxStr = String.valueOf(max[0]) + appendOne.toString();
    				int newMaxInt = Integer.parseInt(newMaxStr);
    				System.out.println(">newMaxInt:"+newMaxInt);
    				if(num >= newMaxInt) { 
    					max[0] = num; 
    					System.out.println(">>>max:"+num);
    					maxIndex = i; 
    				} //���� ���� num�� set
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
//    			//�ڸ����� �ٸ���� �ִ��ڸ��� ��ŭ 1�� ä���� ��, ex) 34, 30, 3 => 34, 30, 31 => max(34), min(30)
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
//    				} //���� ���� num�� set
//    			}else {
//    				String newMaxStr = String.valueOf(min[0]) + appendOne.toString();
//    				int newMaxInt = Integer.parseInt(newMaxStr);
//    				System.out.println(">newMinInt:"+newMaxInt);
//    				if(num <= newMaxInt) { 
//    					min[0] = num; 
//    					System.out.println(">>>min:"+num);
//    					minIndex = i; 
//    				} //���� ���� num�� set
//    			}
//    		}        	
    	}
    	
    	numbers[maxIndex] = -1; //����� ���� ǥ��
    	//numbers[minIndex] = -1; //����� ���� ǥ��
    	
    	//return max;
    }
    
    public int getMin(int[] numbers) {
    	int min=1000;
    	int minIndex = 0;
    	
    	for(int i=0; i<numbers.length; ++i) {
    		if(numbers[i]==-1) continue; //��밪�� ����
    		if(min==1000) { min = numbers[i]; } //�ʱ�ȭ
    		
    		int num = numbers[i];
    		System.out.println("max:"+min+", num:"+num);
    		
    		//������ �ڸ��� ���
    		int minLen = (int)(Math.log10(min)+1);
    		int numLen = (int)(Math.log10(num)+1);
    		//System.out.println("maxLen:"+maxLen+", numLen:"+numLen);
    		
    		//�ڸ����� ������� ū���� max
    		if(minLen==numLen) {
    			if(num<=min) { 
    				min = num; 
    				minIndex = i;
    				System.out.println(">>>> num:"+num);
    			}
    		}else {
    			//�ڸ����� �ٸ���� �ִ��ڸ��� ��ŭ 1�� ä���� ��, ex) 34, 30, 3 => 34, 30, 31 => max(34), min(30)
    			int gapSize = Math.abs(minLen - numLen);
    			//System.out.println(">gapSize:"+gapSize);
    			
    			StringBuffer appendOne = new StringBuffer();
				for(int j=0; j<gapSize; ++j) { appendOne.append("1"); }
				System.out.println(">appendOne:"+appendOne);
				
    			if(min>num) {    				
    				String newNum = String.valueOf(num) + appendOne.toString();
    				int newNumInt = Integer.parseInt(newNum);
    				if(min >= newNumInt) { 
    					min = num; //���� ���� num�� set
    					minIndex = i;
    				} 
    			}else {
    				String newMaxStr = String.valueOf(min) + appendOne.toString();
    				int newMaxInt = Integer.parseInt(newMaxStr);
    				System.out.println(">newMaxInt:"+newMaxInt);
    				if(num <= newMaxInt) { 
    					min = num;  //���� ���� num�� set
    					System.out.println(">>>min:"+num);
    					minIndex = i; 
    				} 
    			}
    		}
    		
    		System.out.println(">min:"+min);
    	}
    	
    	numbers[minIndex] = -1; //����� ���� ǥ��
    	
    	return min;
    }
}

//����ū�� Ȯ�ι��:
//1.���� �ڸ����ΰ��, �ܼ� ��
//2.�ٸ� �ڸ����ΰ��, n/m�̶�� �Ҷ� n�� m+1�� ���ؼ� ū�� ū����, ������ ��������

//�˰���
//���ڸ�ŭ array�� ��������, loop�� ���鼭 ���� ū����/������������ ã�Ƽ� ū���� ÷����, �������� ���������� �Է�
//=> ���� ť���?
//=> ����� -1�� ä��� n*n�� ���⵵�� 1/2�� ���ϼ� ������

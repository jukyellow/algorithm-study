package com.juk.algo.A0702;

import java.util.Stack;

//Given a balanced parentheses string S, compute the score of the string based on the following rule:
//() has score 1
//AB has score A + B, where A and B are balanced parentheses strings.
//(A) has score 2 * A, where A is a balanced parentheses string.
//Example 1:
//Input: "()"
//Output: 1
//Example 2:
//Input: "(())"
//Output: 2
//Example 3:
//Input: "()()"
//Output: 2
//Example 4:
//Input: "(()(()))"
//Output: 6
public class ParenthesesScore {
    
    public static void main(String[] args) {
        //String S = "()";
        //String S = "(())";
        //String S = "((()))";
        //String S = "()()";
        //String S = "()()()";
        //String S = "(()())";
        //String S = "(()(()))";
        String S = "(()(()()))";
        int result = (new ParenthesesScore()).scoreOfParentheses(S);
        System.out.println("result:"+result);
    }
    
//���:
//    temp stack: [(, 1]
//    temp stack: [(, 1, +, (, 1]
//    temp stack: [(, 1, +, (, 1, +, 1]
//    temp stack: [(, 1, +, 4]
//    temp stack: [10]
//    result:10
//����: 1.�°�ȣ�� ������ stack�� push(��, ���ȣ ������ �°�ȣ�̸� +������ �߰�)
//     2.���ȣ�� ������ stack���� �°�ȣ�� ���������� ���ϸ鼭 ��ȣ���� ���(��, +�����ڰ� ��Ÿ���� �¿� ���ڸ� �ջ��ؼ� �ٽ� push)
    public int scoreOfParentheses(String S) {
        int result = 0;
        
        Stack<String> stack = new Stack<String>();
        char[] inputArr = S.toCharArray();

        String preReadCh = "";
        for(int idx =0 ; idx<inputArr.length; ++idx) {
            char ch = inputArr[idx];
            
            if(ch == '(') {
            	//������ ���ȣ������, +������ �߰�
                if(preReadCh.equals(")")) { 
                    stack.push("+");
                }
                stack.push(String.valueOf(ch)); 
            }else if(ch == ')') {
                String peekChar = "";
                String popChar = "";
                
                // �°�ȣ("(")���������� pop�ϸ鼭 +�����ڰ� ������ ���ؼ� ���� push��
                while(!stack.empty()) {  
                    peekChar = stack.peek();
                    
                    String pushVal = "";
                    if(peekChar.equals("(")) {
                        popChar = stack.pop(); //'('
                        
                        pushVal = "1";
                        stack.push(pushVal);
                        break;
                    }else if(isNumeric(peekChar)) {
                        popChar = stack.pop();
                        
                        if(stack.size()>=2 && stack.peek().equals("+")) {
                            stack.pop(); //'+'
                            String beforeVal = stack.pop();
                            int sumVal = Integer.parseInt(beforeVal) + Integer.parseInt(popChar);
                            
                            stack.push(String.valueOf(sumVal));
                            continue;
                        }
                        
                        stack.pop(); //'(' �°�ȣ
                        int val = Integer.parseInt(popChar);
                        stack.push(String.valueOf(val*2)); 
                        
                        break;
                    }else {
                    	//System.out.println(">>>"+peekChar);
                    }
                }
                
                System.out.println("temp stack: " + stack);
            }else { ; }
            
            preReadCh = String.valueOf(ch);
        }
        
        //stack�� ��� ����� �ջ�
        while(!stack.empty()) {
            String popChar = stack.pop();
            
            if(stack.size()>=2 && stack.peek().equals("+")) {
                stack.pop(); //'+'
                String beforeChar = stack.pop();
                int sum = Integer.parseInt(beforeChar) + Integer.parseInt(popChar);

                stack.push(String.valueOf(sum));
            }else {
                result = Integer.parseInt(popChar);
            }
        }
        
        return result;
    }
    
    public static boolean isNumeric(String str) { 
        try {  
            Double.parseDouble(str);  
        return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
    }
}

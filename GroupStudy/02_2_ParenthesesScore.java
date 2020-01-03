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
    
//결과:
//    temp stack: [(, 1]
//    temp stack: [(, 1, +, (, 1]
//    temp stack: [(, 1, +, (, 1, +, 1]
//    temp stack: [(, 1, +, 4]
//    temp stack: [10]
//    result:10
//설명: 1.좌괄호를 만나면 stack에 push(단, 우괄호 다음의 좌괄호이면 +연산자 추가)
//     2.우괄호를 만나면 stack에서 좌괄호를 만날때까지 팝하면서 괄호숫자 계산(단, +연산자가 나타나면 좌우 숫자를 합산해서 다시 push)
    public int scoreOfParentheses(String S) {
        int result = 0;
        
        Stack<String> stack = new Stack<String>();
        char[] inputArr = S.toCharArray();

        String preReadCh = "";
        for(int idx =0 ; idx<inputArr.length; ++idx) {
            char ch = inputArr[idx];
            
            if(ch == '(') {
            	//이전에 우괄호였으면, +연산자 추가
                if(preReadCh.equals(")")) { 
                    stack.push("+");
                }
                stack.push(String.valueOf(ch)); 
            }else if(ch == ')') {
                String peekChar = "";
                String popChar = "";
                
                // 좌괄호("(")만날때까지 pop하면서 +연산자가 나오면 더해서 값을 push함
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
                        
                        stack.pop(); //'(' 좌괄호
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
        
        //stack에 담긴 결과를 합산
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

package com.juk.algo.A0917;

public class RotateString {

    public static void main(String[] args) {
        Solution s = new Solution();
        //boolean result = s.rotateString("abcde", "cdeab");
        //boolean result = s.rotateString("abcde", "abced");
        boolean result = s.rotateString("aa", "a"); 
        System.out.println("result:"+result);
    }

}

class Solution {
    public boolean rotateString(String A, String B) {
        if(A.length()!=B.length()) { return false; }
        
        //1.자신의 오른쪽 값을 가지는 쌓으로 전처리(abcde->ab bc cd de ea)
        StringBuffer pairSB = new StringBuffer();
        char[] ch = A.toCharArray();
        for(int i=0; i<ch.length; ++i) {
            if(i+1<ch.length) {
                pairSB.append(ch[i] + "" + ch[i+1]);
            }else {
                pairSB.append(ch[i] + "" + ch[0]);
            }
        }
        System.out.println("pairSB:"+pairSB.toString());
        
        //2.2문자씩 비교해서 불일치하면 false, 모두 일치하면 true
        String pairStr = pairSB.toString();
        char[] newCh = B.toCharArray();
        for(int i=0; i<newCh.length; ++i) {
            if(i+1<newCh.length) {
                String twoCharStr = newCh[i] + "" + newCh[i+1];
                System.out.println("twoCharStr:"+twoCharStr+",pairStr.indexOf(twoCharStr):"+pairStr.indexOf(twoCharStr));
                //일치하는 문자가 없으면 false
                if(pairStr.indexOf(twoCharStr) == -1) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
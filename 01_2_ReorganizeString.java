package com.juk.algo.A0615;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class RecognizedString_PriorityQueue {

    public static void main(String[] args) {
        //String inputStr = "aab";
        //String inputStr = "aaab";
        //String inputStr = "vvvlo"; //vlvov 
        //String inputStr = "vvvbba"; //vvvbba->vbvba 
        //String inputStr = "vvbbba"; //vvvbba->vbvba 
        //String inputStr = "aaaccb"; //acacb
        //String inputStr = "baaba"; //ababa
        String inputStr = "bbccaa"; //bcabca
        //String inputStr = "tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao";
        //String inputStr = "aaabbbccc";
            
        String answer = new RecognizedString_PriorityQueue().reorganizeString(inputStr);
        System.out.println("answer:"+answer);
    }
    
    public String reorganizeString(String S) {
        if(S==null || S.length()<=0) { return ""; }
        int total = S.length();
        
        //count char
        int[] charCnt = new int[26];
        for(char c : S.toCharArray()) {
        	charCnt[c - 'a'] += 1;
        }
        //System.out.println("charCnt:" + Arrays.toString(charCnt));
        
        //create heap structure
        PriorityQueue<CharStruct> pQue = new PriorityQueue<CharStruct>();
        for(int i=0; i<26; ++i) {
        	if(charCnt[i] > (total+1)/2) return ""; //error (ex: aaab)
            if(charCnt[i]>0) {
	            CharStruct cs = new CharStruct((char) (i + 'a'), charCnt[i]);
	            pQue.add(cs);
            }
        }
        
//        while(!pQue.isEmpty()) {
//        	CharStruct cs = pQue.poll();
//        	System.out.println("ch: "+ cs.ch + " cnt:"+cs.count);
//        }
        
        StringBuffer result = new StringBuffer();
        char preChar = '\0';
        while(pQue.size()>0) {
        	CharStruct cs = pQue.poll(); //pop
        	//이전 문자와 다른 새로운 문자이면 추가
            if(preChar!=cs.ch) {
    result.append(cs.ch);
    preChar = cs.ch;
    
    --cs.count;
                if(cs.count>0) {
    	pQue.add(cs); //push
    }
                
    continue;
            }else {
    //이전문자와 같은문자이면, 하나더 pop하고 추가
    CharStruct cs2 = pQue.poll(); //pop
    result.append(cs2.ch);
    preChar = cs2.ch;
    
    --cs2.count; //사용한건 갯수를 줄이고
                if(cs2.count>0) {
    	pQue.add(cs2); //push
    }
                
                //처음 꺼낸건 다시 넣기
                pQue.add(cs); //push
            }
        }
        
        return result.toString();
    }    
    
    private class CharStruct implements Comparable{
    	public char ch;
    	public int count;
    	
    	public CharStruct(char c, int cnt) {
    		this.ch = c;
    		this.count = cnt;
    	}

		@Override
		public int compareTo(Object o) {
			CharStruct target = (CharStruct)o;
			
			if(this.count < target.count) { return 1; }
			else { return -1; }
		}
    }
}

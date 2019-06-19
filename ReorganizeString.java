package com.juk.algo.A0615;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RecognizedString {

    public static void main(String[] args) {
        //String inputStr = "aab";
        //String inputStr = "aaab";
        //String inputStr = "vvvlo"; //vlvov 
        //String inputStr = "vvvbba"; //vvvbba->vbvba 
        //String inputStr = "vvbbba"; //vvvbba->vbvba 
        //String inputStr = "aaaccb"; //acacb
        //String inputStr = "baaba"; //ababa
        //String inputStr = "bbccaa"; //bcabca
        //String inputStr = "tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao";
        String inputStr = "aaabbbccc";
            
        String answer = new RecognizedString().reorganizeString(inputStr);
        System.out.println("answer:"+answer);
    }
    
    public String reorganizeString(String S) {
        //1. count alphabet (ex: a-2, b-3, c-1)
        HashMap<Character, Integer> cntHm = new HashMap<Character, Integer>();
        char[] array = S.toCharArray();
        
        for(int i=0; i<array.length; ++i) {
            char c = array[i];
            if(cntHm.get(c)==null) {
                cntHm.put(c, 1);
            }else {
                int cnt = (int) cntHm.get(c);
                cntHm.put(c, ++cnt);
            }
        }

        //2. sort desc by max count (ex: bbbaac)
        Iterator<Character> sortedIt = sortByValue(cntHm).iterator();
        StringBuffer sb = new StringBuffer();
        while(sortedIt.hasNext()) {
            char key = (char) sortedIt.next();
            for(int i=0; i<(int)cntHm.get(key); ++i) {
                sb.append(key);
            }
        }         
        System.out.println("sorted val:" + sb.toString());

        //3. mix alphabet (ex: babac)
        String mixedStr = mixString(sb.toString(), true);
        
        return mixedStr;
    }    
    
    public String mixString(String sortedStr, boolean reverseChk) {
        char[] sortedArray = sortedStr.toCharArray(); 
        
        for(int i=0; i<sortedArray.length; ++i) {
            char currChar = sortedArray[i];
            
            //find other char
            char otherChar ='\0';
            int j=i+1;
            for( ; j<sortedArray.length; ++j) {
            	otherChar = sortedArray[j];
                if(currChar!=otherChar) {
                    break; //find char
                }else {
                	//if reach to end
                    if(j+1 == sortedArray.length) {
                        if(reverseChk) {
                            StringBuffer sb2 = new StringBuffer(String.valueOf(sortedArray));                            
                            String reverse = sb2.reverse().toString();
                            //System.out.println("reverse:" + reverse);
                            return mixString(reverse, false);
                        }
                        
                        return ""; //error
                    }
                    
                    continue; //move next
                }
            }
            
            //if no find, move to next
            if(i+1 >= sortedArray.length) {
                break; 
            }
            
            //swap next to other
            if(otherChar!='\0') {
                char nextChar = sortedArray[i+1];
                sortedArray[i+1] = otherChar;
                sortedArray[j] = nextChar;
            }
            //System.out.println("mix i:"+ i +":" + String.valueOf(sortedArray));
        }        
        
        return String.valueOf(sortedArray);
    }

    public List sortByValue(final Map map) {
        List list = new ArrayList();
        list.addAll(map.keySet());         

        Collections.sort(list,new Comparator() {     
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);           
                return ((Comparable) v2).compareTo(v1);
            }
        });
        //Collections.reverse(list); // 주석시 오름차순
        //System.out.println("sort2:"+list);
        return list;
    }
    
//    private class ComparatorDesc implements Comparator<Integer>{
//        @Override
//        public int compare(Integer o1, Integer o2) {
//            if(o1 > o2) { return -1; }
//            else if(o1 == o2) { return 0; }
//            else { return 1; }
//        }
//        
//    }
}

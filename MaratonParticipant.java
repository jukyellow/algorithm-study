package com.juk.algo.A0615;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class MaratonParticipant {

	public static void main(String[] args) {
		//String[] participant = {"A","B","C","A","D"};	
		//String[] completion = {"A","B"};	
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};	
		//["leo", "kiki", "eden"], ["eden", "kiki"]
		
		String answer = new MaratonParticipant().solution(participant, completion);
		System.out.println("answer:"+answer);
	}
	
	public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> pMap = new HashMap<String, Integer>();
        
        for(String pName :  participant){
            if(pMap.get(pName)==null){
            	pMap.put(pName, 1);
            }else {
            	int cnt = (int) pMap.get(pName);
                pMap.put(pName, ++cnt);
            }            
            //System.out.println("pName:"+pName+",val:"+pMap.get(pName));
        }
        
        for(String cName : completion) {
        	if(pMap.get(cName)==null) { ; }
        	else {
        		int cnt = (int) pMap.get(cName);
        		--cnt;
        		if(cnt <= 0) { pMap.remove(cName); }
        		else { pMap.put(cName, cnt); }
        	}        	
        	//System.out.println("cName:"+cName+",val:"+pMap.get(cName));
        }
        
        Iterator<Entry<String, Integer>> keys = pMap.entrySet().iterator();
        while(keys.hasNext()) {
        	Entry<String, Integer> nonCompName = keys.next();
        	answer += nonCompName.getKey() + ",";
        }
        if(answer.endsWith(",")) {
        	answer = answer.substring(0, answer.length()-1);
        }
        
        return answer;
    }

}

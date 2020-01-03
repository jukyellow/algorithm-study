package com.kakao.test.A0915;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class test3_WordCompress {

    public static void main(String[] args) throws NumberFormatException, IOException {
        //반복적으로 K를 입력받아서 제거하는 연속처리 구조로 main문생성
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = bufferedReader.readLine().trim();

        while(inputStr.length()>0) {
            int k = Integer.parseInt(bufferedReader.readLine().trim());
            inputStr = WordCompress.compressWord(inputStr, k);
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}

//전처리: baaabbc -> b1a3b2c1으로 압축
//큐를 이용!, K에 해당하는 연속된 문자일때 바로제거->K문자가 아니면, 큐에 넣되 같은 문자의 연속이면 합쳐서 넣고 이때 K가 되면 다시 큐에서 제거->큐에서 FIFO 추출후 압축해제
class WordCompress {
    public static String compressWord(String input, int REMOVE_LEN) {
    	if(input==null || input.trim().length()==0) {
    		return "";
    	}
    	
        //1.전처리: 압축
        StringBuffer compressSB = new StringBuffer();
        char[] charArr = input.toCharArray();
        int compressCnt = 1;
        for(int i=0; i<charArr.length; ++i) {
            if(charArr.length==1) {
                compressSB.append(charArr[i] + "" + compressCnt);
                break;
            }
            if(i>0) {
                if(charArr[i-1]==charArr[i]) ++compressCnt;
                else {
                    compressSB.append(charArr[i-1] + "" + compressCnt); //문자의 건수를 더해서 저장
                    compressCnt = 1;
                }
            }
            if(i==charArr.length-1) {
                compressSB.append(charArr[charArr.length-1] + "" + compressCnt);
            }
        }
        System.out.println("compressSB:"+compressSB.toString());
        
        //2.삭제(REMOVE_LEN에 해당하는 문자열을 삭제)
        Queue<String> que = new LinkedList<String>();        
        char[] compressArr = compressSB.toString().toCharArray();
        for(int i=1; i<compressArr.length; i=i+2) {
            int charLen2Int = compressArr[i]-48; //아스키코드 48 => 숫자 0
            
            if(charLen2Int == REMOVE_LEN) {
                ; //삭제할 길이와 같으면, 바로 제거(저장하지 않음)
            }
            //다르면, 큐에 입력(큐 peek->저장할 단어와 같으면, poll(pop)하여 합산(삭제할 단어와 같으면 제거)->offer(push))
            else {
                if(que.isEmpty()) {
                    que.offer(compressArr[i-1] + "" + compressArr[i]);
                    continue;
                }
                if(que.peek().toCharArray()[0] == compressArr[i-1]) {
                    char[] popCharArr = que.poll().toCharArray();
                    int popCharLen2Int = popCharArr[1]-48;
                    if(popCharLen2Int + charLen2Int == REMOVE_LEN) {
                        ; //삭제할 단어수가 같으면 재입력하지 않음
                    }else {
                        String sum = popCharArr[0] + "" + (popCharLen2Int+charLen2Int);
                        que.offer(sum);
                    }
                }else {
                    //System.out.println(">>"+compressArr[i-1] + "" + compressArr[i]);
                    que.offer(compressArr[i-1] + "" + compressArr[i]);
                }
            }
        }
        
        //3.압축 문자열 full 스트링으로 재변환
        StringBuffer newSB = new StringBuffer();
        while(!que.isEmpty()) {
            String popStr = que.poll();
            char popChar = popStr.toCharArray()[0];
            char popCharLen = popStr.toCharArray()[1];
            
            int popChar2Int = popCharLen-48;
            for(int i=0; i<popChar2Int; ++i) {
                newSB.append(popChar);
            }
        }
        System.out.println("new:"+newSB.toString());
        
        //4.삭제가 끝난 문자열 반환
        return newSB.toString();
    }
    
}
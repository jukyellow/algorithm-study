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
        //�ݺ������� K�� �Է¹޾Ƽ� �����ϴ� ����ó�� ������ main������
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

//��ó��: baaabbc -> b1a3b2c1���� ����
//ť�� �̿�!, K�� �ش��ϴ� ���ӵ� �����϶� �ٷ�����->K���ڰ� �ƴϸ�, ť�� �ֵ� ���� ������ �����̸� ���ļ� �ְ� �̶� K�� �Ǹ� �ٽ� ť���� ����->ť���� FIFO ������ ��������
class WordCompress {
    public static String compressWord(String input, int REMOVE_LEN) {
    	if(input==null || input.trim().length()==0) {
    		return "";
    	}
    	
        //1.��ó��: ����
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
                    compressSB.append(charArr[i-1] + "" + compressCnt); //������ �Ǽ��� ���ؼ� ����
                    compressCnt = 1;
                }
            }
            if(i==charArr.length-1) {
                compressSB.append(charArr[charArr.length-1] + "" + compressCnt);
            }
        }
        System.out.println("compressSB:"+compressSB.toString());
        
        //2.����(REMOVE_LEN�� �ش��ϴ� ���ڿ��� ����)
        Queue<String> que = new LinkedList<String>();        
        char[] compressArr = compressSB.toString().toCharArray();
        for(int i=1; i<compressArr.length; i=i+2) {
            int charLen2Int = compressArr[i]-48; //�ƽ�Ű�ڵ� 48 => ���� 0
            
            if(charLen2Int == REMOVE_LEN) {
                ; //������ ���̿� ������, �ٷ� ����(�������� ����)
            }
            //�ٸ���, ť�� �Է�(ť peek->������ �ܾ�� ������, poll(pop)�Ͽ� �ջ�(������ �ܾ�� ������ ����)->offer(push))
            else {
                if(que.isEmpty()) {
                    que.offer(compressArr[i-1] + "" + compressArr[i]);
                    continue;
                }
                if(que.peek().toCharArray()[0] == compressArr[i-1]) {
                    char[] popCharArr = que.poll().toCharArray();
                    int popCharLen2Int = popCharArr[1]-48;
                    if(popCharLen2Int + charLen2Int == REMOVE_LEN) {
                        ; //������ �ܾ���� ������ ���Է����� ����
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
        
        //3.���� ���ڿ� full ��Ʈ������ �纯ȯ
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
        
        //4.������ ���� ���ڿ� ��ȯ
        return newSB.toString();
    }
    
}
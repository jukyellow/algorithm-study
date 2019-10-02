package com.kakao.test.A0915;

import java.io.*;

import java.util.List;
import java.util.ArrayList;

public class test2_StrCompare {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int sCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> s = new ArrayList<>();

        for (int i = 0; i < sCount; i++) {
            String sItem = bufferedReader.readLine();
            s.add(sItem);
        }

        int tCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> t = new ArrayList<>();

        for (int i = 0; i < tCount; i++) {
            String tItem = bufferedReader.readLine();
            t.add(tItem);
        }

        List<String> result = Result.areAlmostEquivalent(s, t);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(result.get(i));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

class Result {

    /*
     * Complete the 'areAlmostEquivalent' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY s
     *  2. STRING_ARRAY t
     */

    public static List<String> areAlmostEquivalent(List<String> s, List<String> t) {
        List<String> result = new ArrayList<String>();

        if(s==null || s.size()==0 || t==null || t.size()==0 ){ 
            result.add("NO"); 
            return result; 
        }
        
        if(s.size() != t.size()) {
            System.out.println("�� �Է� ���ڿ��� ������ �ٸ�");
             result.add("NO"); 
             return result; 
        }
        
        //1. �� ���ڿ����� ���ĺ� ī��Ʈ�� ���ڷ� ��ȯ�Ͽ� ����
        int firCharCntArr[][] = new int[s.size()][26];
        int secCharCntArr[][] = new int[t.size()][26];
        for(int i=0; i<s.size(); ++i){
            for(char c : s.get(i).toCharArray()){
                firCharCntArr[i][c - 'a'] += 1;
            }
            for(char c : t.get(i).toCharArray()){
                secCharCntArr[i][c - 'a'] += 1;
            }
        }

        //3. �� ���ڿ����� ���ĺ� ���ں� ������ ��
        List<List> strList = new ArrayList<List>();
        for(int k=0; k<s.size(); ++k){
            List<CharCountStruct> charList = new ArrayList<CharCountStruct>();
            for(int j=0; j<s.size(); ++j){
                for(int i=0; i<26; ++i){
                    int fCharCnt = firCharCntArr[j][i];
                    int sCharCnt = secCharCntArr[j][i];
                    
                    if(fCharCnt!=0 || sCharCnt!=0){
                        CharCountStruct charCntStruct = new CharCountStruct((char)(i+'a'), fCharCnt, sCharCnt, Math.abs(fCharCnt-sCharCnt));
                        charList.add(charCntStruct);
                    }
                }
            }
            strList.add(charList);
        }

        //4. 3���� ū(4�̻�) ��찡 ������ "NO", �ƴϸ� 'YES'
        for(List charList : strList){
            for(int i=0; i<charList.size(); ++i){
                CharCountStruct charStruct = (CharCountStruct)charList.get(i);
                if(charStruct.differ >=4 ){
                    result.add("NO"); 
                    return result; 
                }
            }
        }

        result.add("YES"); 
        return result;
    }

}

//���ĺ� ������ �����ϴ� Ŭ����(����ü)
class CharCountStruct {
    public char letter;
    public int firstCnt;
    public int secondCnt;
    public int differ;   //�� ���ڿ��� ���ĺ� ���� ����

    public CharCountStruct(char c, int fCnt, int sCnt, int differ){
        this.letter = c;
        this.firstCnt = fCnt;
        this.secondCnt = sCnt;
        this.differ = differ;
    }
}

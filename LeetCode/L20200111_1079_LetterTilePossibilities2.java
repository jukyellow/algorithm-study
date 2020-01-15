//https://leetcode.com/problems/letter-tile-possibilities/
package leetcode;

import java.util.LinkedHashSet;
import java.util.Set;

public class L20200111_1079_LetterTilePossibilities2 {
    public static void main(String args[]) {
        SolutionLetterTilePossibilities2 s = new SolutionLetterTilePossibilities2();
        
        String tiles = "AAB";
        int retCnt = s.numTilePossibilities(tiles);
        System.out.println("retCnt:" + retCnt);
        for(String val : s.verify) {
            System.out.println(val);
        }
    }
}

class SolutionLetterTilePossibilities2{
    int ret=0;
    int[] counts = new int[26];
    Set<String> verify = new LinkedHashSet<>();
    
    private void backtrack(String preFix){
        String curr="";
        for(int i=0;i<26;i++){
            if(counts[i]>0){
                ret++;

                curr=String.valueOf((char)(i + 'A'));
                verify.add(ret + ":" + preFix+curr);
                
                counts[i]--;
                backtrack(preFix + curr);
                counts[i]++; //다음 순회를 위해 count 복구
            }
        }
    }
    public int numTilePossibilities(String tiles) {
        for(char ch: tiles.toCharArray()) counts[ch-'A']++;
        backtrack("");
        return ret;
    }
}
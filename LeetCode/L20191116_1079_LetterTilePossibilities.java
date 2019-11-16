package leetcode;

import java.util.HashSet;
import java.util.Set;

public class L20191116_1079_LetterTilePossibilities {
	public static void main(String args[]) {
		SolutionLetterTilePossibilities s = new SolutionLetterTilePossibilities();
		
		String tiles = "AAB";
		s.numTilePossibilities(tiles);
	}
}

class SolutionLetterTilePossibilities{
	private int count = 0;
    Set<String> done = new HashSet<>();
    public int numTilePossibilities(String tiles) {
        fill(tiles, "");
        return count;
    }
    
    private void fill(String str, String build){
    	System.out.println(">>fill str:"+str+",build:"+build);
    	
        for(int i = 0;i<str.length();i++){
            if(!done.contains(build+str.charAt(i))){
                String rs = str.substring(0,i) + str.substring(i+1,str.length());
                System.out.println("str.substring(0,i):"+str.substring(0,i)+" ,str.substring(i+1,str.length():"+str.substring(i+1,str.length()));
                done.add(build+str.charAt(i));
                count++;
                
                System.out.println("rs:"+rs+" ,str.charAt(i):"+str.charAt(i)+" ,build+str:"+(build+str.charAt(i)));
                fill(rs, build+str.charAt(i));
            }
        }
    }	
}
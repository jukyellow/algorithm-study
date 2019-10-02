package com.kakao.test.A0915;

import java.io.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class test1_ArrSort {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int modelCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> model = new ArrayList<>();

        for (int i = 0; i < modelCount; i++) {
            int modelItem = Integer.parseInt(bufferedReader.readLine().trim());
            model.add(modelItem);
        }

        int result = test1_ArrSort.reduceCapacity(model);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
    
    /*
     * Complete the 'reduceCapacity' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY model as parameter.
     */

    public static int reduceCapacity(List<Integer> model) {
        if(model==null || model.size()==0 ){ return 0 ; }

        int minCeil = (int) Math.ceil(model.size()/2.0); // minimum ceiling (3.5->4)
        System.out.println("minCeil:"+minCeil);
        Integer[] freArr = new Integer[model.size()];

        //1. count num
        HashMap<Integer, Integer> freMap = new HashMap<Integer, Integer>();
        for(Integer type : model){
            if(freMap.get(type)==null){ 
                freMap.put(type, 1); 
            }else{
                Integer cnt = (Integer)freMap.get(type); 
                freMap.put(type, ++cnt);
            }
        }

        //2. set int array for sort
        Iterator<Entry<Integer, Integer>> keys = freMap.entrySet().iterator();
        for(int i=0; keys.hasNext(); ++i){
            Entry<Integer, Integer> key = keys.next();
            Integer val = freMap.get(key.getKey());
            freArr[i]=val;
        }

        //3. sort desc
        Arrays.sort(freArr, new ComparatorDesc());
        for(int i=0; i<freArr.length; ++i){
        	System.out.println("freArr:"+freArr[i]);
        }

        //4. check deactivative generators.
        int deActiveSum = 0;
        int deActTypeCnt = 0;
        for(int i=0; i<freArr.length; ++i){
            ++deActTypeCnt;
            System.out.println("deActTypeCnt:"+deActTypeCnt);
            deActiveSum += freArr[i];
            if(minCeil <= deActiveSum){
                break;
            }
        }

        return deActTypeCnt;
    }
}

//Descending order
class ComparatorDesc implements Comparator<Integer>{
    @Override
    public int compare(Integer num1, Integer num2){
        if(num1==null) num1 = 0;
        if(num2==null) num2 = 0;
        
        if(num1>num2){ return -1; }
        else if (num1==num2){ return 0; }
        else { return 1; }
    }
}



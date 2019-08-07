package com.juk.algo.A0806;

public class ArithmeticSlice {
    public static void main(String[] args) {
        //int[] A = {1,2,3,4};
        //int[] A = {1,3,5,7,9};
        //int[] A = {7,7,7,7};
        int[] A = {3,-1,-5,-9};
        int result = new ArithmeticSlice().numberOfArithmeticSlices(A);
        System.out.println("result:" + result);
    }
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        for (int s = 0; s < A.length - 2; s++) {
            int d = A[s + 1] - A[s];
            System.out.print("[d:"+d+"] " + A[s]+","+A[s+1]+",");
            for (int e = s + 2; e < A.length; e++) {
                if (A[e] - A[e - 1] == d) {
                    count++;
                    System.out.println(A[e]+" >>count:"+count);
                }else {
                    System.out.println("");
                    break;
                }
            }
           
        }
        return count;
    }
}

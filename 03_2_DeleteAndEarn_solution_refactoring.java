package com.juk.algo.A0715;

//Given an array nums of integers, you can perform operations on the array.
//In each operation, you pick any nums[i] and delete it to earn nums[i] points. 
//After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
//You start with 0 points. Return the maximum number of points you can earn by applying such operations.
public class DeleteAndEarn_solution_refactoring {
    public static void main(String[] args) {
        //int[] nums = {3,1,4,2}; 
        //int[] nums = {3, 4, 2, 5}; 
        int[] nums = {2, 2, 3, 3, 3, 4};
        //int[] nums = {2, 2, 3, 3, 3, 4, 5, 5};
        //int[] nums = {2, 2, 2, 2, 3, 3, 4};
        //int[] nums = {2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6};
        //int[] nums = {2,2,2,5, 6,6,6, 7};
        //int[] nums = {8,7,3,8,1,4,10,10,10,2};
        //int[] nums = {8,7,3,8,1,4,5,10,10,10,2};
        //int[] nums = {1,2,3};
        
        int result = new DeleteAndEarn_solution_refactoring().deleteAndEarn(nums);
        System.out.println("result:"+result);
    }
    
    //1. calcualte left->right direction
    //2. save two-accumulate variable
    //3. a->b->c: c의 위치에서 b를 선택할지, a+c의 값을 선택할지 결정
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001]; //1~10000
        for (int x: nums) count[x]++; //sorted list(1~10000)
        int twoStepAgoVal = 0;
        int oneStepAgoVal = 0;
        int prevSeq = -1;

        for (int seq = 0; seq <= 10000; ++seq) {
            if( count[seq] == 0) { continue ; }
            
            int max = Math.max(twoStepAgoVal, oneStepAgoVal);
            
            if (seq != prevSeq + 1) {
                oneStepAgoVal = seq * count[seq] + max;
                twoStepAgoVal = max;
            } else {
                oneStepAgoVal = seq * count[seq] + twoStepAgoVal;
                twoStepAgoVal = max;
            }
            prevSeq = seq;
        }
        
        return Math.max(twoStepAgoVal, oneStepAgoVal);
    }
}

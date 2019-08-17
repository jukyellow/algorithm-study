package com.juk.algo.A0803;

import java.util.Arrays;
import java.util.HashMap;
//You must not modify the array (assume the array is read only).
//You must use only constant, O(1) extra space.
//Your runtime complexity should be less than O(n2).
//There is only one duplicate number in the array, but it could be repeated more than once.
public class FindDupNum2 {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        int result = new FindDupNum2().findDuplicate(nums);
        System.out.println("result:"+result);
    }
    
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        System.out.println("init tortoise:"+tortoise+",hare:"+hare);
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
            System.out.println("tortoise:"+tortoise+",hare:"+hare);
        } while (tortoise != hare);

        
        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        System.out.println("init ptr1:"+ptr1+",ptr2:"+ptr2);
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
            System.out.println("ptr1:"+ptr1+",ptr2:"+ptr2);
        }

        return ptr1;
    }
}

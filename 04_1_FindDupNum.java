package com.juk.algo.A0803;

import java.util.Arrays;
import java.util.HashMap;

public class FindDupNum {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        int result = new FindDupNum().findDuplicate(nums);
        System.out.println("result:"+result);
    }
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        
        for(int i=1; i<nums.length; ++i) {
            if(nums[i-1] == nums[i]) {
                return nums[i];
            }
        }
        
        return 0;
    }
    public int findDuplicate2(int[] nums) {
        HashMap uniqMap = new HashMap();
        for(int num : nums) {
            if(uniqMap.get(num)==null) { 
                uniqMap.put(num, num); 
            }else { 
                return num; 
            }
        }
        return 0;
    }
}

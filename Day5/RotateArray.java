Leetcode 189: https://leetcode.com/problems/rotate-array/
class Solution {
    public void rotate(int[] nums, int k) {
        k=k%nums.length;
        rev(0,nums.length-1,nums);
        rev(0,k-1,nums);
        rev(k,nums.length-1,nums);
        //return nums;
    }


        private void rev(int l,int r,int[] nums)
        {
            while(l<r)
            {
                int temp=nums[l];
                nums[l++]=nums[r];
                nums[r--]=temp;
            }
        }
}  
    

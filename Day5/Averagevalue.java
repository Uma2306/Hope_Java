Leetcode 2455: https://leetcode.com/problems/average-value-of-even-numbers-that-are-divisible-by-three/
class Solution {
    public int averageValue(int[] nums) {
        int sum=0;
        int count=0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]%6==0) 
            {
                sum+=nums[i];
                count++;
            }
        }
        if(count==0) return 0;
        else return sum/count;
        
    }
}

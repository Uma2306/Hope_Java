Leetcode 724: https://leetcode.com/problems/find-pivot-index/
class Solution {
    public int pivotIndex(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++)
        {
            int leftsum=0,rightsum=0;
            for(int j=0;j<i;j++)
            {
                leftsum+=nums[j];
            }
            for(int j=i+1;j<n;j++)
            {
                rightsum+=nums[j];
            }
            if(leftsum==rightsum) return i;
        }
        return -1;
        
    }
}

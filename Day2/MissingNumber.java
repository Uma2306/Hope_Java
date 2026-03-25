Leetcode 268: https://leetcode.com/problems/missing-number/
class Solution {
    public int missingNumber(int[] nums) {
        int len=nums.length,sum=0;
        int tot=len*(len+1)/2;
        for(int n:nums)
        {
            sum+=n;
        }
        return tot-sum;
    }
}

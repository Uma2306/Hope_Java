Leetcode 136: https://leetcode.com/problems/single-number/
class Solution {
    public int singleNumber(int[] nums) {
        int res=0;
        for(int num:nums){
            res^=num;

        }
        return res;
    }
}

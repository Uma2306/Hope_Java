Leetcode 1470: https://leetcode.com/problems/shuffle-the-array/
class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] arr=new int[2*n];
        int index=0;
        for(int i=0;i<n;i++)
        {
            arr[i*2]=nums[i];
            arr[i*2+1]=nums[i+n];
        }
        return arr;
        
    }
}

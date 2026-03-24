Leetcode 3232: https://leetcode.com/problems/find-if-digit-game-can-be-won/
class Solution {
    public boolean canAliceWin(int[] nums) {
        int s=0,g=0;
        for(int n:nums)
        {
            if(n<10) s+=n;
            else g+=n;
        }
        return s!=g;
        
    }
}

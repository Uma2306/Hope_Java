Leetcode 476: https://leetcode.com/problems/number-complement/
class Solution {
    public int findComplement(int n) {
        int mask = 0;
        int temp = n;

        
        while (temp > 0) {
            mask = (mask << 1) | 1;
            temp >>= 1;
        }

        return n ^ mask;
    }
        
    }

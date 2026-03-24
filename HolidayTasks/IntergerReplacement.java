Leetcode 397: https://leetcode.com/problems/integer-replacement/
class Solution {
    public int integerReplacement(int num) {
        int count=0;
        long n=num;
        while(n!=1)
        {
            if(n%2==0) n=n/2;
            else
            {
                 if (n == 3 || n % 4 == 1) {
                    n -= 1;
                } else {
                    n += 1;
                }
            }
            count++;
        }
        return count;
        
    }
}

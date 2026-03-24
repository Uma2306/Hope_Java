Leetcode 400: https://leetcode.com/problems/nth-digit/description/
class Solution {
    public int findNthDigit(int n) {
        long digit=1;
        long start=1;
        long count=9;
        while(n>digit*count)
        {
            n-=(digit*count);
            digit++;
            count*=10;
            start*=10;
        }
        long num=start+(n-1)/digit;
        String str=Long.toString(num);
        int index=(n-1)%(int)digit;
        char ch=str.charAt(index);
        return ch-'0';
        }
    }

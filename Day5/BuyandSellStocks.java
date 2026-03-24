Leetcode 121: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
class Solution {
    public int maxProfit(int[] prices) {
        int buy = 0;            
        int maxProfit = 0;

        for (int sell = 1; sell < prices.length; sell++) {
            if (prices[sell] < prices[buy]) {
                buy = sell;
            } else {
                maxProfit = Math.max(maxProfit, prices[sell] - prices[buy]);
            }
        }

        return maxProfit;
    }
}

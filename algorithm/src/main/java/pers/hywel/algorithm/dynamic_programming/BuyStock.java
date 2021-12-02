
package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 只能进行一次交易，怎样取得最大利润
 *
 *  Best Time to Buy and Sell Stock
 * Solution
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 *
 * Solve:
 *  profit(n) = max{profit(n-1), prices[n]-min}
 *
 * @author 张益达
 *

 */
public class BuyStock {
    public static int maxProfit(int[] prices) {
        if (prices.length < 2)
            return 0;
        int[] profit = new int[prices.length];
        profit[0] = 0;
        int min = prices[0];
        for (int i=1; i<prices.length; i++) {
            profit[i] = Math.max(profit[i-1], prices[i] - min);
            if (prices[i] < min)
                min = prices[i];
        }
        return profit[prices.length-1];
    }


    public static void main(String[] args) {
        int[] test = new int[]{7,1,5,3,6,2};
        System.out.println(maxProfit(test));
    }
}

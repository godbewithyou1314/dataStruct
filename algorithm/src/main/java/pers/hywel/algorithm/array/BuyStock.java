/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.array;

/**
 * Description:
 * 买股票2
 * 给一个数组，可以多次交易，怎么获取最大值
 *
 * Best Time to Buy and Sell Stock II
 * Solution
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one
 * and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy
 * again).
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 *
 * @author 张益达
 * Created on 2019-12-10 14:22
 */
public class BuyStock {
    // 只要后一个数大就买，只要后一个小就卖
    public int maxProfit(int[] prices) {
        int own = -1;
        int profit = 0;
        if (prices.length < 2)
            return 0;
        for(int i=0; i < prices.length; i++) {
            if (i == prices.length - 1) {
                if (own != -1) {
                    profit += prices[i] - own;
                    own = -1;
                }
                return profit;
            }
            if ((own == -1) && (prices[i] < prices[i+1])) {
                own = prices[i];
            }
            if ((own != -1) && (prices[i] > prices[i+1])) {
                profit += (prices[i] - own);
                own = -1;
            }
        }
        return profit;
    }
}

package pers.hywel.algorithm.dynamic_programming;

/**
 * 322. Coin Change [Medium]
 *
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 * 解最小的找零钱数
 * @Date 2022-04-10
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // 金额为i时，最小的零钱方案
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin || dp[i - coin] == -1) {
                    continue;
                }
                min = Math.min(min, dp[i - coin] + 1);
            }
            dp[i] = (min == Integer.MAX_VALUE ? -1 : min);
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange testObj = new CoinChange();
        int[] coins = new int[]{1,2,5};
        int amount = 11;
        int count = testObj.coinChange(coins, amount);
        System.out.println(count);
    }
}

package pers.hywel.algorithm.dynamic_programming;

/**
 * 518. Coin Change 2 [Medium]
 *
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the number of combinations that make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 *
 * 解有多少种找零方式(背包问题)
 *
 * @Date 2022-04-10
 */
public class CoinChange2 {
    // 参考题解：
    // dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
    //
    //State transition:
    //not using the ith coin, only using the first i-1 coins to make up amount j, then we have dp[i-1][j] ways.
    //using the ith coin, since we can use unlimited same coin, we need to know how many ways to make up amount j - coins[i-1] by using first i coins(including ith), which is dp[i][j-coins[i-1]]
    //Initialization: dp[i][0] = 1
    public int change(int amount, int[] coins) {
        // dp[i][j]: 使用i组零钱，达到j金额时的组合数
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                // 不使用当前硬币 + 使用当前硬币
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1]? dp[i][j - coins[i - 1]]: 0);
            }
        }
        return dp[coins.length][amount];
    }

    // Now we can see that dp[i][j] only rely on dp[i-1][j] and dp[i][j-coins[i]],
    // then we can optimize the space by only using one-dimension array.
    public int changeOptimize(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange2 testObj = new CoinChange2();
        int[] coins = new int[]{1,2,5};
        int amount = 5;
        int count = testObj.change(amount, coins);
        System.out.println(count);
    }
}

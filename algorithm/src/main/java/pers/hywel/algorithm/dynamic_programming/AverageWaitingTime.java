
package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 1701. Average Waiting Time
 * Medium
 * There is a restaurant with a single chef. You are given an array customers, where customers[i] = [arrivali, timei]:
 *
 * arrivali is the arrival time of the ith customer. The arrival times are sorted in non-decreasing order.
 * timei is the time needed to prepare the order of the ith customer.
 * When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle. The customer waits till the chef finishes preparing his order. The chef does not prepare food for more than one customer at a time. The chef prepares food for customers in the order they were given in the input.
 *
 * Return the average waiting time of all customers. Solutions within 10-5 from the actual answer are considered accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: customers = [[1,2],[2,5],[4,3]]
 * Output: 5.00000
 * Explanation:
 * 1) The first customer arrives at time 1, the chef takes his order and starts preparing it immediately at time 1, and finishes at time 3, so the waiting time of the first customer is 3 - 1 = 2.
 * 2) The second customer arrives at time 2, the chef takes his order and starts preparing it at time 3, and finishes at time 8, so the waiting time of the second customer is 8 - 2 = 6.
 * 3) The third customer arrives at time 4, the chef takes his order and starts preparing it at time 8, and finishes at time 11, so the waiting time of the third customer is 11 - 4 = 7.
 * So the average waiting time = (2 + 6 + 7) / 3 = 5.
 *
 * @author RobertZhang
 * Created on 2021/3/15 8:22 下午
 *
 */
public class AverageWaitingTime {
    /**
     * dp解法：
     * @param customers
     * @return
     */
    public double averageWaitingTime(int[][] customers) {
        if (customers == null || customers.length < 1) return 0;
        // dp[i]用来表示第i个客户需要等待的总时间数
        // 状态转移：dp[i - 1] ----> dp[i]
        //  1. 如果i个客户到达时间，大于i-1个客户的任务完成时间，则dp[i]只用等待i个客户自己的任务时间
        //  2. 如果i个客户到达时间，小于i-1个客户的任务完成时间，则需要等待i-1个客户的剩余完成时间+自己的任务时间
        int[] waitingTimeDP = new int[customers.length];
        waitingTimeDP[0] = customers[0][1];
        int gap;
        for (int i = 1; i < customers.length; i++) {
            gap = customers[i][0] - customers[i - 1][0];
            if (gap > waitingTimeDP[i - 1]) {
                waitingTimeDP[i] = customers[i][1];
            } else {
                waitingTimeDP[i] = customers[i][1] + waitingTimeDP[i - 1] - gap;
            }
        }
        long sum = 0L;
        for (int waitingTime : waitingTimeDP) {
            sum += waitingTime;
        }
        return (double) sum/waitingTimeDP.length;
    }

    public static void main(String[] args) {
        int[][] customers = new int[][]{{5,2},{5,4},{10,3},{20,1}};
        AverageWaitingTime test = new AverageWaitingTime();
        System.out.println(test.averageWaitingTime(customers));
    }
}

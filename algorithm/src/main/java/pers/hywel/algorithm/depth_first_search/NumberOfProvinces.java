package pers.hywel.algorithm.depth_first_search;

/**
 * Description:
 * 547. Number of Provinces
 * Medium
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
 * and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 * Example 1:
 *  1 1 0
 *  1 1 0
 *  0 0 1
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 * Example 2:
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 *
 * @author RobertZhang
 * Created on 2021/3/16 10:16 下午
 */
public class NumberOfProvinces {
    /**
     * leetcode解法
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        // visited[i] 表示第i个城市是否被访问过
        int[] visited = new int[isConnected.length];
        int count = 0;
        // 一共n个城市，遍历这n个城市，每个城市进行dfs深度搜索
        for (int i = 0; i < isConnected.length; i++) {
            // 如果i城市未访问过，深度遍历全部相关联的城市
            if (visited[i] == 0) {
                dfs(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }

    // 遍历第i个城市所能达到的
    public void dfs(int[][] M, int[] visited, int i) {
        // 遍历第i个城市直接关联的城市
        for (int j = 0; j < M.length; j++) {
            // 继续遍历相邻城市
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
}

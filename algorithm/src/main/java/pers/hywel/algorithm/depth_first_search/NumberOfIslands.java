package pers.hywel.algorithm.depth_first_search;

/**
 * Description:
 * 200. Number of Islands
 * Medium
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * @author RobertZhang
 * Created on 2021/3/18 8:59 下午
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] visited = new int[m][n];
        int island = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1' && visited[row][col] == 0) {
                    dfs(row, col, grid, visited);
                    island++;
                } else {
                    visited[row][col] = 1;
                }
            }
        }
        return island;
    }

    private void dfs(int row, int col, char[][] grid, int[][] visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                || grid[row][col] != '1' || visited[row][col] == 1)
            return;
        visited[row][col] = 1;
        dfs(row - 1, col, grid, visited);
        dfs(row + 1, col, grid, visited);
        dfs(row, col - 1, grid, visited);
        dfs(row, col + 1, grid, visited);
    }

    public static void main(String[] args) {
        NumberOfIslands test = new NumberOfIslands();
        char[][] grid = new char[][]{
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}};
        int island = test.numIslands(grid);
        System.out.println(island);
    }
}

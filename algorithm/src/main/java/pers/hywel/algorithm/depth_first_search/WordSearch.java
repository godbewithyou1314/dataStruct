package pers.hywel.algorithm.depth_first_search;

/**
 * 79. Word Search [Medium]
 * <p>
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * Example 1:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * <p>
 * Example 2:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * <p>
 * Example 3:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 * <p>
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {
    /**
     * leetcode实现
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        // 替换当前（i,j）的值，避免往深处遍历的时候咬尾巴，再匹配到当前位置
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x + 1, word, i + 1)
                || exist(board, y, x - 1, word, i + 1)
                || exist(board, y + 1, x, word, i + 1)
                || exist(board, y - 1, x, word, i + 1);
        board[y][x] ^= 256;
        return exist;
    }

    /**
     * 个人实现
     *
     * @param board
     * @param word
     * @return
     */
    public boolean existMy(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (dfs(i, j, board, visited, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, char[][] board, boolean[][] visited, String word, int matchIndex) {
        // 首字母匹配
        if (board[i][j] == word.charAt(matchIndex)) {
            // 匹配到最后一位，完全匹配上，返回true
            if (matchIndex >= word.length() - 1) {
                return true;
            }
            visited[i][j] = true;
            int newIndex = matchIndex + 1;
            boolean fourDirectionResult = false;
            // 右
            if (j + 1 < board[0].length && !visited[i][j + 1]) {
                fourDirectionResult = fourDirectionResult || dfs(i, j + 1, board, visited, word, newIndex);
            }
            // 下
            if (i + 1 < board.length && !visited[i + 1][j]) {
                fourDirectionResult = fourDirectionResult || dfs(i + 1, j, board, visited, word, newIndex);
            }
            // 左
            if (j - 1 >= 0 && !visited[i][j - 1]) {
                fourDirectionResult = fourDirectionResult || dfs(i, j - 1, board, visited, word, newIndex);
            }
            // 上
            if (i - 1 >= 0 && !visited[i - 1][j]) {
                fourDirectionResult = fourDirectionResult || dfs(i - 1, j, board, visited, word, newIndex);
            }
            // 此路不通，当前节点未使用
            if (!fourDirectionResult) {
                visited[i][j] = false;
            }
            return fourDirectionResult;
        }
        return false;
    }

    public static void main(String[] args) {
        WordSearch obj = new WordSearch();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "ABCESEEEFS";
        System.out.println(obj.exist(board, word));
    }
}

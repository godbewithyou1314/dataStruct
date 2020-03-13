/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 *
 * [二维数组] Valid Sudoku
 *
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following
 * rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Example 1:
 * Input:
 * {
 * {'5','3','.','.','7','.','.','.','.'},
 * {'6','.','.','1','9','5','.','.','.'},
 * {'.','9','8','.','.','.','.','6','.'},
 * {'8','.','.','.','6','.','.','.','3'},
 * {'4','.','.','8','.','3','.','.','1'},
 * {'7','.','.','.','2','.','.','.','6'},
 * {'.','6','.','.','.','.','2','8','.'},
 * {'.','.','.','4','1','9','.','.','5'},
 * {'.','.','.','.','8','.','.','7','9'}
 * }
 * Output: true
 *
 * @author RobertZhang
 *

 */
public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        Set<String> oneSet = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    // 对每一个数进行编码，row(value),  (value)column, row(value)column
                    String encodeValueStr = "(" + board[row][col] + ")";
                    if (!oneSet.add(row + encodeValueStr) || !oneSet.add(encodeValueStr + col)
                            || !oneSet.add(row / 3 + encodeValueStr + col / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] test = new char[][] {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println(isValidSudoku(test));
    }
}

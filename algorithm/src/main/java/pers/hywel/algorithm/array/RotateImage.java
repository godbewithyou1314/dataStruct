package pers.hywel.algorithm.array;

import java.util.Arrays;

/**
 * Description:
 *
 * [二维数组] Rotate Image
 *
 * 翻转图片(用n*n大小的二维矩阵代表图片）
 * 顺时针把图片翻转90度(不能额外申请一个图片空间)
 *
 * Example 1:
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * notes:
 *  (row, col) ---> (col, array.length-1-row)
 * 剥洋葱，(0, 0)-> (1,1) ,每次一行的旋转
 *
 * @author RobertZhang
 *

 */
public class RotateImage {
    public static void rotate(int[][] matrix) {
        int swap;
        for (int row=0; row<matrix.length-1; row++) {
            for (int col=row; col < matrix.length-1-row; col++) {
                swap = matrix[row][col];
                matrix[row][col] = matrix[matrix.length-1-col][row];
                matrix[matrix.length-1-col][row] = matrix[matrix.length-1-row][matrix.length-1-col];
                matrix[matrix.length-1-row][matrix.length-1-col] = matrix[col][matrix.length-1-row];
                matrix[col][matrix.length-1-row] = swap;
            }
        }
    }

    public static void main(String[] args) {
        int[][] test = {{5, 1, 9, 11},{2, 4, 8, 10},{13, 3, 6, 7},{15, 14, 12, 16}};
        rotate(test);
        for (int row=0; row<test.length; row++) {
            System.out.println(Arrays.toString(test[row]));
        }

    }

}

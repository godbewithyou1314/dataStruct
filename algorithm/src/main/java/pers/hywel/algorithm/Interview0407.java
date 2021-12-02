
package pers.hywel.algorithm;


import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 *
 * @author zhangwei111
 * Created on 2021/4/7 11:31 上午
 */
public class Interview0407 {
    public boolean isValid(int[][] matrix) {
        Set<String> rowsSet = new HashSet<>();
        Set<String> colsSet = new HashSet<>();
        Set<String> subMatrixSet = new HashSet<>();

        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) continue;
                if (!colsSet.add(matrix[i][j] + "_" + j)
                        || !rowsSet.add(i + "_" + matrix[i][j])
                        || !subMatrixSet.add(i / 3 + "_" + matrix[i][j] + "_" + j / 3)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int division(int a, int b) {
        if (a < b) return 0;
        int result = 1;
        int temp = b;
        while ((temp << 1) < a) {
            temp = (temp << 1);
            result *= 2;
        }
        result += division(a - temp , b);
        return result;
    }

    public static void main(String[] args) {
        int result = division(6, 3);
        System.out.println(result);
    }





    public int getKthMin(int[] array1, int[] array2, int k) {
        // 1. k非法 (k <= || k > m+n) -->
        if (k <= 0 || k > (array1.length + array2.length)) return Integer.MIN_VALUE;
        // 2. array1 == null || array2 == null
        if (array1.length < 1) {
            return array2[array2.length - k];
        }
        if (array2.length < 1) {
            return array1[k];
        }
        // 4. 正常遍历，array1 and array2 不为边界
        int aPoint = 0;
        int bPoint = array2.length - 1;
        int result = 0;
        for (int i = 0; i < k; i++) {
            // 3. 遍历中，array1 or array2 提前结束 --> 回到2：array[k - i]
            if (aPoint >= array1.length) return array2[bPoint - (k - i)];
            if (bPoint <= 0) return array1[aPoint + (k - i)];
            result = array1[aPoint] < array2[bPoint] ? array1[aPoint++] : array2[bPoint--];
        }
        return result;
    }



}

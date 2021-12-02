
package pers.hywel.algorithm.bit_operate;

/**
 * Description:
 * 371. Sum of Two Integers
 * Medium
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 * Example 1:
 * Input: a = 1, b = 2
 * Output: 3
 *
 * Example 2:
 * Input: a = 2, b = 3
 * Output: 5
 *
 * Constraints:
 *
 * -1000 <= a, b <= 1000
 *
 * @author RobertZhang
 * Created on 2021/4/8 4:18 下午
 */
public class SumOfTwoIntegers {
    public static int getSum(int a, int b) {
        int result = a ^ b;
        // 进位位置
        int carry = (a & b) << 1;
        while (carry != 0) {
            int add1 = result;
            result = result ^ carry;
            // 判断当轮是否需要进位
            carry = (add1 & carry) << 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getSum(10, 1));
    }
}

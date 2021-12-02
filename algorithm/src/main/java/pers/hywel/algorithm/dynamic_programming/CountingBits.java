
package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @author RobertZhang
 * Created on 2021/2/19 7:23 下午
 */
public class CountingBits {
    // 从0开始，每次末尾添加一位0或1，相当于乘2，或者乘2+1
    // 末尾添加0，f(2i)和f(i)一样多的1
    // 末尾添加1，f(2i + 1)比f(i)多一个1
    // f(i * 2) = f(i)            1 ==> 10    偶数，直接末尾加0，1和f(i)一样
    // f(i * 2 + 1) = f(i) + 1    1 ==> 11    奇数，直接末尾加1，1比f(i)多1
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;
        for (int i = 0; i <= num / 2; i++) {
            result[i * 2] = result[i];
            if (i * 2 + 1 <= num) result[i * 2 + 1] = result[i] + 1;
        }
        return result;
    }

    public int[] countBitsModify(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;
        for (int i = 0; i <= num ; ++i) {
            // 和countBits实现一样，末尾添0，但是规律中可以发现，偶数不变，奇数+1
            result[i] = result[i / 2] + (i & 1);
        }
        return result;
    }

    public static void main(String[] args) {
        CountingBits test = new CountingBits();
        for (int i : test.countBits(5))
            System.out.println(i);
    }
}

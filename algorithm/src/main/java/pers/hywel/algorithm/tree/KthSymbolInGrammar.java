package pers.hywel.algorithm.tree;

/**
 * Description:
 * 779. [Medium] K-th Symbol in Grammar
 *
 * On the first row, we write a 0. Now in every subsequent row,
 * we look at the previous row and replace each occurrence of 0 with 01,
 * and each occurrence of 1 with 10.
 *
 * Given row N and index K, return the K-th indexed symbol in row N.
 * (The values of K are 1-indexed.) (1 indexed).
 *
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 2
 * Output: 1
 *
 * Input: N = 4, K = 5
 * Output: 1
 *
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 * row 5: 0110100110010110
 * Note:
 *
 * N will be an integer in the range [1, 30].
 * K will be an integer in the range [1, 2^(N-1)].
 *
 * row(n) = row(n-1) + func(row(n-1)后半段)
 * row(n, k) = k < row(n-1).size() ? row(n - 1, k) : func(row(n-1)后半段, k - row(n-1).size() / 2)
 *
 * @author zhangwei
 * Created on 2021/1/7 8:28 下午
 */
public class KthSymbolInGrammar {

    /**
     *
     *  * row 1: 0
     *  * row 2: 01
     *  * row 3: 0110
     *  * row 4: 01101001
     *  * row 5: 0110100110010110
     *
     * 遇到0变为01，把0和1看作上一层0的左右子结点呢，同时，把1和0看作上一层1的左右子结点，
     * 这样的话，我们整个结构就可以转为二叉树了，那么前四层的二叉树结构如下所示：
     *               0
     *        /             \
     *       0               1
     *    /     \         /     \
     *   0       1       1       0
     *  / \     / \     / \     / \
     * 0   1   1   0   1   0   0   1
     *
     * 我们假设n=4，k=3，其父节点为 (k+1) / 2。那么可以根据k的奇偶，从父节点计算来。
     * 我们可以根据K的奇偶性知道其是左结点还是右结点，
     * 由于K是从1开始的，所以当K是奇数时，其是左结点，当K是偶数时，其是右结点。
     * 而且还能观察出来的是，左子结点和其父节点的值相同，右子结点和其父节点值相反，这是因为0换成了01，1换成了10，
     * 左子结点保持不变，右子结点flip了一下
     *
     */
    public static int kthGrammar(int n, int k) {
        if (n == 1) {
            return 1 - (k % 2);
        }
        if (k % 2 == 0)
            return 1 - kthGrammar(n - 1, (k + 1) / 2);
        else
            return kthGrammar(n - 1, (k + 1) / 2);
    }

    public static void main(String[] args) {
        System.out.println("开始时间：" + System.currentTimeMillis());
        System.out.println(kthGrammar(4, 5));
        System.out.println("结束时间：" + System.currentTimeMillis());
    }
}

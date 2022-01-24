package pers.hywel.algorithm.dynamic_programming;

import pers.hywel.algorithm.tree.TreeNode;
import pers.hywel.algorithm.tree.TreeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. House Robber III 【Medium】
 *
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that all houses in this place form a binary tree.
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 * Example 1:
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 104
 *
 * @Date 2022-01-10
 */
public class HouseRobber3 {

    // 方法一：贪心算法
    public int robGreedy(TreeNode root) {
        int[] ans = robHouse(root);
        return Math.max(ans[0],ans[1]);
    }

    public int[] robHouse(TreeNode root) {
        if(root==null) {
            return new int[2];
        }

        int[] left = robHouse(root.left);
        int[] right = robHouse(root.right);

        int[] ans = new int[2];

        ans[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        ans[1] = root.val + left[0] + right[0];

        return ans;
    }

    // 方法二：递归 + map
    public int rob(TreeNode root) {
        return theft(root, new HashMap<>());
    }

    private int theft(TreeNode root, Map<TreeNode, Integer> theftMap) {
        if (root == null) return 0;
        if (theftMap.containsKey(root)) return theftMap.get(root);
        // 如果偷了当前层，接着偷下一层的下一层
        int sum = 0;
        if (root.left != null) {
            sum += theft(root.left.left, theftMap) + theft(root.left.right, theftMap);
        }
        if (root.right != null) {
            sum += theft(root.right.left, theftMap) + theft(root.right.right, theftMap);
        }
        // 当前层的最大金额，偷当前层 或者 不偷当前层，偷下一层节点
        sum = Math.max(sum + root.val, theft(root.left, theftMap) + theft(root.right, theftMap));
        // 使用map存储当前节点的最终结果，避免反复向下递归时的重复计算
        theftMap.put(root, sum);
        return sum;
    }


    public static void main(String[] args) {
        HouseRobber3 testObj = new HouseRobber3();
        TreeNode root = TreeUtils.genTreeFromArray(new Integer[]{3,2,3,null,3,null,1});
        int max = testObj.robGreedy(root);
        System.out.println(max);
    }
}

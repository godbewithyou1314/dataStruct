package pers.hywel.algorithm.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * 655. Print Binary Tree【Medium】
 * <p>
 * Given the root of a binary tree, construct a 0-indexed m x n string matrix res that represents a formatted layout of the tree.
 * The formatted layout matrix should be constructed using the following rules:
 * <p>
 * The height of the tree is height and the number of rows m should be equal to height + 1.
 * The number of columns n should be equal to 2height+1 - 1.
 * Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
 * For each node that has been placed in the matrix at position res[r][c],
 * place its left child at res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
 * Continue this process until all the nodes in the tree have been placed.
 * Any empty cells should contain the empty string "".
 * Return the constructed matrix res.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,2]
 * Output:
 * [["","1",""],
 * ["2","",""]]
 * <p>
 * Example 2:
 * Input: root = [1,2,3,null,4]
 * Output:
 * [["","","","1","","",""],
 * ["","2","","","","3",""],
 * ["","","4","","","",""]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 210].
 * -99 <= Node.val <= 99
 * The depth of the tree will be in the range [1, 10].
 * <p>
 * 二叉树打印：
 */
public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        // 获取树的层级，确定结果list的长度
        int level = getLevel(root);
        List<List<String>> result = new ArrayList<>(level);
        // 获取每一层的宽度。总宽度 = 1层1个元素 + 2层2个元素 + ... + n层 2^(n-1) 个元素
        int cols = 0;
        for (int i = 0; i < level; i++) {
            cols += 1 << i;
        }
        // 用""初始化结果list
        List<String> temp = new ArrayList<>(cols);
        for (int j = 0; j < cols; j++) {
            temp.add("");
        }
        for (int i = 0; i < level; i++) {
            result.add(new ArrayList<>(temp));
        }
        // 遍历树，放值到result list中
        traverse(root, 0, 0, cols, result);
        return result;
    }

    private void traverse(TreeNode root, int level, int start, int end, List<List<String>> result) {
        if (root == null) return;
        // 每一层都是在中间位置
        int middle = (start + end) / 2;
        result.get(level).set(middle, String.valueOf(root.val));
        traverse(root.left, level + 1, start, middle, result);
        traverse(root.right, level + 1, middle + 1, end, result);
    }

    private Integer getLevel(TreeNode root) {
        if (root == null) return 0;
        return Math.max(1 + getLevel(root.left), 1 + getLevel(root.right));
    }

    public static void main(String[] args) {
        PrintBinaryTree testObj = new PrintBinaryTree();
        TreeNode root = TreeUtils.genTreeFromArray(new Integer[]{10, 5, 15, null, null, 6, 20});
        for (List<String> temp : testObj.printTree(root)) {
            System.out.println(temp);
        }
    }
}

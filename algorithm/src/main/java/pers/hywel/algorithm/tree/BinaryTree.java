package pers.hywel.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Binary Tree :
 * 最多含有两个节点
 */
public class BinaryTree {

    /**
     * 树节点结构定义
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 树遍历方式：
     * 1. Pre-order遍历：根节点->左树->右树
     * 2. In-order遍历：左树->根节点->右树
     * 3. Post-order遍历：左树->右树->根节点
     *
     * @param root
     * */
    public List<Integer> orderTraversal(TreeNode root) {
        if (null == root) {
            return null;
        }
        List<Integer> result = new LinkedList<>();

        //result.add(root.val) 在这里是Pre-order,root->left->right
        result.add(root.val);

        List<Integer> leftList = orderTraversal(root.left);
        if(null!=leftList){result.addAll(leftList);}

        //result.add(root.val) 在这里就是In-order中序遍历，left->root->right

        List<Integer> rightList = orderTraversal(root.right);
        if(null!=rightList){result.addAll(rightList);}

        //result.add(root.val) 在这里就是Post-order后序遍历,left->right->root
        return result;
    }
}

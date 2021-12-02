
package pers.hywel.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {
    /**
     * 按树层次遍历
     */
    public static ArrayList<Integer> treeLayerArray(TreeNode root) {
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        ArrayList<Integer> resultArray = new ArrayList<>();
        treeNodeQueue.offer(root);
        while (!treeNodeQueue.isEmpty()) {
            TreeNode temp = treeNodeQueue.poll();
            if (temp.left != null) treeNodeQueue.offer(temp.left);
            if (temp.right != null) treeNodeQueue.offer(temp.right);
            resultArray.add(temp.val);
        }
        return resultArray;
    }

}

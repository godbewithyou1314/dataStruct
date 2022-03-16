package pers.hywel.algorithm.tree;

/**
 * 99. Recover Binary Search Tree [Medium]
 * <p>
 * You are given the root of a binary search tree (BST),
 * where the values of exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 * <p>
 * Example 1:
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * <p>
 * Example 2:
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [2, 1000].
 * -231 <= Node.val <= 231 - 1
 * <p>
 * Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
 *
 * @Date 2022-03-02
 */
public class RecoverBinarySearchTree {

    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // left --> root --> right
    TreeNode prevElement = null;

    /**
     * '
     * 中序遍历。记录第一个和第二个
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        // In order traversal to find the two elements
        traverse(root);

        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void traverse(TreeNode root) {

        if (root == null)
            return;

        traverse(root.left);

        // Start of "do some business",
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement != null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }

        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;

        // End of "do some business"

        traverse(root.right);
    }

    public static void main(String[] args) {
        RecoverBinarySearchTree testObj = new RecoverBinarySearchTree();
        Integer[] testTreeArray = new Integer[]{1, 3, null, null, 2};
        TreeNode root = TreeUtils.buildTreeFromArray(testTreeArray);
        TreeUtils.prettyPrint(root);
        System.out.println("-----------------------------------");
        testObj.recoverTree(root);
        TreeUtils.prettyPrint(root);
    }
}

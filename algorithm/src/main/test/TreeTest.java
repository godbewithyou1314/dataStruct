import org.junit.Test;
import pers.hywel.algorithm.tree.BinarySearchTree;
import pers.hywel.algorithm.tree.TreeNode;
import pers.hywel.algorithm.tree.TreeUtils;

import java.util.ArrayList;

/*
 * Copyright (C) 2020 Robert, Inc. All Rights Reserved.
 */
public class TreeTest {

    @Test
    public void printTreeTest() {
        int[] nums = new int[]{-10,-3,0,5,9};
        TreeNode tree = BinarySearchTree.sortedArrayToBST(nums);
        ArrayList<Integer> treeArray = TreeUtils.treeLayerArray(tree);
        System.out.println(treeArray.toString());
    }
}

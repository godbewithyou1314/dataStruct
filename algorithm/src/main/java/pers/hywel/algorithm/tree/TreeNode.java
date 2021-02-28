/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.tree;

/**
 * 树节点
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
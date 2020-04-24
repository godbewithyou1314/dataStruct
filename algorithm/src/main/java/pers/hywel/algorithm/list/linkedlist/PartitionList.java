/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.list.linkedlist;

import pers.hywel.algorithm.list.common.ListNode;

/**
 * Description:
 * 86. Partition List
 * Medium
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
 * equal to x.
 * 所有小于x的值都移到链表前边，大于或等于x的节点顺序不变
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 *
 * @author zhangwei111
 * Created on 2020-04-01 19:48
 */
public class PartitionList {
    /**
     * 双指针，一个小指针，一个大指针。遍历链表，分别挂上去就行。注意清理双指针最后为null
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode lessPartition = new ListNode(0);
        ListNode lessPoint = lessPartition;
        ListNode greaterPartition = new ListNode(0);
        ListNode greaterPoint = greaterPartition;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                lessPoint.next = cur;
                lessPoint = cur;
            } else {
                greaterPoint.next = cur;
                greaterPoint = cur;
            }
            cur = cur.next;
        }
        greaterPoint.next = null;
        lessPoint.next = greaterPartition.next;
        return lessPartition.next;
    }

    public static void main(String[] args) {
        PartitionList testClass = new PartitionList();

        ListNode head = new ListNode(1);
        ListNode two = new ListNode(4);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(2);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(2);


        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;

        ListNode result = testClass.partition(head, 3);

        while (result != null) {
            System.out.print(result.val + "->" );
            result = result.next;
        }

    }
}

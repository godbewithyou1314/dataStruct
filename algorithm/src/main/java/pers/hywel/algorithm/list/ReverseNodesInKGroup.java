/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.list;

/**
 * Description:
 * 25. Reverse Nodes in k-Group (每k个节点内翻转)
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not
 * a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * @author RobertZhangwei
 */
public class ReverseNodesInKGroup {
    /**
     * 定义节点结构
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 非递归实现
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int length = 0;
        // get the list length
        for (ListNode i = head; i != null; length++, i = i.next);

        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for(ListNode prev = dmy, tail = head; length >= k; length -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }

            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }

    /**
     * 递归实现（递归空间就不为O(1)了）
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup02(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

    /**
     * 按k个节点翻转
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroupByMyself(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        ListNode result = head;
        ListNode preTail = null;
        while (head != null) {
            ListNode start = head;
            for (int i = 0; i < k; i++) {
                if (head == null) {
                    return result;
                }
                head = head.next;
            }
            ListNode curNewHead = reverseByRec(start, head, k);
            if (result == start)
                result = curNewHead;
            if (preTail != null)
                preTail.next = curNewHead;
            preTail = start;

        }
        return result;
    }



    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();

        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        a.next.next.next = new ListNode(4);

        ReverseNodesInKGroup testClass = new ReverseNodesInKGroup();
        ListNode result = testClass.reverseKGroup(a, 2);
        while (result.next != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
        System.out.println(result.val);

        Long endTime = System.currentTimeMillis();
        System.out.println("运行耗时：" + (endTime - startTime) + "ms");

    }

    /**
     * 链表翻转--递归翻转
     * @param head
     * @param newHead
     * @param k
     * @return
     */
    private ListNode reverseByRec(ListNode head, ListNode newHead, int k) {
        if (k == 0) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverseByRec(next, head, k - 1);
    }

    /**
     * 链表翻转--迭代
     */
    private ListNode reverseByNoRec(ListNode head) {
        ListNode result = null;
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            cur.next = result;
            result = cur;
        }
        return result;
    }
}

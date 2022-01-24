package pers.hywel.algorithm.list;

import pers.hywel.algorithm.list.common.ListNode;

/**
 * Description:
 *
 * Remove Duplicates from Sorted List II (result not contain the duplicate number)
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
 * original list.
 *
 * Return the linked list sorted as well.
 *
 * Example 1:
 *
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 *
 * Input: 1->1->1->2->3
 * Output: 2->3
 *
 * @author zhangwei111
 * Created on 2020-03-31 16:19
 */
public class RemoveDuplicates2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode result = new ListNode(head.val);
        result.next = head;

        ListNode pre = result;
        ListNode cur = head;
        int repeatVal = head.val;

        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val ) {
                repeatVal = cur.val;
                pre.next = cur.next.next;
                cur = cur.next.next;
                continue;
            } else if (cur.val == repeatVal && cur != head) pre.next = cur.next;
            else pre = cur;
            cur = cur.next;
        }
        return result.next;
    }

    /**
     * solve by leecoder
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fakeHead = new ListNode(head.val);
        fakeHead.next = head;

        ListNode pre = fakeHead;
        ListNode cur = head;

        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) cur = cur.next;
            // 无重复
            if (pre.next == cur) pre = pre.next;
            // 有重复
            else pre.next = cur.next;
            cur = cur.next;
        }
        return fakeHead.next;
    }

    public static void main(String[] args) {
        RemoveDuplicates2 testClass = new RemoveDuplicates2();

        ListNode head = new ListNode(1);
        ListNode two = new ListNode(1);
        ListNode three = new ListNode(1);
        ListNode four = new ListNode(2);
        ListNode five = new ListNode(3);

        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        ListNode result = testClass.deleteDuplicates2(head);

        while (result != null) {
            System.out.print(result.val + "->" );
            result = result.next;
        }

    }
}

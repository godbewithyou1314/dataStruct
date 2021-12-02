
package pers.hywel.algorithm.list.linkedlist;

import pers.hywel.algorithm.list.common.ListNode;

/**
 * Description:
 *
 * 83. Remove Duplicates from Sorted List(result contain duplicate number)
 * Easy
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 *
 * @author zhangwei111
 * Created on 2020-04-01 19:33
 */
public class RemoveDuplicates1 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) cur.next = cur.next.next;
            else cur = cur.next;
        }
        return head;
    }


    public static void main(String[] args) {
        RemoveDuplicates1 testClass = new RemoveDuplicates1();

        ListNode head = new ListNode(1);
        ListNode two = new ListNode(1);
        ListNode three = new ListNode(1);
        ListNode four = new ListNode(2);
        ListNode five = new ListNode(3);

        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        ListNode result = testClass.deleteDuplicates(head);

        while (result != null) {
            System.out.print(result.val + "->" );
            result = result.next;
        }

    }
}


package pers.hywel.algorithm.list.linkedlist;

import pers.hywel.algorithm.list.common.ListNode;

/**
 * Description:
 * 92. Reverse Linked List II
 * Medium
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 *
 * @author zhangwei111
 * Created on 2020-04-03 14:18
 */
public class ReverseLinkedList {

    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    /**
     * 方法一：递归实现
     * @param right
     * @param m
     * @param n
     */
    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

    public ListNode reverseBetween1(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    /**
     * 方法二：找到m位置后，然后开始翻转
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode cur = fakeHead;
        for (int i = 1; i < m; i++) {
            cur = cur.next;
        }
        cur.next = reverseLinkedList(cur.next, n - m);
        return fakeHead.next;
    }

    /**
     * 子方法：链表翻转前n个
     *
     * @param head
     *
     * @return
     */
    private ListNode reverseLinkedList(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }
        ListNode stackHead = null;
        ListNode stackBottom = head;
        for (int i = 0; i <= n; i++) {
            ListNode cur = head;
            head = head.next;
            cur.next = stackHead;
            stackHead = cur;
        }
        stackBottom.next = head;
        return stackHead;
    }

    /**
     * 子方法：链表翻转-递归实现
     * @param head
     * @param newHead
     * @param count 翻转次数，如果为-1，则不限制次数，到链表末尾自动停止
     * @return
     */
    private ListNode reverseLinkedListRecur(ListNode head, ListNode newHead, int count) {
        if (head == null || count == 0) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverseLinkedListRecur(next, head, count - 1);
    }

    private ListNode reverseLinkedListRecur(ListNode head, ListNode newHead) {
        return reverseLinkedListRecur(head, newHead, -1);
    }


    public static void main(String[] args) {
        ReverseLinkedList testClass = new ReverseLinkedList();

        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        ListNode result = testClass.reverseLinkedListRecur(head, null);

        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }

    }
}

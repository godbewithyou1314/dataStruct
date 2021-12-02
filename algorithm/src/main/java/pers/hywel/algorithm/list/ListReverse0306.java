
package pers.hywel.algorithm.list;


/**
 * Description:
 *
 * @author zhangwei111
 * Created on 2021/3/6 9:21 上午
 */
public class ListReverse0306 {
     public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // ------>[newHead] [curNode] [head]<---------
    // curHead++
    // [head]++
    // curHead.next = newHead
    // newHead++
    public static ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode curNode = head;
            head = head.next;
            curNode.next = newHead;
            newHead =  curNode;
        }
        return newHead;
    }

    // 测试
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        head.next = node1;
        node1.next = node2;

        ListNode newHead = reverseList(head);

        while (newHead != null) {
            System.out.print(newHead.val + "---->");
            newHead = newHead.next;
        }
    }
}

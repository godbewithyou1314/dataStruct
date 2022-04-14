
package pers.hywel.algorithm.list;


import pers.hywel.algorithm.common.PrintUtils;
import pers.hywel.algorithm.list.common.ListNode;

/**
 * Description:
 *  普通链表翻转
 *  from
 *  1 --> 2-- > 3 --> 4
 *  to
 *  4--> 3 --> 2 --> 1
 *
 * @author zRobertZhang
 * Created on 2021/3/6 9:21 上午
 */
public class ReverseLinkedList {

    // ------>[newHead] [curNode] [head]<---------
    // 类似于两条链表，右边链表退1，左边链表进1，
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

    // 递归实现
    public static ListNode reverseListByRecursion(ListNode head) {
        return reverseListByRecursionHelper(null, head);
    }
    public static ListNode reverseListByRecursionHelper(ListNode newHead, ListNode curNode) {
        if (curNode == null) {
            return newHead;
        }
        ListNode nextNode = curNode.next;
        curNode.next = newHead;
        return reverseListByRecursionHelper(curNode, nextNode);
    }

    // 测试
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        ListNode newHead = reverseListByRecursion(head);
        PrintUtils.printList(newHead);
    }
}

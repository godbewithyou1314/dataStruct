package pers.hywel.algorithm.list;

/**
 * Description:
 *
 * Swap Nodes in Pairs(交换链表中相邻的两节点)
 * ==> 进阶，交换k节点，ReverseNodesInKGroup.class
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * @author RobertZhangwei
 *
 */
public class SwapNodesPairs {
    /**
     * 定义节点结构
     */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 每两个交换，并记得连接上一批两个的尾节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;
            first.next = second.next;
            second.next = first;
            cur.next = second;
            cur = first;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();

        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);


        SwapNodesPairs testClass = new SwapNodesPairs();
        ListNode result = testClass.swapPairs(a);
        while (result.next != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
        System.out.println(result.val);

        Long endTime = System.currentTimeMillis();
        System.out.println("运行耗时：" + (endTime - startTime) + "ms");
    }
}

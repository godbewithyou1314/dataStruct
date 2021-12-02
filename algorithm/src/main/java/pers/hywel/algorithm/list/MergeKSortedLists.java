
package pers.hywel.algorithm.list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Description:
 * Merge k Sorted Lists(合并k个已排序链表)
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * @author RobertZhang
 */


public class MergeKSortedLists {
    // 固定单链表结构
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 最小队列
     * @param lists
     * @return
     */
    public ListNode mergeKListsByPriorityQueue(ListNode[] lists) {
        if (lists==null || lists.length==0) return null;

        PriorityQueue<ListNode> queue= new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }

    /**
     * 归并
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        int length = lists.length;
        while (length > 0 && length != 1) {
            for (int i = 0; i < length / 2; i++) {
                lists[i] = mergeTwoList(lists[i], lists[length - 1 - i]);
            }
            length = length / 2 + length % 2;
        }
        return lists[0];
    }

    public ListNode mergeTwoList(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        ListNode dummyFirst = new ListNode(0);
        ListNode cur = dummyFirst;
        while (a != null || b != null) {
            if (a == null) {
                cur.next = b;
                break;
            }
            if (b == null) {
                cur.next = a;
                break;
            }
            if (a.val < b.val) {
                cur.next = a;
                cur = cur.next;
                a = a.next;
            } else {
                cur.next = b;
                cur = cur.next;
                b = b.next;
            }
        }
        return dummyFirst.next;
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();

        ListNode a = new ListNode(1);
        a.next = new ListNode(4);
        a.next.next = new ListNode(5);

        ListNode b = new ListNode(1);
        b.next = new ListNode(3);
        b.next.next = new ListNode(4);

        ListNode c = new ListNode(2);
        c.next = new ListNode(6);

        ListNode[] lists = {a, b};

        MergeKSortedLists testClass = new MergeKSortedLists();
        ListNode result = testClass.mergeKLists(lists);
        while (result.next != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
        System.out.println(result.val);

        Long endTime = System.currentTimeMillis();
        System.out.println("运行耗时：" + (endTime - startTime) + "ms");
    }
}

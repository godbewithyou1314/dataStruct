
package pers.hywel.algorithm;

/**
 * Description:
 *
 * @author zhangwei111
 * Created on 2021/3/10 4:36 下午
 */
public class Interview0310 {
    static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
    }

    public Node mergeNSortList(Node[] lists) {
        int end = lists.length;
        while (end > 0) {
            for (int i = 0; i < end / 2 + 1; i++) {
                lists[i] = merge2SortList(lists[i], lists[end - 1]);
            }
            end = end / 2;
        }
        return lists[0];
    }

    /**
     * 合并两个有序链表
     * @param head1
     * @param head2
     * @return
     */
    public Node merge2SortList(Node head1, Node head2) {
        Node newHead = new Node();
        Node cur = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = head1 == null ? head2 : head1;
        return newHead.next;
    }
}

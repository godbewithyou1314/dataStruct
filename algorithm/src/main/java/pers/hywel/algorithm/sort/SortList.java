
package pers.hywel.algorithm.sort;

/**
 * 148. Sort List
 * <p>
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * <p>
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * <p>
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * <p>
 * Example 3:
 * Input: head = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 * <p>
 * <p>
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortList {
    /**
     * ListNode定义
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 快排通用思路
     * 1. 分区
     * 2. 分区排序，处理分区内顺序
     * 3. 合并分区，保证本轮整体顺序
     *
     * Notes： int数组快排时，1，3步合并做了。
     *
     * @param head
     * @return
     */
    public ListNode quickSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 分成一半一半，两个分区
        ListNode half = partition(head);
        // 分区内递归处理，保证局部有序
        ListNode l1 = quickSort(head);
        ListNode l2 = quickSort(half);
        // 归并保证整体有序
       return mergeList(l1, l2);
    }

    // 使用快慢指针分区
    public ListNode partition(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return slow;
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode fakeHead = new ListNode();
        ListNode curNode = fakeHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curNode.next = l1;
                curNode = l1;
                l1 = l1.next;
            } else {
                curNode.next = l2;
                curNode = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            curNode.next = l2;
        } else {
            curNode.next = l1;
        }
        return fakeHead.next;
    }


    public static void main(String[] args) {
        SortList testObj = new SortList();
        ListNode head =  genTestList(new int[]{1});
        ListNode newHead = testObj.quickSort(head);
        printList(newHead);
    }

    public static ListNode genTestList(int[] nums) {
        ListNode head = null, pre = null;
        for (int num : nums) {
            ListNode node = new ListNode(num);
            if (head == null) {
                head = node;
            }
            if (pre != null) pre.next = node;
            pre = node;
        }
        return head;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "-->" );
            head = head.next;
        }
    }
}

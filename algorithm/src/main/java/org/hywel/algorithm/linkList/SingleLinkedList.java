package org.hywel.algorithm.linkList;

public class SingleLinkedList {
    public ListNode head;
    public ListNode tail;
    private int length;

    /**
     * ListNode
     */
    public class ListNode {
        private int val;
        public ListNode next;

        private ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }


    /**
     * Initialize your data structure here.
     */
    public SingleLinkedList() {
        this.head = null;
        this.length = 0;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    /**
     * Get the value of the index-th ListNode in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= length || index < 0) {
            return -1;
        }
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * Add a ListNode of value val before the first element of the linked list. After the insertion, the new ListNode will be the first ListNode of the linked list.
     */
    public void addAtHead(int val) {
        ListNode ListNode = new ListNode(val);
        if (length == 0) {
            head = ListNode;
            tail = ListNode;
        }else{
            ListNode.next = head;
            this.head = ListNode;
        }
        this.length++;
    }

    /**
     * Append a ListNode of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode ListNode = new ListNode(val);
        if (length == 0) {
            this.head = ListNode;
            this.tail = ListNode;
        } else {
            tail.next = ListNode;
            this.tail = ListNode;
        }
        this.length++;

    }

    /**
     * Add a ListNode of value val before the index-th ListNode in the linked list. If index equals to the length of linked list, the ListNode will be appended to the end of linked list. If index is greater than the length, the ListNode will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > this.length || index < 0) {
            return;
        }
        if (index == this.length) {
            addAtTail(val);
        } else if (index == 0) {
            addAtHead(val);
        } else {
            ListNode ListNode = new ListNode(val);
            ListNode cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            ListNode.next = cur.next;
            cur.next = ListNode;
            this.length++;
        }

    }

    /**
     * Delete the index-th ListNode in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index >= length || index < 0) {
            return;
        }
        if (1 == length) {
            head = null;
            tail = null;
            length--;
        } else if (0 == index) {
            head = head.next;
            length--;
        } else {
            ListNode cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            if (null == cur.next) {
                tail = cur;
            }
            length--;
        }
    }

    public void printHead() {
        if (null == head) {
            System.out.println("Linked List is empty");
        }
        {
            System.out.println(this.head.val);
        }
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("The Linked List is Empty");
        } else {
            ListNode cur = head;
            for (int i = 0; i < length; i++) {
                System.out.print(cur.val + " ");
                if (null != cur.next) {
                    cur = cur.next;
                }
            }
            System.out.println();
        }
    }


    /**
     * Source: Linked List Cycle
     *
     * 返回链表的环节点，如果没有环则返回null
     *
     * 解决办法：快慢指针
     * 实现步骤：
     * 1.1) Using a slow pointer that move forward 1 step each time
     *
     * 1.2) Using a fast pointer that move forward 2 steps each time
     *
     * 1.3) If the slow pointer and fast pointer both point to the same location after several moving steps, there is a cycle;
     *
     * 1.4) Otherwise, if (fast == NULL || fast->next == NULL), there has no cycle.
     *
     * @param head 链表头节点
     * @return 是否存在环
     */
    public boolean detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    /**
     * Source: Linked List Cycle 2
     *
     * target: 如果链表存在环，返回环节点。否则返回NULL
     *
     * 算法证明，设
     * L1： head到环节点的距离
     * L2: 环节点到相遇的距离
     * 相遇时Slow的路程： L1+L2
     * 相遇时fast的路程： L1+L2+nc(c为一圈环的长度，n为fast相遇时所转的圈数)
     * fast速度和距离均为slow两倍，则有：
     *      2(L1+L2) = L1+L2+nc
     *      L1+L2 = nc
     *      L1 = nc - L2
     *      L1 = (n-1)c + (c-L2)
     *  ==> head到环节点的距离等于n-1圈环后再跑到环节点的距离。所以总会在环节点相遇
     *
     * STEP：
     * STEP 1：判断链表是否存在环
     * STEP 2：在相遇点，一个指针从head开始往前，slow指针从相遇点开始继续往前，他们相遇的点就是环节点
     *
     * */
     public ListNode detectCycleListNode(ListNode head){
         if(null==head||null==head.next){
             return null;
         }
         ListNode fast = head;
         ListNode slow = head;
         ListNode cur = head;
         while(null != fast && null != fast.next){
             fast = fast.next.next;
             slow = slow.next;
             if(fast == slow){
                 while(slow != cur){
                     cur = cur.next;
                     slow = slow.next;
                 }
                 return cur;
             }
         }
         return null;
     }


    /**
     * 返回如下两个交叉链表的交叉点，如果没有则返回null
     * （要求：1. 时间复杂度为O(n),空间复杂度为O(1)）
     *
     *
     * For example, the following two linked lists:
     *
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3
     *                    ↗
     * B:     b1 → b2 → b3
     * begin to intersect at node c1.
     *
     * 实现方法：
     * 1. 利用两次迭代，经历相同循环后，同时指向交叉点
     * （a1+a2+ c1+c2+c3 +b1+b2+b3 =
     *  b1+b2+b3+ c1+c2+c3+ a1+a2）所以这么多次迭代次数后在交叉点相遇
     *
     *  2. 拿到两个链表的Length，去掉较长的部分，再开始迭代。例如把上边例子中B链表去掉较长部分
     *  A:       a1 → a2
     *                   ↘
     *                     c1 → c2 → c3
     *                    ↗
     *  B:       b2 → b3
     */
    //方法一实现
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(a != b){
            a = a == null?headB:a.next;
            b = b == null?headA:b.next;
        }
        return a;
    }

    /**
     * 移除尾部第N个节点
     *
     * Example:
     *
     * Given linked list: 1->2->3->4->5, and n = 2.
     *
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     *
     * 要求：仅遍历链表一次，并且传入的n总是可用的
     *
     * @param head 链表头节点
     * @param n 删除从尾部第n的节点
     * @return 删除后的链表头节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //先定义一个快指针到位置
        ListNode fast = head;
        for(int i=1;i<n;i++){
            fast = fast.next;
        }
        //判断如果删除的是头节点，直接头节点删除然后返回
        if(fast.next==null){
            head = head.next;
            return head;
        }else{
            //fast再前进一步，确保slow指针是在删除节点前一个
            fast = fast.next;
        }
        //fast和slow节点一起往前走，当fast到达链表末尾，slow就是删除节点的前一节点
        ListNode slow = head;
        while(fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }

}



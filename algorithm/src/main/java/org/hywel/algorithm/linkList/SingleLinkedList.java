package org.hywel.algorithm.linkList;

public class SingleLinkedList {
    public Node head;
    public Node tail;
    private int length;

    /**
     * Node
     */
    public class Node {
        public int val;
        public Node next;

        private Node(int val) {
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
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= length || index < 0) {
            return -1;
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node node = new Node(val);
        if (length == 0) {
            head = node;
            tail = node;
        }else{
            node.next = head;
            this.head = node;
        }
        this.length++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if (length == 0) {
            this.head = node;
            this.tail = node;
        } else {
            tail.next = node;
            this.tail = node;
        }
        this.length++;

    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
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
            Node node = new Node(val);
            Node cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            node.next = cur.next;
            cur.next = node;
            this.length++;
        }

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
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
            Node cur = head;
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
            Node cur = head;
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
     * 返回链表的环节点，如果没有环则返回null
     *
     * 实现步骤：
     * 1.1) Using a slow pointer that move forward 1 step each time
     *
     * 1.2) Using a fast pointer that move forward 2 steps each time
     *
     * 1.3) If the slow pointer and fast pointer both point to the same location after several moving steps, there is a cycle;
     *
     * 1.4) Otherwise, if (fast == NULL || fast->next == NULL), there has no cycle.
     *
     * @param head
     * @return
     */
    public boolean detectCycle(Node head) {
        Node fast = head;
        Node slow = head;
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
     * 如果链表存在环，返回环节点。否则返回NULL
     *
     * 实现步骤：
     * STEP 1：判断链表是否存在环
     */

}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */


package pers.hywel.algorithm.linklist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hywel
 * @Comment 功能: 双链表增删，多级链表展开，随机指针链表深层拷贝
 */
public class DoublyLinkedList {
    /**
     * 链表节点定义
     */
    class DoublyListNode {
        int val;
        DoublyListNode next, prev, child;

        public DoublyListNode() {
        }

        DoublyListNode(int _val) {
            val = _val;
            next = null;
            prev = null;
            child = null;
        }

        DoublyListNode(int _val, DoublyListNode _prev, DoublyListNode _next, DoublyListNode _child) {
            val = _val;
            next = _next;
            prev = _prev;
            child = _child;
        }
    }

    /**
     * 链表定义
     */
    private DoublyListNode head;
    private DoublyListNode tail;
    private int length;

    DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        length = 0;
    }

    /**
     * get(index):Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     *
     * @param index: the index-th node you want to get
     * @return the value of the index-th node
     */
    public int get(int index) {
        if (index >= length || index < 0) {
            return -1;
        } else {
            DoublyListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.val;
        }
    }

    /**
     * Add a node of value val before the first element of the linked list
     *
     * @param val : your value
     */
    public void addAtHead(int val) {
        DoublyListNode newNode = new DoublyListNode(val);
        if (length == 0) {
            tail = newNode;
        }
        newNode.next = head;
        head = newNode;
        this.length++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     *
     * @param val your value
     */
    public void addAtTail(int val) {
        DoublyListNode newNode = new DoublyListNode(val);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        this.length++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     *
     * @param index the index-th you want to insert
     * @param val   the value
     */
    public void addAtIndex(int index, int val) {
        if (index > length || index < 0) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
        } else if (index == length) {
            addAtTail(val);
        } else {
            DoublyListNode newNode = new DoublyListNode(val);
            DoublyListNode cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            newNode.next = cur.next;
            newNode.prev = cur;
            cur.next.prev = newNode;
            cur.next = newNode;
            this.length++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid
     *
     * @param index the index that you want to delete
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
            head.prev = null;
            length--;
        } else if (length - 1 == index) {
            tail = tail.prev;
            tail.next = null;
            length--;
        } else {
            DoublyListNode cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            cur.next.next.prev = cur.next.prev;
            cur.next = cur.next.next;
            length--;
        }
    }

    /**
     * 展开一个多级链表
     * <p>
     * Input:
     * 1---2---3---4---5---6--NULL
     * |
     * 7---8---9---10--NULL
     * |
     * 11--12--NULL
     * <p>
     * Output:
     * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
     *
     * @param head
     * @return
     */
    public DoublyListNode flatten(DoublyListNode head) {
        DoublyListNode flattenNode = getChildTail(head);
        while (null != flattenNode && null != flattenNode.prev) {
            flattenNode = flattenNode.prev;
        }
        return flattenNode;
    }

    /**
     * 获取孩子链表的tail节点
     *
     * @param head
     * @return tail节点
     */
    private DoublyListNode getChildTail(DoublyListNode head) {
        //如果链表为null，直接返回null
        if (null == head) {
            return null;
        }
        //如果没有孩子节点，继续往下遍历
        if (null == head.child) {
            //head.next==null表示到达链表尾部，返回该节点
            if (null == head.next) {
                return head;
            }
            //未到尾节点继续向后遍历
            return getChildTail(head.next);
        } else {//发现存在child子链的节点
            //1.保存该他的child节点，并把child指针置为null
            DoublyListNode child = head.child;
            head.child = null;
            DoublyListNode next = head.next;
            head.next = child;
            child.prev = head;
            //返回该child子链的tail节点
            DoublyListNode childTail = getChildTail(child);
            //如果该节点不是tail
            // 1. 把子链的尾节点指向他的next节点
            // 2. 并把next节点的prev指向子链尾节点
            // 3. 继续向后遍历
            if (null != next) {
                childTail.next = next;
                next.prev = childTail;
                return getChildTail(next);
            }
            //如果该节点是父链的tail节点，直接返回子链尾节点
            return childTail;
        }
    }


    /**
     * 含随机指针的链表节点
     */
    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    /**
     * 深层拷贝一个带有随即指针的链表，节点类如上
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {return null;}

        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        // loop 1. copy所有的节点，放到对于的map一一对应
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        // loop 2. 将节点对应得指针赋值上去
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }
}

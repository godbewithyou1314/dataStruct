
package pers.hywel.algorithm.topinterview;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * 146. LRU Cache
 * Medium
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache(2)
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *
 * @author RobertZhang
 * Created on 2020-05-19
 */
class LRUCache {
    class DoubleLinkedListNode {
        int key;
        int val;
        DoubleLinkedListNode pre;
        DoubleLinkedListNode next;

        DoubleLinkedListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }

    int capacity;
    DoubleLinkedListNode head = new DoubleLinkedListNode(0, 0);
    DoubleLinkedListNode tail = new DoubleLinkedListNode(0, 0);
    Map<Integer, DoubleLinkedListNode> data;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.data = new HashMap<>(capacity);
        head.next = tail;
        tail.pre =head;
    }

    // 删除节点
    private void deleteNode(DoubleLinkedListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    // 新增头结点
    private void addHead(DoubleLinkedListNode node) {
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    public int get(int key) {
        if (data.containsKey(key)) {
            DoubleLinkedListNode node = data.get(key);

            deleteNode(node);
            addHead(node);

            return node.val;

        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (data.containsKey(key)) {
            deleteNode(data.get(key));
            data.remove(key);
        }
        if (data.size() >= capacity) {
            DoubleLinkedListNode deletedNode = tail.pre;
            deleteNode(deletedNode);
            data.remove(deletedNode.key);
        }
        DoubleLinkedListNode node = new DoubleLinkedListNode(key, value);
        addHead(node);
        data.put(key, node);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* capacity */ );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}

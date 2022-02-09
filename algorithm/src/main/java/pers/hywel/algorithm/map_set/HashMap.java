package pers.hywel.algorithm.map_set;

/**
 * 自己实现的HashMap
 *
 * @author hywel
 */
public class HashMap {
    class Node {
        int key;
        int val;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

    Node[] keys;

    private static int hashFunction(int key) {
        return key % 10000;
    }

    public HashMap() {
        keys = new Node[10000];
    }


    /**
     * 返回对应key的节点，或者前一个节点信息
     * <p>
     * 如果时头节点，直接返回头节点
     * 如果不是头节点，则根据preNode参数返回前一个节点或者当前节点
     * 没有对应key，返回null
     *
     * @param key     对应key
     * @param preNode 是否前一个节点
     * @return
     */
    private Node getTheKeyNode(int key, boolean preNode) {
        int index = hashFunction(key);
        if (null == keys[index]) {
            return null;
        } else {
            if (keys[index].key == key) {
                return keys[index];
            } else if (keys[index].key > key) {
                return null;
            } else {
                Node cur = keys[index];
                while (null != cur.next) {
                    if (cur.next.key == key) {
                        return preNode ? cur : cur.next;
                    } else if (cur.next.key > key) {
                        return null;
                    } else {
                        cur = cur.next;
                    }
                }
                return null;
            }
        }
    }

    /**
     * HashMap put方法
     *
     * @param key   所需要添加的key
     * @param value key对于的value
     */
    public void put(int key, int value) {
        int index = hashFunction(key);
        Node node = new Node(key, value);
        if (null == keys[index]) {
            keys[index] = node;
        } else {
            if (keys[index].key == key) {
                keys[index].val = value;
            } else if (keys[index].key > key) {
                node.next = keys[index];
                keys[index] = node;
            } else {
                Node cur = keys[index];
                while (null != cur.next) {
                    if (cur.next.key == key) {
                        cur.next.val = value;
                        return;
                    } else if (cur.next.key > key) {
                        node.next = cur.next;
                        cur.next = node;
                        return;
                    } else {
                        cur = cur.next;
                    }
                }
                cur.next = node;
            }
        }
    }

    /**
     * HashMap get方法
     * 有则返回对应的value值，没有返回-1
     *
     * @param key 需要查找的对应值
     * @return
     */
    public int get(int key) {
        Node cur = getTheKeyNode(key, false);
        if (null != cur) {
            return cur.val;
        } else {
            return -1;
        }
    }

    /**
     * HashMap remove方法
     *
     * @param key 需要删除的key值
     */
    public void remove(int key) {
        int index = hashFunction(key);
        if (null != keys[index]) {
            if (keys[index].key == key) {
                keys[index] = keys[index].next;
            } else {
                Node cur = getTheKeyNode(key, true);
                if (null != cur) {
                    cur.next = cur.next.next;
                }
            }
        }
    }
}

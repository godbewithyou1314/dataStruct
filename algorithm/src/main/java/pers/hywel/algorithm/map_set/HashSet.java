package pers.hywel.algorithm.map_set;

/**
 * 自己实现的HashSet结构
 *
 * @author hywel
 */
public class HashSet {

   class Node{
       int val;
       Node next;

       public Node(int val){
           this.val = val;
           next = null;
       }
   }
   Node[] keys;

    public HashSet(){
        keys = new Node[10000];
    }

    private int hashFucntion(int key){
        return key%10000;
    }

    public void add(int key){
        int index = hashFucntion(key);
        Node node = new Node(key);
        if(null == keys[index]){
            keys[index] = node;
        }else {
            if(keys[index].val==key){}
            else if(keys[index].val>key){
                node.next = keys[index];
                keys[index] = node;
            }else {
                Node cur = keys[index];
                while (null != cur.next) {
                    if (cur.next.val == key) {
                        return;
                    } else if (cur.next.val > key) {
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

    public void remove(int key){
        int index = hashFucntion(key);
        if(null != keys[index]){
            if(key == keys[index].val){
                keys[index] = keys[index].next;
                return;
            }
            Node cur = keys[index];
            while (null!=cur.next){
                if(cur.next.val>key){return;}
                else if(cur.next.val==key){
                    cur.next = cur.next.next;
                    return;
                }
                cur = cur.next;
            }
        }
    }

    public boolean contains(int key){
        int index = hashFucntion(key);
        if(null != keys[index]){
            if(key == keys[index].val){
                return true;
            }else {
                Node cur = keys[index];
                while (null != cur.next) {
                    if(cur.next.val>key){return false;}
                    else if (cur.next.val == key) {
                       return true;
                    }
                    cur = cur.next;
                }
            }
        }
        return false;
    }
}

package pers.hywel.algorithm.list;

public class linkListMain {
    public static void main(String[] args){
        SinglyLinkedList l1 = new SinglyLinkedList();
        SinglyLinkedList l2 = new SinglyLinkedList();
        l1.addAtHead(2);
        l1.addAtHead(1);
        l1.addAtHead(0);
        l1.rotateRight(l1.head,4);

//        l2.addAtHead(4);
//        l2.addAtHead(3);
//        l2.addAtHead(1);
//        SinglyLinkedList.ListNode mergedList = l1.mergeTwoLists(l1.head,l2.head);
//
//        SinglyLinkedList.ListNode cur  = mergedList;
//        while (null != cur){
//            System.out.println(cur.getVal());
//            cur = cur.next;
//        }

    }
}
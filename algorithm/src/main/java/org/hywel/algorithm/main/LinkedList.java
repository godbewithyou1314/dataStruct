package org.hywel.algorithm.main;

import org.hywel.algorithm.linkList.SingleLinkedList;

public class LinkedList {
    public static void main(String[] args) {
        SingleLinkedList obj = new SingleLinkedList();

        obj.addAtHead(1);
        obj.print();

        obj.tail.next = obj.head.next;

        if(null == obj.detectCycle(obj.head))
        {
            System.out.println("It's null");
        }else {
            System.out.println(obj.detectCycle(obj.head).val);
        }

    }
}

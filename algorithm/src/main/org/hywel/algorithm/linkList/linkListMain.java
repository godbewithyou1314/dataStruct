package org.hywel.algorithm.linkList;

public class linkListMain {
    public static void main(String[] args){

        singleLinkList singlelist = new singleLinkList(0,1,2,3,4);
        singlelist.printList();

        System.out.println("测试添加元素（下标从0开始计算）：");
        singlelist.insert(5,6);
        singlelist.printList();

        System.out.println("测试删除元素（下标从0开始计算）：");
        singlelist.delete(6);
        singlelist.printList();
//        System.out.println(a.toString());
    }
}
package org.hywel.algorithm.linkList;

/**
 * 单链表
 * 链表的随机访问不如数组的O(1)，链表需要从head遍历，get(i)为O(N)
 * 链表胜在insert和delete操作，均为O(1)
 *
 * 单链表java实现
 * 定义头指针head：指向第一个节点
 *
 * */
public class singleLinkList<T> {

    /**节点
     * 数据
     * 下一节点指针
     * */
    private class Node<T>{
        T data;
        Node<T> next;

        Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    //头指针
    private Node head;
    //尾指针
    private Node tail;
    //链表长度
    private int length = 0;

    public singleLinkList(){
        head = tail = null;
        length = 0;
    }

    public singleLinkList(T... datas){
        for(int i = 0; i < datas.length;i++){
            Node node = new Node<>(datas[i]);
            if(i==0) {head = node; tail = node;}
            tail.next = node;
            tail = node;
            length++;
        }
        System.out.println("链表构造成功！ 链表长度："+length);
    }

    /**判断链表是否为空
     * */
    public boolean isEmpty(){
        return head==null;
    }

    /**
     * 查找链表元素
     * @param  data 需要查找的元素
     * @return 元素所在位置，不存在则返回-1
     * */
    public int find(T data){
        if(this.isEmpty()) {
            System.out.println("链表为空！！！");
            return -1;
        }
        Node position = head;
        int index = 0;
        while(position!=null && position.data!=data) {
            index += 1;
            position = position.next;
        }
        if(position==null){
            System.out.println("未找到该元素");
            return -1;
        }else {
            return index;
        }
    }


    //获取节点内值
    public Object get(int index){
        Object s = getNode(index).data;
        if(s==null) return ""; else return s;
    }
    /**
     * 查找链表元素
     * @param  index 查找指定位置元素
     * @return 位置所在元素，不存在则返回null
     * */
    public Node getNode(int index){
        if(index<length && index>=0) {
            int i = 0;
            Node point = head;
            while (i<index) {
                i++;
                point = point.next;
            }
            return point;
        }else {
            System.out.println("链表越界！！！该链表总长度："+length);
            return null;
        }

    }

    /**链表为空时，添加元素
     * @param data 需要添加的元素
     * */
    public void insertFirstItem(T data){
        if(isEmpty()){
            Node newNode = new Node<>(data);
            head = newNode;
            tail = newNode;
            length++;
        }
    }
    /**链表末尾新增一个节点
     * @param data 需要添加的元素
     * */
    public void insertTail(T data){
        if(isEmpty()) insertFirstItem(data);
        else {
            Node newNode = new Node<>(data);
            tail.next = newNode;
            tail = newNode;
            length++;
        }
    }

    /**在链表头新增一个节点
     * @param data 需要添加的元素
     */
    public void insertHead(T data){
        if(isEmpty()) insertFirstItem(data);
        else {
            Node newNode = new Node<>(data);
            tail.next = newNode;
            tail = newNode;
            length++;
        }
    }

    /**向链表增加一个元素
     * @param data 需要添加的元素
     * @param locate 需要添加在哪个位置
     * */
    public void insert(T data,int locate){
        Node newNode = new Node<>(data);
        if(isEmpty()) insertFirstItem(data);
        else{
            int loc = (locate<length-1)?locate:length-1;
            if(loc==0) insertHead(data);
            else if(loc == length-1) insertTail(data);
            else{
                Node point = getNode(loc);
                newNode.next = point.next;
                point.next = newNode;
                length++;
            }
        }
    }

    /**删除指定位置元素
     * @param index 删除位置
     * */
    public void delete(int index){
        if(isEmpty()) {
            System.out.println("链表为空！！！");
            return;
        }else{
            if(index==0) head = head.next;
            else if(index>=length) {
                System.out.println("删除失败！下标越界，链表总长度："+length+"，最大可接收index："+(length-1));
                return;
            } else{
                Node s = getNode(index-1);
                s.next = s.next.next;
            }
        }

    }

    /**打印链表
     * */
    public void printList(){
        Node point = head;
        while (point!=null){
            System.out.print(point.data+" ");
            point = point.next;
        }
        System.out.println();
        System.out.println();
    }
}

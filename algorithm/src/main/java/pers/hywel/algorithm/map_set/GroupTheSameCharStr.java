package pers.hywel.algorithm.map_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

class Node{
    String str;
    Node next;
    Node child;

    Node(String str){
        this.str = str;
        this.next = null;
        this.child = null;
    }
}

/**
 * 详见hashtable.DesignTheKey中的对相同字符的字符串分组题目
 *
 */
public class GroupTheSameCharStr {
    public static void main(String[] args) {
        String[] test = new String[]{"eat","tea","tan","ate","nat","bat"};
        //String result = getTheSameChar(test);
        //System.out.println(result);
    }

    /**
     * 对字符相同，顺序不同的字符串数组进行分组
     *
     * @param strs 需要分组的字符串数组
     * @return 分组后的一个字符串
     */
    public static List<List<String>> getTheSameChar(String[] strs){
        //异常检测
        if(null==strs||strs.length==0){return null;}
        HashMap<Integer,Node> map = new HashMap<>();
        for(String str:strs){
            //求出每个字符串的ASCII码值，加入到对应的Hash表中
            Node node = new Node(str);
            int hash = stringToAscii(str);
            if(null == map.get(hash)){map.put(hash,node);}
            else {
                //对具有相同Ascii码的字符串进行比较，相同则加入子节点，不同则链接在该链表后边
                Node cur = map.get(hash);
                while (null!=cur){
                    if(isSame(cur.str,str)){
                        while (null!=cur.child){
                            cur = cur.child;
                        }
                        cur.child = node;
                        break;
                    }else {
                        //遍历到链表末端，直接加入到链表最后
                        if(null==cur.next){
                            cur.next = node;
                            break;
                        }
                        cur = cur.next;
                    }
                }
            }
        }

        //遍历整个map，同一个node的所有child节点则是相同字符串。node为不同的分组
        List<List<String>> result = new ArrayList<>();
        for(Node node:map.values()) {
            Node sameHashNode = node;
            while (null != sameHashNode) {
                List<String> group = new ArrayList<>();
                group.add(sameHashNode.str);

                //获取相同的字符串
                Node cur = sameHashNode.child;
                while (null != cur) {
                    group.add(cur.str);
                    cur = cur.child;
                }
                result.add(group);
                sameHashNode = sameHashNode.next;
            }
        }

        return result;
    }

    /**
     * String转换为一个Ascii码和
     * @param str
     * @return
     */
    public static int stringToAscii(String str){
        if(null==str||str.length()==0){return 0;}
        char[] array = str.toCharArray();
        int result = 0;
        for(char c:array){
            result+=(int)c;
        }
        return result;
    }

    /**
     * 判断两个字符串是否相同
     * 转换为字符数组排序后比较
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isSame(String str1,String str2){
        if(str1.length()!=str2.length()){return false;}
        char[] strChar1 = str1.toCharArray();
        char[] strChar2 = str2.toCharArray();
        Arrays.sort(strChar1);
        Arrays.sort(strChar2);
        for(int i=0;i<strChar1.length;i++){
            if(strChar1[i]!=strChar2[i]){return false;}
        }
        return true;
    }
}

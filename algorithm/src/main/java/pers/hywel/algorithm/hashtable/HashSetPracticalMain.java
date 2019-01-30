package pers.hywel.algorithm.hashtable;

import java.util.*;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * HashSet 相关练习题
 *
 * 以下代码实例：
 * 1. 判断数组中是否存在重复元素（containsDuplicate）
 * 2. 给定一个数组，判断数组中是否存在个数为奇数的单身元素（singleNumber）
 * 3. 给定两个整数数组，求两数组中相同元素（intersection）
 * 4. 给定两个字符串，求两个字符串中相同元素个数（leetcode题目：Jewels and Stones）（numJewelsInStones）
 * 5. 求happy数，每位上的数的平方和最终等于0（leetcode题目：Happy Number）（isHappy）
 *
 * @author hywel
 */
public class HashSetPracticalMain {
    public static void main(String[] args) {
        int n = 9/10;
        System.out.println(n);
    }

    /**
     * 判断是否存在重复元素
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet hashSet = new HashSet();
        for(int num:nums){
            if(hashSet.contains(num)){return true;}
            hashSet.add(num);
        }
        return false;
    }

    /**
     * 返回单身元素，没有则返回-1
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        HashSet hashSet = new HashSet();
        for(int num:nums){
            if(hashSet.contains(num)){
                hashSet.remove(num);
            }else {
                hashSet.add(num);
            }
        }
        if (hashSet.iterator().hasNext()){
            return (int)hashSet.iterator().next();
        }else {
            return -1;
        }
    }

    /**
     * 判断两个数组中的相同元素
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet = new HashSet<>();
        for(int num:nums1){
            hashSet.add(num);
        }

        HashSet<Integer> resultSet = new HashSet<>();
        for(int num:nums2){
            if(hashSet.contains(num)){resultSet.add(num);}
        }
        int[] result = new int[resultSet.size()];
        int i = 0;
        for(Integer j:hashSet){
            result[i] = j;
            i++;
        }
        return result;
    }

    /**
     * 给定两个字符串，求两个字符串中相同字符出现次数
     * （leetcode题目：Jewels and Stones）
     */
    public int numJewelsInStones(String J, String S) {
        if(null==J||null==S){return 0;}
        char[] charJ = J.toCharArray();
        Set<Character> setJ = new HashSet<>();
        for(int i=0;i<charJ.length;i++){
            setJ.add(charJ[i]);
        }
        char[] charS = S.toCharArray();
        int jewelNums=0;
        for(int i=0;i<charS.length;i++){
            if(setJ.contains(charS[i])){
                jewelNums++;
            }
        }
        return jewelNums;
    }

    /**
     * happy数，每次算各位上的平方和，如果最终结果为1，则是happy数。如果进入循环结果不为1，则不是
     *
     * Input: 19
     * Output: true
     * Explanation:
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     *
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        int sum = n;
        while (true){
            int digit = sum;
            sum = 0;
            while (digit>0){
                int remainder = digit%10;
                sum += remainder*remainder;
                digit = digit/10;
            }
            if(1==sum){return true;}
            if(hashSet.contains(sum)){return false;}
            else { hashSet.add(sum); }
        }
    }
}

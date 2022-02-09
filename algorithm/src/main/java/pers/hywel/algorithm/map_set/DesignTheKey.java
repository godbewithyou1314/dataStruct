package pers.hywel.algorithm.map_set;

import java.util.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 设计合适的key
 *
 * 很多时候我们都需要自己设计一个合适的key去分组，而不是仅仅用HashCode函数的方式。
 * 一个合适的hash函数和key键设计能够带来很大的性能提升和数据均衡
 *
 * key总结，常用方法：
 * 1.对乱序的string或者array，可以考虑排序后作为key（如下实例1）
 * 2.使用距离第一个值的offset作为key，选择一个统一的坐标原点，例如（x1,x2,x3）==>(0，x1-0,x2-0,x3-0)
 * 3.在树中，可以使用treeNode作为key，不过在大多数场景中，可以使用子树为key（如下实例3，寻找相同子树）
 * 4.矩阵中，可以使用横坐标和纵坐标构建唯一key（如实例2判断行列时）
 * 5.数独中，可以使用横坐标和纵坐标来将数独划分块。（如实例2，通过除法和取余，将坐标映射到块坐标）
 *      0 ,1，2
 *      3，4，5
 *      6，7，8
 * 6.矩阵中，有时可以使用对角线做key。例如横纵坐标的：i+j ,i-j
 *
 * 以下实例代码解决问题：
 * 1. 将字符串数组里相同字符的字符串归组（groupAnagrams）
 * 2. 验证数独是否可用（isValidSudoku）
 * 3. 寻找相同子树（findDuplicateSubtrees）
 */
public class DesignTheKey {

    /**
     * 给一个字符串数组，对其中的字符串进行分组。相同字母的字符串归为一组（阿里面试题）
     *
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     *
     * @param strs
     * @return
     *
     * 一.LeetCode大神解法，通过素数相乘来确定key值
     * 二.自己实现，查看GroupTheSameCharStr类
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        //最多10609个z
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        List<List<String>> res = new ArrayList<>();
        //存储<key,该key子链表在大链表中的位置>
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(String str:strs){
            //key：字符串转换为对应的素数的乘积
            int key=1;
            for(char c:str.toCharArray()){
                key *= prime[c-'a'];
            }
            List<String> group;
            if(hashMap.containsKey(key)){
                group = res.get(hashMap.get(key));
            }else {
                group = new ArrayList<>();
                res.add(group);
                hashMap.put(key,res.size()-1);
            }
            group.add(str);
        }
        return res;
    }

    /**
     * 验证9*9的数独是否可用
     * 1. 每行，每列包含1-9的不重复数字，可以包含重复'.'（包含数字1-9，或者'.')
     * 2. 每个3*3的子数独包含有1-9的不重复数字,可以包含重复'.'(包含数字1-9，或者'.')
     *
     * 如：
     * Input:
     * [
     *   ["5","3",".",".","7",".",".",".","."],
     *   ["6",".",".","1","9","5",".",".","."],
     *   [".","9","8",".",".",".",".","6","."],
     *   ["8",".",".",".","6",".",".",".","3"],
     *   ["4",".",".","8",".","3",".",".","1"],
     *   ["7",".",".",".","2",".",".",".","6"],
     *   [".","6",".",".",".",".","2","8","."],
     *   [".",".",".","4","1","9",".",".","5"],
     *   [".",".",".",".","8",".",".","7","9"]
     * ]
     * Output: true
     *
     * 例二：
     * Input:
     * [
     *   ["8","3",".",".","7",".",".",".","."],
     *   ["6",".",".","1","9","5",".",".","."],
     *   [".","9","8",".",".",".",".","6","."],
     *   ["8",".",".",".","6",".",".",".","3"],
     *   ["4",".",".","8",".","3",".",".","1"],
     *   ["7",".",".",".","2",".",".",".","6"],
     *   [".","6",".",".",".",".","2","8","."],
     *   [".",".",".","4","1","9",".",".","5"],
     *   [".",".",".",".","8",".",".","7","9"]
     * ]
     * Output: false
     * Explanation: Same as Example 1, except with the 5 in the top left corner being
     *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
     *
     * 解决办法：
     * 对每个数字所在位置，编码成字符串，扔到Set里边进行匹配
     *
     * '4' in row 7 is encoded as "(4)7".
     * '4' in column 7 is encoded as "7(4)".
     * '4' in the top-right block is encoded as "0(4)2".
     * 如果添加失败，则说明存在重复
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Set set = new HashSet();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if('.'!=board[i][j]){
                    String encodeStr = "("+board[i][j]+")";
                    if(!set.add(encodeStr+i)||!set.add(j+encodeStr)||!set.add(i/3+encodeStr+j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //验证数独是否可用:第二种实现方式
    public boolean isValidSudoku1(char[][] board) {
        for(int i = 0; i<9; i++){
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                {return false;}
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                { return false;}
                int rowIndex = 3*(i/3);
                int colIndex = 3*(i%3);
                if(board[rowIndex + j/3][colIndex + j%3]!='.' && !cube.add(board[rowIndex + j/3][colIndex + j%3]))
                { return false;}
            }
        }
        return true;
    }

    /**
     * 查找相同的子树
     * Two trees are duplicate if they have the same structure with same node values.

     Example 1:

                1
             /   \
            2    3
          /    /  \
        4    2    4
      /
     4
     The following are two duplicate subtrees:

         2
       /
     4
     and
     4

     Therefore, you need to return above trees' root in the form of a list.
     */
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> duplicatedSubTrees = new LinkedList<TreeNode>();
        Map<String,Integer> treeNodeStrMap = new HashMap<>();
//        Set<String> treeNodeStrSet = new HashSet<>();
        recursionTree(root,treeNodeStrMap,duplicatedSubTrees);
        return duplicatedSubTrees;
    }

    private String recursionTree(TreeNode root,Map<String,Integer> treeNodeStrMap,List<TreeNode> duplicatedSubTrees){
        if(null == root){
            return "#";
        }else{
            String subTreeStr = root.val+"->"
                    +recursionTree(root.left,treeNodeStrMap,duplicatedSubTrees)+"|"
                    +recursionTree(root.right,treeNodeStrMap,duplicatedSubTrees);
            if(treeNodeStrMap.containsKey(subTreeStr)){
                int count = treeNodeStrMap.get(subTreeStr);
                if(count==1) {
                    duplicatedSubTrees.add(root);
                    treeNodeStrMap.put(subTreeStr,count+1);
                }
            }
            else {treeNodeStrMap.put(subTreeStr,1);}
            return subTreeStr;
        }
    }
}

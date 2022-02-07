
package pers.hywel.algorithm.dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * Generate Parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * @author RobertZhangwei
 */
public class GenerateParentheses {

    /**
     * backtracking solution 回溯解法
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max){

        if(str.length() == max*2){
            list.add(str);
            return;
        }

        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }

    /**
     * 暴力解法
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesisBrute(int n) {
        if (n == 1) {
            return Collections.singletonList("()");
        }
        List<String> parentheses = generateParenthesisBrute(n-1);
        Set<String> results = new HashSet<>();
        for (String str : parentheses) {
            results.add("()" + str);
            for (int i = 0; i < str.length(); i++) {
                for (int j = i + 1; j < str.length(); j++) {
                    String s = str.substring(0, i) + '(' + str.substring(i, j) + ')' + str.substring(j);
                    results.add(s);
                    while (j< str.length() && str.charAt(j) == ')')
                        j++;
                }
                while (i < str.length() && str.charAt(i) == '(')
                    i++;
            }
        }
        return new ArrayList<>(results);
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        GenerateParentheses testClass = new GenerateParentheses();
        List<String> result = testClass.generateParenthesis(3);
        System.out.println(result.toString());
        Long endTime = System.currentTimeMillis();
        System.out.println("运行耗时：" + (endTime - startTime) + "ms");
    }
}

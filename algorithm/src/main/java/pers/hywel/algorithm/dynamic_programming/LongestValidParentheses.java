
package pers.hywel.algorithm.dynamic_programming;

import static java.lang.Math.max;

import java.util.Stack;

/**
 * Description:
 * 32. Longest Valid Parentheses
 * Hard
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 * <p>
 * Example 1:
 * <p>
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 * <p>
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * @author RobertZhang
 * Created on 2020-04-29
 */
public class LongestValidParentheses {

    // dp实现
    public int longestValidParenthesesByDP(String s) {
        // 以i结尾的最大有效括号长度
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                // ...() ==> dp[i] = dp[i-2] + 2
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 > 0 ? dp[i - 2] + 2 : 2;
                }
                // （...)) ==> dp[i] = dp[i - 1] + 2 + i - dp[i - 1] - 2
                else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = i - dp[i - 1] - 2 < 0 ? dp[i - 1] + 2 : dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                }
            }
            res = max(res, dp[i]);
        }
        return res;
    }

    // 啰嗦版
    public int longestValidParenthesesByDP2(String s) {
        // 以i结尾的最大有效括号长度
        int[] dp = new int[s.length() + 1];
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            // '('结尾，肯定够不成有效括号，直接赋值0
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            }
            // ')'结尾
            else if (s.charAt(i) == ')') {
                // ...() ==> dp[i - 2] + 2
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 > 0 ? dp[i - 2] + 2 : 2;
                }
                // ...)) ==> i - dp[i - 1] - 1 为末尾')'对应的'('下标位置
                else if (s.charAt(i - 1) == ')') {
                    // (...)) 对应位置'('匹配上： dp[i] = dp[i - 1] + 2 + 对应'('前的匹配数
                    if (i - dp[i - 1] - 1 > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                    } else {
                        // 对应位置没匹配上
                        dp[i] = 0;
                    }
                }
            }
            res = max(res, dp[i]);
        }
        return res;
    }

    // 栈实现
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int res = 0, start = 0, n = s.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else if (s.charAt(i) == ')') {
                if (st.empty()) {
                    start = i + 1;
                } else {
                    st.pop();
                    res = st.empty() ? max(res, i - start + 1) : max(res, i - st.peek());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestValidParentheses testClass = new LongestValidParentheses();
        String str = "())(()";
        int result = testClass.longestValidParenthesesByDP(str);
        System.out.println(result);
    }
}

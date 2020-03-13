/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.list.stack;

import java.util.Stack;

/**
 * Description:
 * 验证括号
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 *
 * @author zhangwei111
 *

 */
public class ValidParentheses {

    /**
     * method by discuss
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    /**
     * method 2
     * @param s
     * @return
     */
    public boolean isValidMyself(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i <s.length(); i++) {
            if (stack.isEmpty())
                stack.push(s.charAt(i));
            else
                switch (s.charAt(i)) {
                    case ')':
                        if (stack.peek() == '(')
                            stack.pop();
                        else
                            stack.push(s.charAt(i));
                        break;
                    case ']':
                        if (stack.peek() == '[')
                            stack.pop();
                        else
                            stack.push(s.charAt(i));
                        break;
                    case '}':
                        if (stack.peek() == '{')
                            stack.pop();
                        else
                            stack.push(s.charAt(i));
                        break;

                    default:
                        stack.push(s.charAt(i));

                }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses testClass = new ValidParentheses();
        System.out.println(testClass.isValid("{{)}"));
    }
}

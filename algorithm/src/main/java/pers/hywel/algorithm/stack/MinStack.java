/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.stack;

import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * 最小栈,常数时间获取最小值
 *   Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * @author RobertZhang
 *

 */
public class MinStack {
    private List<Integer> stack;
    private int min = (1 << 31) - 1;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        if (x < min)
            min = x;
        stack.add(0, x);
    }

    public void pop() {
        int delete = stack.remove(0);
        // rebalance
        if (delete == min) {
            min = (1 << 31) - 1;
            for (int i : stack)
                if (i < min)
                    min = i;
        }
    }

    public int top() {
        if (null != stack && !stack.isEmpty())
            return stack.get(0);
        return 0;
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}

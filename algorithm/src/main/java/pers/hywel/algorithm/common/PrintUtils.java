package pers.hywel.algorithm.common;

import pers.hywel.algorithm.list.common.ListNode;

import java.lang.reflect.Method;
import java.util.List;

public class PrintUtils {

    public static void printWithExecuteTime(int totalCount, Method method, Object obj, Object... args) {

        Long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        int count = 0;
        while (count++ < totalCount) {
            if (count == 1) {
                try {
                    Object result = method.invoke(obj, args);
                    System.out.println(result.toString());
                } catch (Exception e) {
                    System.out.println("执行错误：" + e.getMessage());
                }
            }
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println("总耗时(秒)：" + (endTime - startTime) / 1000);
    }

    public static void printArray(int[] array) {
        for (int j = 0; j < array.length; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println();
    }

    public static void printMatrix(int[][] arrays) {
        for (int[] array : arrays) {
            for (int j = 0; j < arrays[0].length; j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println();
        }
    }

    public static void printList(ListNode head) {
        while (head != null && head.next != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println(head == null ? "" : head.val);
    }

    public static void printList(List<?> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                List<?> subList = (List<?>) obj;
                printList(subList);
            } else {
                sb.append(obj.toString());
                if (i != list.size() - 1) sb.append("-->");
            }
        }
        System.out.println(sb);
    }
}

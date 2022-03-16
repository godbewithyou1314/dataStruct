package pers.hywel.algorithm.breadth_first_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 207. Course Schedule【Medium】
 * <p>
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0,
 * and to take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {
    /**
     * BFS实现
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        int[] indegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0)
                indegree[ready]++; //duplicate case
            matrix[pre][ready] = 1;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }


    /**
     * 自己实现：DFS
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishByMe(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length < 2) return true;
        Set<Integer> hasLearned = new HashSet<>();
        // 每个课程的依赖链路
        Map<Integer, List<Integer>> everyCourseLineage = new HashMap<>();
        for (int[] course : prerequisites) {
            everyCourseLineage.computeIfAbsent(course[0], k -> new ArrayList<>()).add(course[1]);
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int curCourse = prerequisites[i][0];
            // 该门课程已经学过，或者无依赖课程。直接跳过
            if (hasLearned.contains(curCourse) || !everyCourseLineage.containsKey(curCourse)) continue;

            // 未学过, 处理依赖课程
            Set<Integer> tempSet = new HashSet<>();
            tempSet.add(curCourse);
            // 依赖处理成功，添加到已学列表
            if (dealDependency(everyCourseLineage.get(curCourse), everyCourseLineage, hasLearned, tempSet))
                hasLearned.add(curCourse);
                // 依赖处理失败，返回false
            else return false;
        }
        return true;
    }

    private boolean dealDependency(List<Integer> dependencyList, Map<Integer, List<Integer>> everyCourseLineage,
                                   Set<Integer> hasLearned, Set<Integer> dealingSet) {
        if (dependencyList == null || dependencyList.isEmpty()) return true;
        // 前提课程如果已经学习，或者没有依赖课程，直接学习
        for (Integer course : dependencyList) {
            // 循环依赖
            if (dealingSet.contains(course)) return false;

            // 处理当前课程
            // 已经学习过，或者没有依赖课程
            if (hasLearned.contains(course) || !everyCourseLineage.containsKey(course)) {
                hasLearned.add(course);
            } else {
                //处理当前课程的依赖关系
                dealingSet.add(course);
                boolean dependencyFlag = dealDependency(everyCourseLineage.get(course), everyCourseLineage, hasLearned, dealingSet);
                if (dependencyFlag) hasLearned.addAll(dealingSet);
                else return false;
                dealingSet.remove(course);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule testObj = new CourseSchedule();
        int[][] prerequisites = new int[][]{{0, 1}, {0, 2}, {1, 2}};
        int numCourses = 3;
        System.out.println(testObj.canFinish(numCourses, prerequisites));
    }
}

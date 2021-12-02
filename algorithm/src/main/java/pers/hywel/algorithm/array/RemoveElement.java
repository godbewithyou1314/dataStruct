package pers.hywel.algorithm.array;

/**
 * Description:
 * Remove Element (移除指定element)
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1)
 * extra memory.
 *
 * @author RobertZhang
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[length] = nums[i];
            if (nums[i] != val)
                length++;
        }
        return length;
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();

        RemoveElement testClass = new RemoveElement();
        int[] intArrays = {1, 2, 3, 4, 5};
        int result = testClass.removeElement(intArrays, 5);
        System.out.println(result);
        for (int i : intArrays) {
            System.out.print(i + " ");
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("运行耗时：" + (endTime - startTime) + "ms");
    }
}

package pers.hywel.algorithm.doublepoint;

/**
 * Description:
 * 75. 颜色分类[medium]
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
   此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 示例 1：

 输入：nums = [2,0,2,1,1,0]
 输出：[0,0,1,1,2,2]
 示例 2：

 输入：nums = [2,0,1]
 输出：[0,1,2]
 示例 3：

 输入：nums = [0]
 输出：[0]
 示例 4：

 输入：nums = [1]
 输出：[1]
 *
 * @author RobertZhang
 * Created on 2021/2/1 8:04 下午
 */
public class SortColors {
    // 双指针：把0放到最前端，把2放到最后端。分别使用min来标记0的末尾位置，max标记2的起始位置，用作交换
    // 双指针： 一个指向2前一个，一个指向0后一个，遇到2就和2指针交换，遇到0就和0指针交换。1则不动
    public void sortColors(int[] nums) {
        int min = 0;
        int max = nums.length - 1;
        for (int cur = 0 ; cur < nums.length;) {
            if (cur > max) {
                return;
            }
            switch (nums[cur]) {
                case 0:
                    if (cur == min) {
                        cur++;
                    } else {
                        swap(cur, min, nums);
                    }
                    min++;
                    break;
                case 1:
                    cur++;
                    break;
                case 2:
                    swap(cur, max, nums);
                    max--;
            }
        }
    }

    private void swap(int a, int b, int[] nums) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        SortColors test = new SortColors();
        int[] colors = new int[]{2, 0, 1};
        test.sortColors(colors);
        for (int i : colors)
            System.out.print(i + " ");
    }

}

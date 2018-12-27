package pers.hywel.algorithm.dynamicprogramming;

/**
@author hywel
*/
public class MaxSubSequenceSum {
    public static void main(String[] args) {
        int[] a = new int[]{2,4,0,-5,3};
        System.out.println(maxSubSequence(a));
    }

    /**
     * 最大序列和
     * 求一个给定数组中最大连续和
     *
     * Solve:
     * 动态规划，状态转移方程
     * MaxSum[i] = Max{ MaxSum[i-1] + A[i], A[i]};MaxSum[i]表示包含A[i]并以A[i]结尾的最大和
     * @param array
     * @return
     */
    public static int maxSubSequence(int[] array) {

        int length = array.length;
        int[] ContainNEleSeqSum = new int[length];
        ContainNEleSeqSum[0] = array[0];
        for (int i = 1; i < length; i++) {
            ContainNEleSeqSum[i] = Math.max(ContainNEleSeqSum[i - 1] + array[i], array[i]);
            System.out.printf("包含第%d个元素的最大序列和为："+ContainNEleSeqSum[i]+"%n",i);
        }
        //找到MaxSum中的最大值
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < ContainNEleSeqSum.length; i++) {
            if (ContainNEleSeqSum[i] > maxSum) {
                maxSum = ContainNEleSeqSum[i];
            }
        }
        return maxSum;
    }

    /**
     * 用m球测n层楼的临界值问题：
     * m球n层楼，b(m,n) = min{ max{ b(m-1,k-1)+1, b(m,n-k)+1 } }，其中b（1，n)=n，b(2,1)=1,1<=k<=n
     * 时间复杂度为O(n^3)
     *
     * tips：
     * 还有一个简单的解题方法：
     * 以等差数列递减扔蛋，例如：
     * 2球100层楼问题，先从15楼扔，再从29楼，42楼，，，这样（依此15，14，13，，，）
     * 取巧解法：
     * 等差数列和>=n,那就是最佳值
     * (k+1)K/2>=100 , k为正整数  ===> k=14 所以最优为扔14次
     *
     * @param eggs 总的蛋数
     * @param floors 总的楼层数
     * @return
     */
    public static int findEgg(int eggs,int floors){
        if(floors==0){return 0;}
        if(eggs==1){return floors;}
        int[] res = new int[floors];
        for (int k=1;k<=floors;k++) {
            //取一球从随机k（0<=k<=floors）层楼扔下,结果只有两种
            //第一：球没碎，问题转移为子问题findEgg（eggs，floors-k）
            //第二：球碎了，问题转移为子问题findEgg（eggs-1，k-1）
            //取最大值，即为从k层楼扔下时的最坏次数
            res[k-1] = Math.max(findEgg(eggs, floors - k) + 1, findEgg(eggs - 1, k-1) + 1);
        }
        //取全部k楼对应的结果里的最小次数即是最优解
        int min = res[0];
        for(int resElem:res){
            if(resElem<min){min = resElem;}
        }
        return min;
    }
}

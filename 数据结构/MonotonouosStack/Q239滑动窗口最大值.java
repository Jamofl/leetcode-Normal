/*
239. 滑动窗口最大值
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。
示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
示例 2：

输入：nums = [1], k = 1
输出：[1]
示例 3：

输入：nums = [1,-1], k = 1
输出：[1,-1]
示例 4：

输入：nums = [9,11], k = 2
输出：[11]
示例 5：

输入：nums = [4,-2], k = 2
输出：[4]

提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */
package 数据结构.MonotonouosStack;

import java.util.Arrays;
import java.util.LinkedList;

public class Q239滑动窗口最大值 {
    // Solution 1: 暴力解法，O(N * k)
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int firstNum = 0;
//        int n = nums.length;
//        int max = Integer.MIN_VALUE;
//        int[] ans = new int[n - k + 1];
//
//        LinkedList<Integer> subList = new LinkedList<>();
//
//        for(int i = 0; i < k; i ++)
//            subList.add(nums[i]);
//        for(int i : subList){
//            if (i > max)
//                max = i;
//        }
//        ans[0] = max;
//        firstNum = subList.getFirst();
//
//        for (int i = 1 ; i <= n - k; i ++){
//            subList.removeFirst();
//            subList.addLast(nums[i + k - 1]);
//            if (firstNum != max){
//                if (subList.getLast() > max){
//                    max = subList.getLast();
//                }
//            }
//            else{
//                if (subList.getLast() > firstNum)
//                    max = subList.getLast();
//                else{
//                    max = Integer.MIN_VALUE;
//                    for(int j : subList){
//                        if (j > max)
//                            max = j;
//                    }
//                }
//            }
//            firstNum = subList.getFirst();
//            ans[i] = max;
//        }
//        return ans;
//    }

    // Solution 2: 单调队列.
    // 维护一个长度为L ~ R的单调队列。每当L所在的索引等于栈最左边的索引时，弹出最左边元素。
    // 栈中的元素单调递减，每次有新元素加入时，和栈中最右边的元素比较，若最右边元素小于新加入元素，则弹出，直到最右边元素大于新加入元素为止。
    //
    // 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
    // 输出: [3,3,5,5,6,7]
    //
    // 解释过程中队列中都是具体的值，方便理解，具体见代码。
    // 初始状态：L=R=0,队列:{}
    // i=0,nums[0]=1。队列为空,直接加入。队列：{1}
    // i=1,nums[1]=3。队尾值为1，3>1，弹出队尾值，加入3。队列：{3}
    // i=2,nums[2]=-1。队尾值为3，-1<3，直接加入。队列：{3,-1}。此时窗口已经形成，L=0,R=2，result=[3]
    // i=3,nums[3]=-3。队尾值为-1，-3<-1，直接加入。队列：{3,-1,-3}。队首3对应的下标为1，L=1,R=3，有效。result=[3,3]
    // i=4,nums[4]=5。队尾值为-3，5>-3，依次弹出后加入。队列：{5}。此时L=2,R=4，有效。result=[3,3,5]
    // i=5,nums[5]=3。队尾值为5，3<5，直接加入。队列：{5,3}。此时L=3,R=5，有效。result=[3,3,5,5]
    // i=6,nums[6]=6。队尾值为3，6>3，依次弹出后加入。队列：{6}。此时L=4,R=6，有效。result=[3,3,5,5,6]
    // i=7,nums[7]=7。队尾值为6，7>6，弹出队尾值后加入。队列：{7}。此时L=5,R=7，有效。result=[3,3,5,5,6,7]

    public int[] maxSlidingWindow(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        LinkedList<Integer> q = new LinkedList(); // queue里面存的是元素，不是下标

        while (r < k){
            offer(q, nums, nums[r]);
            r ++;
        }
        ans[l] = q.getFirst();

        while(r < n){
            if (nums[l] == q.getFirst())
                q.removeFirst();
            offer(q, nums, nums[r]);
            r ++;
            l ++;
            ans[l] = q.getFirst();
        }
        return ans;
    }

    private void offer(LinkedList<Integer> lst, int[] nums, int n){
        while(lst.size() != 0 && lst.getLast() < n){
            lst.removeLast();
        }
        lst.add(n);
    }

    public static void main(String[] args){
        Q239滑动窗口最大值 q = new Q239滑动窗口最大值();
        int[] t = new int[]{1,3,-1,-3,5,3,6,7};
        int[] re = q.maxSlidingWindow(t, 3);
        System.out.println(Arrays.toString(re));
    }
}

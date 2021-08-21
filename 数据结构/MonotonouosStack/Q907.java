/*
907. 子数组的最小值之和
给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。

由于答案可能很大，因此返回答案模 10^9 + 7。


示例：

输入：[3,1,2,4]
输出：17
解释：
子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。


提示：

1 <= A <= 30000
1 <= A[i] <= 30000
 */
package 数据结构.MonotonouosStack;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class Q907 {

    // Solution 1: 按照len从1 2 3 ...递增，通过单调栈枚举每个子数组中的最小元素，会超时。
    // 复杂度为 O(N2)
//    public int sumSubarrayMins(int[] arr) {
//        LinkedList<Integer> q = new LinkedList();
//        LinkedList<Integer> ans = new LinkedList();
//        int sumMin = 0;
//        int maxSubLen = arr.length;
//        int MOD = 1_000_000_007;
//
//        // base case, len == 1;
//        for(int i : arr){
//            ans.add(i);
//            // sumMin += i;
//            // sumMin %= MOD;
//        }
//
//        // 2 <= len <= n
//        for(int k = 2; k <= maxSubLen; k ++){
//            q.clear();
//            int r = 0; int l = 0;
//            while(r < k){
//                offer(q, arr, r);
//                r ++;
//            }
//            ans.add(arr[q.getFirst()]);
//            // sumMin += arr[q.getFirst()];
//            // sumMin %= MOD;
//
//            while(r <= maxSubLen - 1){
//                if (arr[l] == arr[q.getFirst()])
//                    q.removeFirst();
//                offer(q, arr, r);
//                r ++;
//                l ++;
//                ans.add(arr[q.getFirst()]);
//                // sumMin += arr[q.getFirst()];
//                // sumMin %= MOD;
//            }
//        }
//        for(int i : ans){
//            sumMin += i;
//            if (sumMin > MOD)
//                sumMin %= MOD;
//        }
//        return sumMin;
//
//    }
//
//    private void offer(LinkedList<Integer> q, int[] arr, int k){
//        while (q.size() != 0 && arr[k] < arr[q.getLast()])
//            q.removeLast();
//        q.addLast(k);
//    }

    // Solution 2:
    // 对于每一个元素arr[i], 该元素左边第一个比它小的元素下标为j（即有 j - i - 1个元素大于arr[i]），右边第一个比它小的元素下标为k
    // 则以元素arr[i]为最小值的子数组共有(j - i) * (k - i)个
    // 找到下一个更大/更小元素的方法为： 使用单调栈。

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> q = new LinkedList<>();
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        long ans = 0l;
        int MOD = 1_000_000_007;

        for(int i = 0; i < n; i ++){
            while (q.size() != 0 && arr[q.getLast()] > arr[i])
                q.removeLast();
            if (q.size() == 0)
                left[i] = -1;
            else
                left[i] = q.getLast();
            q.addLast(i);
        }

        q.clear();
        for(int i = n - 1; i >= 0; i --){
            while(q.size() != 0 && arr[q.getFirst()] >= arr[i])
                q.removeFirst();
            if (q.size() == 0)
                right[i] = n;
            else
                right[i] = q.getFirst();
            q.addFirst(i);
        }

        for(int i = 0; i < n; i++){
            ans += (long)arr[i] * (right[i] - i) * (i - left[i]); // 这里会溢出，所以要先将arr[i]转换为long
            ans = ans % MOD;
        }
        return (int)ans;
    }


    public static void main(String[] args){
//        Q907 q = new Q907();
//        int[] t = new int[] {11,81,94,43,3};
//        int r = q.sumSubarrayMins(t);
        int a = 2147483647;
        long res = 0l;
        long c = 1;
        res = 2147483647l + a;
        System.out.println(res);


    }
}

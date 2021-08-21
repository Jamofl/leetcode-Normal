/*
1539. 第 k 个缺失的正整数
给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
请你找到这个数组里第 k 个缺失的正整数。

示例 1：

输入：arr = [2,3,4,7,11], k = 5
输出：9
解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
示例 2：

输入：arr = [1,2,3,4], k = 2
输出：6
解释：缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。

提示：

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
对于所有 1 <= i < j <= arr.length 的 i 和 j 满足 arr[i] < arr[j]
 */
package Array数组;

public class Q1539第k个缺失的正整数 {

    // 方法1 笨蛋模拟法  O(N)
    // 模拟一个从1开始递增的数据流，使用count变量统计缺失的元素个数。若count == k,返回当前数字
    public int findKthPositive(int[] arr, int k) {
        int index = 0;
        int count = 0;
        int i = 1;
        while (true){
            if (index >= arr.length)
                count ++;
            else{
                if (i != arr[index])
                    count ++;
                else
                    index ++;
            }

            if (count == k)
                return i;
            i ++;
        }
    }

    // 方法2 二分查找法  O(LOG N)
    // arr = 2 3 4 7 11
    // p   = 1 1 1 3 6  (p表示到第i个元素为止缺失的元素的数量, p[i] = arr[i] - i - 1)
    // 我们可以发现p是关于i非严格单调递增的
    public int findKthPositiv2(int[] arr, int k) {
        if (arr[0] > k)
            return k;

        int l = 0;
        int r = arr.length - 1;
        while (l <= r){
            int middle = l + (r - l) / 2;
            if (arr[middle] - middle - 1 >= k)
                r = middle - 1;
            else
                l = middle + 1;
        }
        return arr[r] + k - (arr[r] - r - 1);
    }

}

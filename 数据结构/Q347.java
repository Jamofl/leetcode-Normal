/*
347. 前 K 个高频元素
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。



示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]


提示：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
你可以按任意顺序返回答案。
 */
package 数据结构;

import java.util.*;

public class Q347 {
    // NlogN
//    public int[] topKFrequent(int[] nums, int k) {
//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for(int num : nums){
//            if (map.getOrDefault(num, -1).equals(-1))
//                map.put(num, 1);
//            else
//                map.put(num, map.get(num) + 1);
//        }
//
//        PriorityQueue<Integer> q = new PriorityQueue<>((x, y) -> (int)map.get(y) - (int)map.get(x));
//        for(Integer key :  map.keySet())
//            q.add(key);
//        int[] ans = new int[k];
//        for(int i = 0; i < k; i ++)
//            ans[i] = q.poll();
//        return ans;
//    }

    // Nlogk
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> q = new PriorityQueue<>((x, y) -> map.get(x) - map.get(y));
        for(Integer key :  map.keySet())
        {
            if (q.size() < k)
                q.add(key);
            else if(q.size() == k)
            {
                if(map.get(key) < map.get(q.peek()))
                    continue;
                else{
                    q.poll();
                    q.add(key);
                }
            }
        }

        int[] ans = new int[k];
        for(int i = 0; i < k; i ++)
            ans[i] = q.poll();
        return ans;
    }
}

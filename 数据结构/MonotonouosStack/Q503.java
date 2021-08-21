/*
503. 下一个更大元素 II
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

示例 1:

输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数；
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
注意: 输入数组的长度不会超过 10000。
 */
package 数据结构.MonotonouosStack;

import com.sun.jdi.connect.spi.TransportService;

import java.util.*;
import java.util.stream.Collectors;

public class Q503 {


    // Solution 1:对于数组中的每一个元素，维护一个单调递减Dequeue，添加元素时与队列末尾元素比较，若大于末尾元素，将末尾元素弹出
    // 直到队列中无大于该元素的元素存在。则队列头即为当前索引元素的NEXT GREATER ELEMENT. 时间复杂度 O(N2)即，每个元素都无NGE存在
    public int[] nextGreaterElements(int[] nums) {
        LinkedList<Integer> q = new LinkedList();
        int n = nums.length;
        int[] ans = new int[n];
        boolean flag = false;
        for(int i = 0; i < n; i ++){
            flag = false;
            q.clear();
            q.addFirst(i);
            int j = (i + 1 == n) ? 0 : i + 1;
            while(j != i){
                offer(q, nums, j);
                if (nums[q.getFirst()] > nums[i]){
                    ans[i] = nums[q.getFirst()];
                    flag = true;
                    break;
                }
                j = (j + 1 == n) ? 0 : j + 1;
            }
            if (!flag)
                ans[i] = -1;
        }
        return ans;
    }

    private void offer(LinkedList<Integer> q, int[] nums, int k){
        while (q.size() != 0 && nums[k] > nums[q.getFirst()]){
            q.removeFirst();
        }
        q.addLast(k);
    }


    /*
    Solution 2: 维护一个单调递减栈 O（N)
    对于数组中的每一个元素A[i], 将其加入单调栈中，若栈首元素小于A[i], 弹出栈首元素，更新弹出栈首元素对应的数字的NGE为A[i],一直重复直到将A[i]加入到栈中
    由于数组可循环，则将数组整体遍历两遍即可，相当于复制了数组。
     */
    public int[] nextGreaterElements2(int[] nums) {
        Stack<Integer> stack = new Stack();
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);

        for(int i = 0; i < nums.length * 2; i ++){
            int j = i % nums.length;
            while (!stack.empty() && nums[stack.peek()] < nums[j]){
                ans[stack.pop()] = nums[j];
            }
            stack.push(j);
        }
        return ans;
    }

    public static void main(String[] args){
        Q503 q = new Q503();
        int[] re = q.nextGreaterElements(new int[]{1,2,3});
        System.out.println();
        int[] t = new int[]{1,3,3};
        List<Integer> lst = Arrays.stream(t).boxed().collect(Collectors.toList());

    }
}

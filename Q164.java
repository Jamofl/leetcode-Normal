import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 *
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 *
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class Q164 {

    public int R = 10; //0 ~ 9

    // return the ith digit of a num, counting from right to left
    private int getithDigit(int num, int k){
        if (num == 0)
            return 0;
        if (k == 0)
            return num % 10;  //获得个位
        else
            return getithDigit(num / 10, k - 1);
    }

    private int[] radixSort(int[] nums) {

        int[] counts;
        int[] startingIndex;
        int[] sortedArray = new int[nums.length];
        int startIndex;

        int k = 0;
        Integer[] numsInteger = Arrays.stream(nums).boxed().toArray(Integer[] :: new);
        int maxValue = (int)Collections.max(Arrays.asList(numsInteger));
        while(maxValue != 0) {
            counts = new int[R];
            startingIndex = new int[R];
            sortedArray = new int[nums.length];
            startIndex = 0;

            for (int i = 0; i < nums.length; i++)
                counts[getithDigit(nums[i], k)] += 1;

            for (int i = 0; i < R; i++) {
                startingIndex[i] = startIndex;
                startIndex += counts[i];
            }

            for (int i = 0; i < nums.length; i++) {
                int tempIndex = startingIndex[getithDigit(nums[i], k)];
                sortedArray[tempIndex] = nums[i];
                startingIndex[getithDigit(nums[i], k)] += 1;
            }
            k ++;
            maxValue = maxValue / 10;

            for(int i = 0 ; i < nums.length; i ++)
                nums[i] = sortedArray[i];

        }
        return sortedArray;


    }

    public int maximumGap(int[] nums) {
        if(nums.length <= 1)
            return 0;
        //Arrays.sort(nums);
        nums = radixSort(nums);
        int maxGap = 0;
        for(int i = 0; i < nums.length - 1; i ++){
            if (nums[i + 1] - nums[i] > maxGap)
                maxGap = nums[i + 1] - nums[i];
        }
        return maxGap;
    }

    public static void main(String[] args){
        Q164 q = new Q164();
        int[] re = q.radixSort(new int[]{123, 312, 132});
        System.out.println(4);
        List<Integer> lst = new LinkedList<>();
        lst.toArray();
    }



}

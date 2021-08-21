import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质：
 * counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * 示例：
 *
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 */
public class Q315 {
    int[] temp;
    int[] originalIndex;
    int[] newIndex;
    int[] result;

    public List<Integer> countSmaller(int[] nums) {
        if(nums.length == 0)
            return new LinkedList<>();

        temp = new int[nums.length];
        originalIndex = new int[nums.length];
        newIndex = new int[nums.length];
        result = new int[nums.length];
        for(int i = 0; i < nums.length; i ++){
            originalIndex[i] = i;
            newIndex[i] = i;
        }

        mergeSort(nums, 0, nums.length - 1);
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    private void mergeSort(int[] nums, int start, int end){
        if(end == start)
            return ;
        // 1.merge sort right and left part
        int middle = start + (end - start) / 2;
        mergeSort(nums, start, middle);
        mergeSort(nums, middle + 1, end);

        // 2.merge right and left parts together
        int i = start; int j = middle + 1; int index = start;
        while(i <= middle && j <= end){
            if(nums[i] <= nums[j]){
                newIndex[index] = originalIndex[i];
                result[newIndex[index]] += j - (middle + 1);
                temp[index ++] = nums[i ++];
            }
            else{
                newIndex[index] = originalIndex[j];
                temp[index ++] = nums[j ++];
            }
        }
        while(i <= middle) {
            newIndex[index] = originalIndex[i];
            result[newIndex[index]] += j - (middle + 1);
            temp[index++] = nums[i++];
        }
        while(j <= end){
            newIndex[index] = originalIndex[j];
            temp[index ++] = nums[j ++];
        }

        // 3.copy sorted array to nums
        for(int k = start; k <= end; k ++){
            nums[k] = temp[k];
            originalIndex[k] = newIndex[k];
        }
    }

    public static void main(String[] args){
        Q315 q = new Q315();
        List r = q.countSmaller(new int[]{7,5,6,4});
        System.out.println(r);
    }
}

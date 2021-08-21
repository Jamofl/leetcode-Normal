import java.nio.channels.WritableByteChannel;

/**
 *
 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

 示例 1:

 输入: [1,3,4,2,2]
 输出: 2
 示例 2:

 输入: [3,1,3,4,2]
 输出: 3
 说明：

 不能更改原数组（假设数组是只读的）。
 只能使用额外的 O(1) 的空间。
 时间复杂度小于 O(n2) 。
 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */

public class Q287 {
    public int findDuplicate(int[] nums) {
        for(int i = 0; i < nums.length - 1; i ++){
            for(int j = i + 1; j < nums.length; j ++){
                if (nums[i] == nums[j])
                    return nums[i];
            }
        }
        return -1;
    }

    public int quickFind(int[] nums, int left, int right){
        if(left > right)
            return left;
        int middle = left + (right - left) / 2;
        int count = 0;
        for(int i = 0; i < nums.length ; i++){
            if (nums[i] <= middle)
                count ++;
        }
        if(count <= middle)
            return quickFind(nums, middle + 1, right);
        else
            return quickFind(nums, left, middle - 1);
    }

    public static void main(String[] args) {
        Q287 q = new Q287();
        int r = q.quickFind(new int[] {3,1,3,4,2},1,4);
        System.out.println( -1 / 2);
    }


}

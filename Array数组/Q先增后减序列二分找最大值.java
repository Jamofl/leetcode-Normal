package Array数组;

public class Q先增后减序列二分找最大值 {
    public static void main(String[] args){
        int[] nums = new int[]{7,5,4,2,1};
        System.out.println(maxElement(nums));
    }

    public static int maxElement(int[] nums){
        int start = 0;
        int end = nums.length - 1;

        while (start <= end){
            int middle = start + (end - start) / 2;
            if (middle == start)
                return Math.max(nums[start], nums[end]);
            else if (middle == end)
                return nums[end];
            else {
                if (nums[middle] >= nums[middle + 1] && nums[middle] >= nums[middle - 1])
                    return nums[middle];
                else if (nums[middle] >= nums[middle + 1] && nums[middle - 1] >= nums[middle])
                    end = middle - 1;
                else if (nums[middle] <= nums[middle + 1] && nums[middle - 1] <= nums[middle])
                    start = middle + 1;
            }
        }
        return -1;
    }
}

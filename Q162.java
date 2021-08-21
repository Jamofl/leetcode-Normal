import java.rmi.RMISecurityException;

/**
 *
 峰值元素是指其值大于左右相邻值的元素。

 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。

 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

 你可以假设 nums[-1] = nums[n] = -∞。

 示例 1:

 输入: nums = [1,2,3,1]
 输出: 2
 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 示例 2:

 输入: nums = [1,2,1,3,5,6,4]
 输出: 1 或 5
 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 或者返回索引 5， 其峰值元素为 6。
 *
 */
public class Q162 {
    public int binarySearch(int[] nums, int start, int end){
        //not needed
//        if(end <= start)
//            return -1;

//        if(end - start == 1){
//            if(middle != 0 && middle != nums.length - 1)
//                return -1;
//        }
//        if (middle == 0)
//            return (nums[middle] > nums[middle + 1]) ? middle : -1;
//        else if(middle == nums.length - 1)
//            return (nums[middle] > nums[middle - 1]) ? middle : -1;
//        else{
//            if(nums[middle] > nums[middle + 1] && nums[middle] > nums[middle - 1])
//                return middle;
//            else{
//                int rightside = binarySearch(nums, middle, end, middle + (end - middle + 1) / 2);
//                int leftside = binarySearch(nums, start, middle, start + (middle - start) / 2);
//                return ( rightside == -1) ? leftside : rightside;
//            }
//        }
        if(start == end)
            return start;
        int middle = start + (end - start) / 2;
        if(nums[middle] > nums[middle + 1])
            return binarySearch(nums, start, middle);
        else
            return binarySearch(nums, middle + 1, end);
    }

    public int findPeakElement(int[] nums) {
        return binarySearch(nums, 0, nums.length - 1);
    }

    public static void main(String[] args){
        Q162 q = new Q162();
        int r = q.findPeakElement(new int[] {1,2,3});
        System.out.println(r);
    }
}

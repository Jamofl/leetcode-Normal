import java.nio.channels.GatheringByteChannel;

public class BinarySearch {


    // return the index of the elements in the array whose value is target.
    public int binarySearch(int[] nums, int target, int start, int end){
        if (end < start)
            return -1;
        int middle = start + (end - start) / 2;
        if (nums[middle] > target)
            return binarySearch(nums, target, start, middle - 1);
        else if(nums[middle] < target)
            return binarySearch(nums, target, middle + 1, end);
        else
            return middle;
    }


    // 若二分查找的数组中有重复元素 是无法保证下标的
    public int binarySearchIter(int[] nums, int target, int start, int end){
        int middle;
        while (start <= end){
            middle = start + (end - start) / 2;
            if (target > nums[middle]){
                start = middle + 1;
            }
            else if (target < nums[middle]){
                end = middle - 1;
            }
            else
                return middle;
        }
        return -1;
    }

    public int binarySearchIterWithRepeatElement(int[] nums, int target, int start, int end){
        int middle;
        while (start <= end){
            middle = start + (end - start) / 2;
            if (target > nums[middle]){
                start = middle + 1;
            }
            else if (target < nums[middle]){
                end = middle - 1;
            }
            else{
                while(middle - 1 >= 0 && nums[middle - 1] == nums[middle])
                    middle --;

                return middle;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        BinarySearch b = new BinarySearch();
        int[] nums = new int[] {2,2,2,2,2,3,4,5};
        int target = 2;
        int n = nums.length;
        //int r = b.binarySearch(nums, target, 0, n - 1);
        int r2 = b.binarySearchIterWithRepeatElement(nums, target, 0, n - 2);
        //System.out.println(r);
        System.out.println(r2);
    }
}

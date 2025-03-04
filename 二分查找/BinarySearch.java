package 二分查找;

public class BinarySearch {

    /**
     * 递归法二分查找
     * return the index of the elements in the array whose value is target.
     * 二分查找 若找到元素返回对应下标 若没找到元素返回-1
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
    public int binarySearchRer(int[] nums, int target, int start, int end){
        if (end < start)
            return -1;
        int middle = start + (end - start) / 2;
        if (nums[middle] > target)
            return binarySearchRer(nums, target, start, middle - 1);
        else if(nums[middle] < target)
            return binarySearchRer(nums, target, middle + 1, end);
        else
            return middle;
    }

    /**
     * 迭代法二分查找
     * 二分查找 若找到元素返回对应下标 若没找到元素返回-1
     * 若二分查找的数组中有重复元素 是无法保证下标的
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
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

    /**
     * 有重复元素的二分查找 返回重复元素的第一个下标
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
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
                // 返回相等元素中最左边的那个下标
                while(middle - 1 >= 0 && nums[middle - 1] == nums[middle])
                    middle --;

                return middle;
            }
        }
        return -1;
    }


    /**
     * 有重复元素的二分查找 返回目标元素的最左和最右下标 不存在则返回-1, -1
     * @param nums
     * @param target
     * @return
     */
    private int[] binarySearchWithRepeatElement(int[] nums, int target){
        int l = 0, r = nums.length - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if (target > nums[mid]){
                l = mid + 1;
            }
            else if (target < nums[mid]){
                r = mid - 1;
            }
            else{
                int leftPoint = mid, rightPoint = mid;
                while (leftPoint - 1 >= 0 && nums[leftPoint - 1] == target){
                    leftPoint --;
                }
                while(rightPoint + 1 <= nums.length - 1 && nums[rightPoint + 1] == target){
                    rightPoint ++;
                }
                return new int[]{leftPoint, rightPoint};
            }
        }
        return new int[]{-1, -1};
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

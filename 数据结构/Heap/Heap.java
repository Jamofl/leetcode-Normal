package 数据结构.Heap;

public class Heap {

    // 大顶堆
    public int[] nums;
    public Heap (int[] nums){
        this.nums = nums;
        heapify();
    }

    private int[] swap (int[] nums, int i, int j){
        if(i == j)
            return nums;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }

    private void swim (int[] nums, int i, int end){
        int indexParent = (i - 1) / 2 <= 0 ? 0 : (i - 1) / 2;
        if (nums[i] > nums[indexParent]){
            swap(nums, i ,indexParent);
            swim(nums, indexParent, end);
        }
    }

    private void sink(int[] nums, int i, int end){
        int indexLeft = i * 2 + 1 > end ? -1 : i * 2 + 1;
        int indexRight = i * 2 + 2 > end ? -1 : i * 2 + 2;
        if (indexLeft == -1 && indexRight == -1)
            return;

        // 找出较大的那个子节点
        int indexLarger = indexLeft;
        if(indexRight != -1)
            indexLarger = (nums[indexLeft] > nums[indexRight]) ? indexLeft : indexRight;

        // 若该节点小于较大的子节点，交换，并递归的下沉
        if(nums[i] < nums[indexLarger]){
            swap(nums, i, indexLarger);
            sink(nums, indexLarger, end);
        }
    }

    private void heapify(){
        for (int i = nums.length - 1; i >= 0; i --){
            sink(nums, i, nums.length - 1);
        }
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,5,4};
    }
}

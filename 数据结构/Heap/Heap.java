package 数据结构.Heap;

public class Heap {

    // 大顶堆
    public static int[] nums;
    public Heap (int[] nums){
        nums = nums;
        heapify();
    }

    private static int[] swap (int[] nums, int i, int j){
        if (i == j)
            return nums;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }

    private static void swim (int[] nums, int i, int end){
        int indexParent = (i - 1) / 2 <= 0 ? 0 : (i - 1) / 2;
        if (nums[i] > nums[indexParent]){
            swap(nums, i ,indexParent);
            swim(nums, indexParent, end);
        }
    }

    /**
     * 大顶堆 堆定是最大元素 堆底是最小元素
     * 将nums[i]对应的元素递归的进行下沉 直到沉到堆底 那么nums[i]就是最小元素
     * 判断nums[i]的左子节点 和 右子节点，若nums[i] < 较大的那一个，那么和他进行交换，然后递归下沉
     * @param nums
     * @param i
     * @param end
     */
    private static void sink(int[] nums, int i, int end){
        int indexLeft = i * 2 + 1 > end ? -1 : i * 2 + 1;
        int indexRight = i * 2 + 2 > end ? -1 : i * 2 + 2;
        if (indexLeft == -1 && indexRight == -1)
            return;

        // 找出较大的那个子节点 （如果找到的是二者中较小的那个节点，交换后，当前节点可能需要再次下沉）
        //  1 2 3 =>  2 1 3 此时2节点还需要再次下沉
        int indexLarger = indexLeft;
        if(indexRight != -1)
            indexLarger = (nums[indexLeft] > nums[indexRight]) ? indexLeft : indexRight;

        // 若该节点小于较大的子节点，交换，并递归的下沉 一直把nums[i]沉到最底部
        if(nums[i] < nums[indexLarger]){
            swap(nums, i, indexLarger);
            sink(nums, indexLarger, end);
        }
    }

    private static void heapify(){
        for (int i = nums.length - 1; i >= 0; i --){
            sink(nums, i, nums.length - 1);
        }
    }

    public static void main(String[] args){
        nums = new int[]{3,2,1,5,4,6,7,8};
        heapify();
        for (int i : nums)
            System.out.println(i);
    }
}

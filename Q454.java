/*

 */
public class Q454 {
    private boolean binaryFind(int k, int[] nums, int start, int end){
        if (start > end)
            return false;
        int middle = start + (end - start) / 2;
        if (nums[middle] == k)
            return true;
        else if (nums[middle] < k)
            return binaryFind(k, nums, middle + 1, end);
        else
            return binaryFind(k, nums, start, middle - 1);
    }

    public static void main(String[] args){
        Q454 q = new Q454();
        boolean r = q.binaryFind(-1, new int[]{1,2,3,4,6,7}, 0, 5);
        System.out.println(r);

    }
}

package 排序算法;

public class RadixSort

{
    public int R = 10; //0 ~ 9

    // return the ith digit of a num, counting from right to left ; K : 0, 1, 2 ...
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

        int k = 0; // 第k位数

//        Integer[] numsInteger = Arrays.stream(nums).boxed().toArray(Integer[] :: new);
//        int maxValue = Collections.max(Arrays.asList(numsInteger));
        int maxValue = 0;
        for (int i : nums){
            if(i > maxValue)
                maxValue = i;
        }

        while (maxValue != 0) {
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

            for (int i = 0 ; i < nums.length; i ++)
                nums[i] = sortedArray[i];
        }
        return sortedArray;
    }
}

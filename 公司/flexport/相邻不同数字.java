package 公司.flexport;

import java.sql.Array;
import java.util.*;

public class 相邻不同数字 {
    /**
     * 输入一个整数数组 输出一个整数数组 保证输出的整数数组的相邻元素不相同
     * 输出任意一个结果 若结果不存在 输出空数组
     * 如 input = [1,1,1,2,2,2]
     * output = [1,2,1,2,1,2]
     */


    public int[] notSameNumArra(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return nums;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] re = new int[nums.length];
        ArrayList<Integer> reList = new ArrayList<>();
        boolean haveAns = true;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }


        while (map.size() > 0) {
            if (doNotHaveAns(map)){
                haveAns = false;
                break;
            }

            Set<Integer> tempKeySet = new HashSet<>(map.keySet());
            for (int num : tempKeySet) {
                if (map.getOrDefault(num, 0) > 0) {
                    reList.add(num);
                    map.put(num, map.getOrDefault(num, 0) - 1);
                }
                if (map.getOrDefault(num, 0) == 0) {
                    map.remove(num);
                }
            }
        }
        if (haveAns){
            reList.stream().toArray(Integer[]::new);
            reList.toArray(new Integer[reList.size()]);
            return reList.stream().mapToInt(Integer::intValue).toArray();
        }
        else{
            return null;
        }
    }

    private boolean doNotHaveAns(HashMap<Integer, Integer> map) {
        if (map.size() == 1){
            for (int num : map.keySet()){
                if (map.get(num) >= 2){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2};
        相邻不同数字 solution = new 相邻不同数字();
        int[] re = solution.notSameNumArra(nums);
        System.out.println(re);

    }
}

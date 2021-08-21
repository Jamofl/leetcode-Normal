import java.util.HashMap;
import java.util.*;

public class 只出现一次的元素 {
    public static void main(String[] args){
        System.out.println(onlyOnceElement2(new int[] {1,1,2,2,3,3,5}));
    }

    public static int onlyOnceElement(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            if (map.getOrDefault(num, -1) == -1)
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
        }

        for (Integer key : map.keySet()){
            if (map.get(key) == 1)
                return key;
        }
        return -1;
    }

    public static int onlyOnceElement2(int[] nums){
        int k = nums[0];
        for (int i = 1; i < nums.length; i ++)
            k = k ^ nums[i];
        return k;
    }



}

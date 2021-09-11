import javax.sql.rowset.serial.SQLInputImpl;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
public class Q2 {



    public static List<Integer> ans = new LinkedList<>();

    static int maxAmount(int[] packets, int n) {
        Arrays.sort(packets);
        dfs(packets, new LinkedList<>(), 0, n);
        return Collections.max(ans);
    }


    // n代表要切的刀数
    private static void dfs(int[] packets, LinkedList<Integer> path, int start, int n){
        if (n == 0){
            ans.add(Collections.min(path));
            return;
        }

        if (packets.length - start < n)
            return;

        int sum = 0;
        for (int i = start; i < packets.length - n; i ++){
            sum = sum + packets[i];
            path.addLast(sum);
            dfs(packets, path, i + 1,n - 1);
            path.removeLast();
        }
    }

    public static  void main(String[] args){
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        System.out.println(maxAmount(nums, 5));


//        数据结构.LinkedList<Integer> lst = new 数据结构.LinkedList<>();
//        lst.add(1);
//        lst.add(3);
//        lst.add(0);
//        lst.remove(0);
    }
}

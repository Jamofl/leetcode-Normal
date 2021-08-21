import java.util.LinkedList;
import java.util.Queue;

public class Q两个数之间的最短距离 {
    /*
    计算器只支持 -1 操作和 *2 操作，初始值x和目标值y，最少通过几步可以从x到y
     */

    // 图的解法
    public static void main(String[] args){
        System.out.println(minStep(1,7));
    }

    public static int minStep(int src, int target){
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);

        int step = 0;
        while (q.size() != 0){
            int size = q.size();
            while (size > 0){
                int pop = q.poll();
                if (pop == target)
                    return step;
                q.offer(pop - 1);
                q.offer(pop * 2);
                size --;
            }
            step ++;
        }

        return -1;
    }
}

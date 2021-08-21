import edu.princeton.cs.algs4.Graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 我们将整数 x 的 权重 定义为按照下述规则将 x 变成 1 所需要的步数：
 *
 * 如果 x 是偶数，那么 x = x / 2
 * 如果 x 是奇数，那么 x = 3 * x + 1
 * 比方说，x=3 的权重为 7 。因为 3 需要 7 步变成 1 （3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）。
 *
 * 给你三个整数 lo， hi 和 k 。你的任务是将区间 [lo, hi] 之间的整数按照它们的权重 升序排序 ，如果大于等于 2 个整数有 相同 的权重，那么按照数字自身的数值 升序排序 。
 *
 * 请你返回区间 [lo, hi] 之间的整数按权重排序后的第 k 个数。
 *
 * 注意，题目保证对于任意整数 x （lo <= x <= hi） ，它变成 1 所需要的步数是一个 32 位有符号整数。
 *
 示例 1：

 输入：lo = 12, hi = 15, k = 2
 输出：13
 解释：12 的权重为 9（12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）
 13 的权重为 9
 14 的权重为 17
 15 的权重为 17
 区间内的数按权重排序以后的结果为 [12,13,14,15] 。对于 k = 2 ，答案是第二个整数也就是 13 。
 注意，12 和 13 有相同的权重，所以我们按照它们本身升序排序。14 和 15 同理。
 */

public class Q1387 {

    // convert k to 1 according to the rules defined above, return the steps it takes.
    private int convertedTo1(int k, int step){
        if(k == 1)
            return step;
        else if (k % 2 == 0)
            return convertedTo1(k / 2, step + 1);
        else
            return convertedTo1(k * 3 + 1, step + 1);
    }
    private class Num implements Comparable {
        public int val;
        public int step;
        public Num(int val, int step){
            this.val = val;
            this.step = step;
        }

        @Override
        public int compareTo(Object o) {
            Num that = (Num) o;
            return this.step - that.step;
        }
    }

    public int getKth(int lo, int hi, int k) {
        List<Num> ans = new LinkedList<>();

        for(int i = lo; i <= hi; i ++){
            ans.add(new Num(i, convertedTo1(i, 0)));
        }

        Collections.sort(ans);
        return ans.get(k - 1).val;
    }

    public static void main(String[] args){
        Q1387 q = new Q1387();
        System.out.println(q.getKth(7,11,4));
    }
}

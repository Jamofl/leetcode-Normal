package Array数组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Q56合并区间 {
    public int[][] merge(int[][] intervals) {
        Comparator<int[]> cmp = new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        };


        Arrays.sort(intervals, cmp);

        LinkedList<int[]> result = new LinkedList<>();
        int i = 0;
        while (i < intervals.length){
            if (result.isEmpty())
                result.add(intervals[i]);
            else{
                if(intervals[i][0] > result.getLast()[1])
                    result.add(intervals[i]);
                else {
                    int[] lastInterval = result.removeLast();
                    int[] newInterval = {lastInterval[0], Integer.max(lastInterval[1], intervals[i][1])};
                    result.add(newInterval);
                }
            }
            i ++;
        }
        return result.toArray(new int[result.size()][]);
    }

    public static  void main(String[] args){
        int[][] intervals = {{1,3}, {2,5}, {8,10}, {15,18}};
        Q56合并区间 q = new Q56合并区间();
        int[][] result = q.merge(intervals);
    }
}
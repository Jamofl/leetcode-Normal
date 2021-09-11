package 数据结构.优先队列;

/**
 * 有一些会议int[][]，其中每个会议表示为int[2]，int[0]为起始时间，int[1]为结束时间。这些会议之间，可能会有重叠。重叠的会议不能使用同一个会议室。
 * 计算出所需要的最少的会议室。
 *
 *
 * 以会议室为维度进行划分  使用一个优先队列保存会议的结束时间
 * 每当有新的会议加入时 取出所有会议中 结束时间最早的那个会议
 * 比较当前会议的开始时间 与最早会议的结束时间 判断是否可以复用会议室
 *          当前会议开始时间 >= 最早会议的结束时间 可以复用   更新结束时间即可
 *          当前会议开始时间 <  最早会议的结束时间 不可以复用 需要新加入一个会议室
 */

import java.util.*;
public class Q会议室问题 {
    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][] {{1,6}, {5,7}, {9, 10}}));
    }

    public static int minMeetingRooms(int[][] intervals) {
        // 根据会议的开始时间进行排序
        Arrays.sort(intervals, (int[] interval1, int[] interval2) -> interval1[0] - interval2[0]);
        // 小顶堆 用于存储当前的会议室的结束时间
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);

        int n = intervals.length;
        for (int i = 1; i < n; i ++){
            int[] currentMeeting = intervals[i];
            int currentStartTime = currentMeeting[0];
            int currentEndTime = currentMeeting[1];
            int minFinishedTime = pq.peek();
            // 如果当前开始时间 >=最早结束时间  可复用会议室 更新结束时间即可
            if (currentStartTime >= minFinishedTime){
                pq.poll();
                pq.offer(currentEndTime);
            }
            // 如果当前开始时间 < 最早结束时间  不可复用 新加入一个会议
            else{
                pq.offer(currentEndTime);
            }
        }
        // pq的容量即为最终需要的会议室个数
        return pq.size();
    }
}

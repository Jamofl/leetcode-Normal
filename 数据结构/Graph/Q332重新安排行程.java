package 数据结构.Graph;
/*
332. 重新安排行程
给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。

提示：

如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
所有的机场都用三个大写字母表示（机场代码）。
假定所有机票至少存在一种合理的行程。
所有的机票必须都用一次 且 只能用一次。

示例 1：

输入：[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
输出：["JFK", "MUC", "LHR", "SFO", "SJC"]
示例 2：

输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 */
import java.awt.*;
import java.util.*;
import java.util.List;

public class Q332重新安排行程 {

    public Map<String, PriorityQueue<String>> adjacent = new HashMap<>();
    public List<String> ans = new LinkedList<>();


    // 维护一个PQ，每次都先弹出字典序最小的那个邻居节点
    // dfs遍历该图，返回dfs后序遍历的结果，然后将该结果逆序
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets){
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (adjacent.getOrDefault(src, null) == null){
                PriorityQueue<String> nei = new PriorityQueue<>();
                nei.add(dst);
                adjacent.put(src, nei);
            }
            else
                adjacent.get(src).add(dst);
        }
        dfs("S");
        Collections.reverse(ans);
        return ans;
    }

    private void dfs(String cur){
        while (adjacent.containsKey(cur) && adjacent.get(cur).size() > 0) {
            String temp = adjacent.get(cur).poll();
            dfs(temp);
        }
        ans.add(cur);
    }

    public static void main(String[] args){
        Q332重新安排行程 q = new Q332重新安排行程();
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(List.of("S", "B"));
        tickets.add(List.of("B", "S"));
        tickets.add(List.of("S", "A"));
        List<String> ans = q.findItinerary(tickets);
        System.out.println(ans.toString());
    }

}

package 数据结构.Graph;
/*
684. 冗余连接
在本问题中, 树指的是一个连通且无环的无向图。

输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。

返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。

示例 1：

输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的无向图为:
  1
 / \
2 - 3
示例 2：

输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
输出: [1,4]
解释: 给定的无向图为:
5 - 1 - 2
    |   |
    4 - 3
注意:

输入的二维数组大小在 3 到 1000。
二维数组中的整数在1到N之间，其中N是输入数组的大小。
 */
import javax.management.InstanceNotFoundException;
import java.util.*;

public class Q684冗余链接 {

    // Solution 1 并查集
    // 构建一个并查集，当每次加入新的边时，都判断两个边是否已经连接上。若已连接，则说明即将成环，直接返回即可
    private class UnionFind{
        public int[] arr;
        public UnionFind(){
            arr = new int[1001];
            Arrays.fill(arr, -1);
        }

        public void union(int m, int n){
            if (m > n){
                union(n, m);
                return;
            }
            int rootM = find(m);
            int rootN = find(n);
            if (rootM == rootN)
                return;

            arr[rootN] = arr[rootN] + arr[rootM];
            arr[rootM] = rootN;
        }

        public int find(int n){
            int root = n;
            while (arr[root] > 0)
                root = arr[root];
            return root;
        }

        public boolean isConnect(int m, int n){
            int rootM = find(m);
            int rootN = find(n);
            return rootM == rootN;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind();
        for (int[] edge : edges){
            if (uf.isConnect(edge[0], edge[1]))
                return edge;
            else
                uf.union(edge[0], edge[1]);
        }
        return null;
    }


    // Solution 2
    // BFS遍历 找出所有度为 1 的顶点，然后释放它们，并且更新所对应邻居顶点的度。
    // 若出现了新的度为 1 的顶点，则加入队列中。重复这一操作，直到队列为空。那么剩下的就是环。
    // 倒叙遍历原列表，找到最后那个出现的边，就是答案。
    public int[] findRedundantConnection2(int[][] edges) {
        boolean [][] matrix = new boolean[1001][1001];
        Map<Integer, Integer> degrees = new HashMap<>();
        Set<Integer>[] adjacent = new HashSet[1001];

        for (int i = 0; i < adjacent.length; i ++)
            adjacent[i] = new HashSet<>();
        for (int[] edge : edges){
            degrees.put(edge[0], degrees.getOrDefault(edge[0], 0) + 1);
            degrees.put(edge[1], degrees.getOrDefault(edge[1], 0) + 1);
            matrix[edge[0]][edge[1]] = true;
            adjacent[edge[0]].add(edge[1]);
            adjacent[edge[1]].add(edge[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (Integer node : degrees.keySet()){
            if (degrees.get(node) == 1)
                q.offer(node);
        }

        while (q.size() != 0){
            Integer pop = q.poll();
            for (Integer nei : adjacent[pop]){
                degrees.put(nei, degrees.get(nei) - 1);
                matrix[Math.min(pop, nei)][Math.max(pop, nei)] = false;
                if (degrees.get(nei) == 1)
                    q.offer(nei);
            }
        }

        for (int i = edges.length - 1; i >= 0; i --){
            int[] edge = edges[i];
            if (matrix[edge[0]][edge[1]])
                return edge;
        }
        return null;
    }

    // Solution 3:  dfs 遍历
    // 可以在添加一条边的时候，检查从其中一个顶点是否可以通过 遍历(dfs bfs均可) 到达另一个顶点
    // 如果可到达，说明存在环了，直接返回即可。
    Set<Integer>[] adjacent;
    public int[] findRedundantConnection3(int[][] edges) {
        adjacent = new HashSet[1001];
        for (int i = 0; i < adjacent.length; i ++)
            adjacent[i] = new HashSet<Integer>();


        for (int[] edge : edges){
            if (! dfs(edge[0], edge[1], new HashSet<Integer>())){
                adjacent[edge[0]].add(edge[1]);
                adjacent[edge[1]].add(edge[0]);
            }
            else
                return edge;
        }
        return null;
    }

    private boolean dfs(int src, int dst, Set<Integer> visit){
        for (int nei : adjacent[src]){
            if (!visit.contains(nei)){
                visit.add(nei);
                if (nei == dst)
                    return true;
                if (dfs(nei, dst, visit))
                    return true;
            }
        }
        return false;
    }
}

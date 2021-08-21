/*
200. 岛屿数量
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。



示例 1：

输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
示例 2：

输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3


提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] 的值为 '0' 或 '1'
 */
package 数据结构.DisjointSet;
import java.util.*;

public class Q200 {

    // Solution 1:并查集
    private class UnionFind{
        private class Node{
            int x; // row
            int y; // col
            public Node(int x, int y){
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o){
                if (o == null || !(o instanceof Node))
                    return false;
                Node that = (Node) o;
                return (this.x == that.x) && (this.y == that.y);
            }

            @Override
            public int hashCode(){
                return this.x * 7 * 7 + this.y * 7;
            }
        }

        public Map<Node, Node> map;
        public Set<Node> set;
        public int[] Xs = new int[] {-1, 0, 1, 0};
        public int[] Ys = new int[] {0, 1, 0, -1};
        public UnionFind(char[][] grid){
            map = new HashMap();
            set = new HashSet();
            for (int i = 0; i < grid.length; i ++){
                for (int j = 0; j < grid[0].length; j ++){
                    if (grid[i][j] == '1'){
                        Node node = new Node(i, j);
                        set.add(node);
                        map.put(node, node);
                    }
                }
            }

            for (Node node : set){
                for (int k = 0; k <= 3; k ++){
                    int newX = node.x + Xs[k];
                    int newY = node.y + Ys[k];
                    if (newX >= 0 && newX <= grid.length - 1 && newY >= 0 && newY <= grid[0].length - 1){
                        Node temp = new Node(newX, newY);
                        if (set.contains(temp))
                            this.union(node, temp);
                    }
                }
            }
        }

        public void union(Node p, Node q){
            Node rootP = find(p);
            Node rootQ = find(q);
            if (rootP.equals(rootQ))
                return;
            map.put(rootP, rootQ);

        }

        public Node findWithoutPathCompression(Node p){
            if (map.get(p) != p)
                return find(map.get(p));
            else
                return p;
        }

        public Node find(Node p){ // with path compression, save time
            Node root = p;
            while (map.get(root) != root)
                root = map.get(root);

            Node cur = p;
            while (map.get(cur) != root){
                Node tempParent = map.get(cur);
                map.put(cur, root);
                cur = tempParent;
            }
            return root;
        }

        public int countRoot(){
            Set<Node> rootSet = new HashSet<>();
            for (Node node : set){
                Node tempRoot = find(node);
                if (rootSet.contains(tempRoot))
                    continue;
                else
                    rootSet.add(tempRoot);
            }
            return rootSet.size();
        }
    }

    public int numIslands(char[][] grid) {
        UnionFind uf = new UnionFind(grid);
        return uf.countRoot();
    }




    // Solution 2: BFS / DFS  faster
    public int[] Xs = new int[]{-1,0,1,0};
    public int[] Ys = new int[]{0,1,0,-1};
    public int count = 0;
    public int numIslands2(char[][] grid) {
        for (int i = 0; i < grid.length; i ++){
            for (int j = 0; j < grid[0].length; j ++){
                if (grid[i][j] == '1'){
                    bfs(grid, i, j); // dfs(grid, i, j)
                    count ++;
                }
            }
        }
        return count;
    }

    public void dfs(char [][] grid, int x, int y){
        grid[x][y] = '0';
        for (int k = 0; k <= 3; k ++){
            int newX = x + Xs[k];
            int newY = y + Ys[k];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '1'){
                dfs(grid, newX, newY);
            }
        }
    }

    public void bfs(char[][] grid, int x, int y){
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{x, y});
        grid[x][y] = '0';
        while (queue.size() != 0){
            int[] temp = queue.poll();
            for (int k = 0; k <= 3; k ++){
                int newX = temp[0] + Xs[k];
                int newY = temp[1] + Ys[k];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '1'){
                    grid[newX][newY] = '0';
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
    }



    public static void main(String[] args){
        Q200 q = new Q200();
        char[][] grid = new char[][]{{'1','1','1'}, {'1','1','0'}, {'0','0','1'}};
        int r = q.numIslands(grid);
        System.out.println(r);
    }
}

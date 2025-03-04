package 数据结构.DisjointSet;


/**
 * leetcode版本实现并查集
 *  使用两个数组，分别维护每个节点的根节点和秩
 */
public class DSU {

    private int[] parent; // 每个节点的根节点
    private int[] rank;   // 秩-定义为树的高度


    public DSU(int n){
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 每个节点的初始根节点就是自己
            rank[i] = 1;   // 每个节点的初始秩就是1
        }
    }


    /**
     * 寻找根节点
     * 判断当前节点是否为根节点，若不是，继续寻找根节点 顺便执行路径压缩；若是 直接返回
     * @param m
     * @return
     */
    public int find(int m){
        // 若不是根节点 则继续寻找根节点 并缩短路径
        if (parent[m] != m){
            parent[m] = find(parent[m]);
            return parent[m];
        }
        // 若是根节点 返回根节点的值
        else{
            return parent[m];
        }
    }


    /**
     * 将两个节点合并到一个集合里
     *  将秩小的合并到秩大的集合里
     *  若两个集合的秩相同 任意合并都可以 合并后秩+1
     * @param m
     * @param n
     * @return
     */
    public boolean union(int m, int n){
        int rootM = find(m);
        int rootN = find(n);
        if (rootM == rootN){
            return false;
        }
        if (rank[rootM] < rank[rootN]){
            parent[rootM] = rootN;
        }
        else if (rank[rootM] > rank[rootN]){
            parent[rootN] = rootM;
        }
        else {
            parent[rootN] = rootM;
            rank[rootM] ++;
        }
        return true;
    }

    // 判断两个元素是否属于同一个集合
    public boolean isConnected(int x, int n) {
        return find(x) == find(n);
    }
}

package 数据结构.DisjointSet;

import java.util.Arrays;

public class UnionFind {

    private int[] arr;
    public UnionFind(int k){
        this.arr = new int[k];
        Arrays.fill(arr, -1); // -x代表着链接着的节点的数量。 -1表示根节点
    }

    // union m and n together
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

    // return the root of m
    public int find(int m){
        if (arr[m] > 0)
            return find(arr[m]);
        else
            return m;
    }

    // 路径压缩
    public int findwithPathCompact(int m){
        int root = m;
        while (arr[root] > 0)
            root = arr[root];

        // 路径压缩
        int cur = m;
        while (cur != root){
            int parent = arr[cur];
            arr[cur] = root;
            cur = parent;
        }
        return root;
    }

    public static void main(String[] args){
        UnionFind unionfind = new UnionFind(5);
        unionfind.union(1,3);
        unionfind.union(2,4);
        unionfind.union(1,2);
        unionfind.union(1,4);
        int r = unionfind.find(3);
        r = unionfind.find(1);

    }
}

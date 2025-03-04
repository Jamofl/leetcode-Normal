package 数据结构.DisjointSet;

import java.util.Arrays;


/**
 * cs61b 实现并查集
 * 只使用一个数组维护根节点
 *  当前值>=0 代表其根节点的值
 *  当前值<0  代表其为根节点 -x的值代表其上链接着的节点数量
 */
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
    // 递归法路径压缩
    public int find(int m){
        //  >=0时  arr[m]存储的是根节点
        if (arr[m] >= 0){
            arr[m] = find(arr[m]);
            return arr[m];
        }
        // <0时  当前节点就是根节点 arr[m]存储的是当前节点上所有子节点的个数
        else{
            return arr[m];
        }
    }

    // 迭代法路径压缩
    public int findwithPathCompactRecursion(int m){
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

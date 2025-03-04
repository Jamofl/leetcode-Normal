package 数据结构.Tree.kdtree;


// 表示二维平面上的一个点


// 二维 KD 树类
public class MyKdTree {
    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // 计算两点之间的欧几里得距离
        public double distanceTo(Point other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    // 二维 KD 树的节点类
    static class KDNode {
        Point point;
        KDNode left;
        KDNode right;

        public KDNode(Point point) {
            this.point = point;
            this.left = null;
            this.right = null;
        }
    }

    private KDNode root;

    public MyKdTree() {
        this.root = null;
    }

    /**
     * 构建kd树的辅助方法
     * @param points 坐标点数组 如 [[1,2], [2,3], [3,4]]
     * @param depth 深度 主要是用来区分当前是x轴还是y轴 这里其实可以用一个boolean值来表示当前是否是x轴 从而达到在x轴和y轴间切换的目的（但如果是kd树不是2d树 那么取mod的方法更加通用）
     * @return 构件好的kd tree
     */
    private KDNode buildTree(Point[] points, int depth) {
        if (points == null || points.length == 0) {
            return null;
        }
        // 确定当前划分维度，0 代表 x 轴，1 代表 y 轴
        int axis = depth % 2;
        // 根据当前维度对节点进行排序
        java.util.Arrays.sort(points, (p1, p2) -> {
            if (axis == 0) {
                return Double.compare(p1.x, p2.x);
            } else {
                return Double.compare(p1.y, p2.y);
            }
        });
        // 取中位数作为当前节点
        int mid = points.length / 2;
        KDNode node = new KDNode(points[mid]);
        // 递归构建左子树
        node.left = buildTree(java.util.Arrays.copyOfRange(points, 0, mid), depth + 1); // copyOfRange[from, to) 左闭右开
        // 递归构建右子树
        node.right = buildTree(java.util.Arrays.copyOfRange(points, mid + 1, points.length), depth + 1);
        return node;
    }

    // 构建 KD 树的公共方法
    public void buildTree(Point[] points) {
        this.root = buildTree(points, 0);
    }

    /**
     * 插入新节点到kd树的辅助方法
     * @param node
     * @param point
     * @param depth
     * @return
     */
    private KDNode insert(KDNode node, Point point, int depth) {
        if (node == null) {
            return new KDNode(point);
        }
        // 确定当前划分维度
        int axis = depth % 2;
        if (axis == 0) {
            if (point.x < node.point.x) {
                node.left = insert(node.left, point, depth + 1);
            } else {
                node.right = insert(node.right, point, depth + 1);
            }
        } else {
            if (point.y < node.point.y) {
                node.left = insert(node.left, point, depth + 1);
            } else {
                node.right = insert(node.right, point, depth + 1);
            }
        }
        return node;
    }

    // 插入新点的公共方法
    public void insert(Point point) {
        this.root = insert(root, point, 0);
    }

    /**
     * 辅助方法：查找最近邻点
     * @param node 当前节点
     * @param target 目标节点
     * @param depth 2d树的深度 将深度mod2得到余数 就可以判断是根据x轴划分还是y轴划分
     * @param best
     * @return
     */
    private Point nearestNeighbor(KDNode node, Point target, int depth, Point best) {
        if (node == null) {
            return best;
        }
        // 计算当前节点到查询点的距离
        double currentDistance = node.point.distanceTo(target);
        if (best == null || currentDistance < best.distanceTo(target)) {
            best = node.point;
        }
        // 确定当前划分维度
        int axis = depth % 2;
        KDNode nearBranch = null; // 更近的分支 即下一个要搜索的分支
        KDNode farBranch = null; // 更远的分支
        if ((axis == 0 && target.x < node.point.x) || (axis == 1 && target.y < node.point.y)) {
            nearBranch = node.left;
            farBranch = node.right;
        } else {
            nearBranch = node.right;
            farBranch = node.left;
        }
        // 先在可能包含最近点的子树中查找
        best = nearestNeighbor(nearBranch, target, depth + 1, best);
        // 检查是否需要搜索另一个子树 即检查当前节点到目标节点的x轴/y轴方向上的距离 是否在目标节点为圆心 最小距离为半径构建的圆的范围内。若在，说明另一边分支里可能也会有更小距离，则继续搜索另一边的分支  具体参考https://www.cnblogs.com/eyeszjwang/articles/2429382.html
        if (axis == 0) {
            if (Math.abs(target.x - node.point.x) < best.distanceTo(target)) {
                best = nearestNeighbor(farBranch, target, depth + 1, best);
            }
        } else {
            if (Math.abs(target.y - node.point.y) < best.distanceTo(target)) {
                best = nearestNeighbor(farBranch, target, depth + 1, best);
            }
        }
        return best;
    }

    // 查找最近邻点的公共方法
    public Point nearestNeighbor(Point query) {
        return nearestNeighbor(root, query, 0, null);
    }

    // 前序遍历 KD 树，用于测试输出
    public void preOrderTraversal(KDNode node) {
        if (node != null) {
            System.out.println(node.point);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        MyKdTree myKdTree = new MyKdTree();
        // 创建一些二维点
        Point[] points = {
                new Point(3, 6),
                new Point(17, 15),
                new Point(13, 15),
                new Point(6, 12),
                new Point(9, 1),
                new Point(2, 7),
                new Point(10, 19)
        };
        // 创建 KD 树实例
        MyKdTree kdTree = new MyKdTree();
        // 构建 KD 树
        kdTree.buildTree(points);
        System.out.println("构建后的 KD 树前序遍历结果：");
        kdTree.preOrderTraversal(kdTree.root);

        // 插入一个新的点
        Point newPoint = new Point(5, 8);
        kdTree.insert(newPoint);
        System.out.println("\n插入新点 " + newPoint + " 后的 KD 树前序遍历结果：");
        kdTree.preOrderTraversal(kdTree.root);

        // 查找最近邻点
        Point queryPoint = new Point(4, 7);
        Point nearest = kdTree.nearestNeighbor(queryPoint);
        System.out.println("\n查询点 " + queryPoint + " 的最近邻点是：" + nearest);
    }
}


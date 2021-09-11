package AStar;

/**
 * 顶点类
 */
public class Vertex {
    // 顶点的权重是指，截止到目前这个顶点的最优距离
    // 对于dijkstra算法，就是截止到目前
    public double weight;
    public Vertex(double weight){
        this.weight = weight;
    }
}

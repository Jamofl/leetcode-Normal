package AStar;

import java.util.*;

public class AStar {
    private class Vertex {
        public double weight;
        public Vertex(double weight){
            this.weight = weight;
        }
        public  Vertex(){

        }
    }

    // 到某个顶点的最短距离
    public Map<Vertex, Double> DistTo;
    // 到某个顶点的最优上一跳节点
    public Map<Vertex, Vertex> EdgeTo;


    public AStar(Graph<Vertex> graph, Vertex src, Vertex dst){
        this.DistTo = new HashMap<>();
        this.EdgeTo = new HashMap<>();

        //根据顶点的权重排列的pq
        PriorityQueue<Vertex> pq = new PriorityQueue<>((Vertex v1, Vertex v2) -> (int)(v1.weight - v2.weight));
        src.weight = graph.estimateDistanceToGoal(src, dst);
        pq.add(src);
        DistTo.put(src, 0.0);
        EdgeTo.put(src, src);

        Vertex from;
        Vertex to;
        double weight;
        while (pq.size() != 0){
            Vertex pop = pq.poll();
            for (WeightEdge<Vertex> edge : graph.neightbors(pop)){
                from = edge.from;
                to = edge.to;
                weight = edge.weight;

                if (DistTo.get(to) == null || DistTo.get(from) + weight < DistTo.get(to)){
                    DistTo.put(to, DistTo.get(from) + weight);
                    EdgeTo.put(to, from);
                    // 距离目标节点的估计距离
                    double h = graph.estimateDistanceToGoal(to, dst);
                    if (!pq.contains(to)){
                        to.weight = DistTo.get(to) + h;
                        pq.add(to);
                    }
                    else
                        to.weight = DistTo.get(to) + h;
                }


            }
        }
    }
}

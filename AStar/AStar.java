package AStar;
import edu.princeton.cs.algs4.Edge;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.lang.management.GarbageCollectorMXBean;
import java.security.spec.ECGenParameterSpec;
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

    public Map<Vertex, Double> DistTo;
    public Map<Vertex, Vertex> EdgeTo;


    public AStar(Graph<Vertex> graph, Vertex src, Vertex dst){
        this.DistTo = new HashMap<>();
        this.EdgeTo = new HashMap<>();


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

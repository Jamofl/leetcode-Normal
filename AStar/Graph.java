package AStar;
import java.util.*;

public interface Graph<Vertex> {
    List<WeightEdge<Vertex>> neightbors(Vertex v);
    double estimateDistanceToGoal(Vertex src, Vertex dst);
}

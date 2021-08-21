package AStar;

public class WeightEdge<Vertex> {
    public Vertex from;
    public Vertex to;
    public double weight;
    public WeightEdge(Vertex from, Vertex to, double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

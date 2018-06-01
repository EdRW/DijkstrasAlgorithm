import java.util.ArrayList;

public class Vertex {
    public String name;
    public ArrayList<Neighbor> neighbors;

    // Stuff to help with implementing Dijkstra's
    public Double distToSource; // shortest distToSource to source
    public Vertex parent; // reference to parent within the tree

    public class Neighbor{
        Vertex vertex; // reference to the neighbor
        Double edgeWeight; //distance to the neighbor

        public Neighbor(Vertex vertex, Double edgeWeight) {
            this.vertex = vertex;
            this.edgeWeight = edgeWeight;
        }

        @Override
        public String toString() {
            return "Neighbor{" +
                    "vertex=" + ((vertex == null)? "null" : vertex.name) +
                    ", edgeWeight=" + edgeWeight +
                    '}';
        }
    }

    public Vertex(String name) {
        this.name = name;
        this.neighbors =  new ArrayList<>();

        this.distToSource = Double.POSITIVE_INFINITY;
        this.parent = null;
    }

    public void addNeighbor(Vertex vertex, Double edgeWeight){
        this.neighbors.add(new Neighbor(vertex, edgeWeight));
    }


    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", neighbors=" + neighbors +
                ", distToSource=" + distToSource +
                ", parent=" + ((parent == null)? "null" : parent.name) +
                "}\n";
    }
}

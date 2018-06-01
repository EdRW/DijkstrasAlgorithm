import java.util.Comparator;
import java.util.TreeSet;

/**
 * Min Heap which performs the following operations in O(lgn) time
 * extractMin, insert, decreaseKey, and contains
 */
public class MinHeap {

    private TreeSet<Vertex> internalHeap;

    private class DijkstrasVertexComparator implements Comparator<Vertex>{

        @Override
        public int compare(Vertex o1, Vertex o2) {
            if (o1 == o2 || o1.name.equals(o2.name)){
                return 0;
            }

            int compareVal = Double.compare(o1.distToSource,o2.distToSource);

            if (compareVal == 0){
                return o1.name.compareTo(o2.name);
            }

            return compareVal;
        }
    }

    public MinHeap(){
        this.internalHeap = new TreeSet<>( new DijkstrasVertexComparator());
    }

    public Vertex extractMin(){
        return this.internalHeap.pollFirst();
    }

    public boolean insert(Vertex vertex){
        return this.internalHeap.add(vertex);
    }

    public void decreaseKey(Vertex vertex, Double newKey){
        this.internalHeap.remove(vertex);
        vertex.distToSource = newKey;
        this.internalHeap.add(vertex);
    }

    public boolean contains(Vertex vertex){
        return this.internalHeap.contains(vertex);
    }

    public boolean isEmpty(){
        return this.internalHeap.isEmpty();
    }

    public int size(){
        return this.internalHeap.size();
    }
}

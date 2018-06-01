import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        String[] fileNames = {"Case1.txt", "Case2.txt", "Case3.txt"};

        for (String fileName : fileNames){

            /*
            1. Read in the adjacency list and produces a graph
            Initializes all vertices in graph with key and parents
            */
            Map<String,Vertex> graph = readInputFile(fileName);
            Vertex startVertex = graph.get("A");
            Vertex endVertex = graph.get("B");


            /*
            2. The first vertex in the list is the source. Set its key to 0.
            */
            startVertex.distToSource = 0.0;

            /*
            3. Initialize the Min Heap
            */
            MinHeap minHeap = new MinHeap();

            /*
            4. Insert each vertex into the Min Heap
            */
            for (String vertexName : graph.keySet()){
                minHeap.insert(graph.get(vertexName));
            }

            /*
            5. Determine the shortest path between between vertices A and B
            */
            while(!minHeap.isEmpty()){
                Vertex minVertex = minHeap.extractMin();

                for (Vertex.Neighbor neighbor : minVertex.neighbors){
                    Vertex neighborVertex = neighbor.vertex;
                    Double newDistToSource = minVertex.distToSource + neighbor.edgeWeight;

                    //boolean contains = minHeap.contains(neighborVertex);
                    if (minHeap.contains(neighborVertex) && (neighborVertex.distToSource > newDistToSource)){
                        neighborVertex.parent = minVertex;
                        minHeap.decreaseKey(neighborVertex, newDistToSource);
                    }
                }
            }

            /*
            6. Print the solution
             */
            System.out.println("Solution for " + fileName);
            System.out.println("-------------------------");
            printPath(endVertex);
        }
    }

    public static void printPath(Vertex endVertex){
        Vertex parentVertex = endVertex.parent;

        System.out.printf("%.0f\n", endVertex.distToSource);
        Stack<String> solution = new Stack<>();
        solution.push(endVertex.name);
        while (parentVertex != null){
            solution.push(parentVertex.name);
            parentVertex = parentVertex.parent;
        }

        while(!solution.empty()){
            System.out.print(solution.pop() + " ");
        }

        System.out.println("\n");
    }


    public static HashMap<String, Vertex> readInputFile(String fileName) throws Exception {
        Scanner scanner = new Scanner(new File(fileName));

        int numVerts = Integer.valueOf(scanner.nextLine());
//        System.out.println(numVerts);

        HashMap<String, Vertex> graph = new HashMap<>();
        String[] vertexStrArr = null;

        while (scanner.hasNextLine()){
            vertexStrArr = scanner.nextLine().split(" ");

            String vertexName1 = vertexStrArr[0];
            String vertexName2 = vertexStrArr[1];
            Double distance =  Double.valueOf(vertexStrArr[2]);

            Vertex vertex1;
            Vertex vertex2;

            if (graph.containsKey(vertexName1)){
                vertex1 = graph.get(vertexName1);
            }
            else{
                vertex1 = new Vertex(vertexName1);
                graph.put(vertex1.name, vertex1);
            }

            if (graph.containsKey(vertexName2)){
                vertex2 = graph.get(vertexName2);
            }
            else{
                vertex2 = new Vertex(vertexName2);
                graph.put(vertex2.name, vertex2);

            }

            vertex1.addNeighbor(vertex2, distance);
            vertex2.addNeighbor(vertex1, distance);

        }

        if (numVerts != graph.size()){
            throw new Exception("The number of vertices created does not match the value at the beginning of the input file");
        }

        return graph;
    }
}

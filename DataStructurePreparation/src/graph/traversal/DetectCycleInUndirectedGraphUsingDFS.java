package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class DetectCycleInUndirectedGraphUsingDFS {


    static class Tracker<T> {

        Vertex<T> vertex;
        long parentVertexId;

        public Tracker(Vertex<T> vertexId, long parentVertexId) {
            this.vertex = vertexId;
            this.parentVertexId = parentVertexId;
        }
    }


    public static void main(String[] args) {

        Graph<Long> graph = new Graph<>(false);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 7);
        graph.addEdge(0, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);

        DetectCycleInUndirectedGraphUsingDFS detectCycleInUndirectedGraph = new DetectCycleInUndirectedGraphUsingDFS();
        System.out.println(" is cycle found in the graph : " +detectCycleInUndirectedGraph.detectCycleInGraphViaDFS(graph));

    }


    public boolean detectCycleInGraphViaDFS(Graph<Long> graph) {
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        Set<Long> visited = new HashSet<>();
        if (!allVertex.isEmpty()) {
            for (Vertex<Long> vertex : allVertex) {
                if (!visited.contains(vertex.getId())) {
                    visited.add(vertex.getId());
                    if (isCycleDetected(vertex, visited)) return true;
                }
            }
        }
        return false;
    }

    private boolean isCycleDetected(Vertex<Long> vertex, Set<Long> visited) {

        Tracker<Long> vertexTracker = new Tracker<>(vertex, -1);
        Stack<Tracker<Long>> vertexStack = new Stack<>();
        vertexStack.add(vertexTracker);

        while(!vertexStack.isEmpty()) {
            Tracker<Long> vTracker = vertexStack.pop();
            Set<Vertex<Long>> adjacentVertex = vTracker.vertex.getAdjacentVertex();
            if (!adjacentVertex.isEmpty()) {
                for (Vertex<Long> adjVertex : adjacentVertex) {
                    if (!visited.contains(adjVertex.getId())) {
                        visited.add(adjVertex.getId());
                        vertexStack.add(new Tracker<>(adjVertex, vTracker.vertex.getId()));
                    } else if (vTracker.parentVertexId != adjVertex.getId()) {
                        return true;
                    }
                }
            }
        }
      return false;
    }


}

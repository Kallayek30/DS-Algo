package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class DetectCycleInUndirectedGraphUsingBFS {

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

        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,7);
        graph.addEdge(0,4);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        graph.addEdge(6,7);
        DetectCycleInUndirectedGraphUsingBFS detectCycleInUndirectedGraph = new DetectCycleInUndirectedGraphUsingBFS();
        System.out.println(" is cycle found in the graph : " +detectCycleInUndirectedGraph.isCycleDetectedInGraph(graph));

    }

    public boolean isCycleDetectedInGraph(Graph<Long> graph) {
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        Set<Long> visited = new HashSet<>();
        if (!allVertex.isEmpty()) {
            for (Vertex<Long> vertex : allVertex) {
                if (!visited.contains(vertex.getId())) {
                    visited.add(vertex.getId());
                    if (isCycleDetected(vertex, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isCycleDetected(Vertex<Long> vertex, Set<Long> visited) {
        Queue<Tracker<Long>> vertexTracker = new LinkedList<>();
        vertexTracker.add(new Tracker<>(vertex, -1));
        while(!vertexTracker.isEmpty()){
            Tracker <Long> tracker = vertexTracker.poll();
            Set<Vertex<Long>> adjacentVertex = tracker.vertex.getAdjacentVertex();
            if(!adjacentVertex.isEmpty()){
                for(Vertex<Long> adjVertex : adjacentVertex){
                    if(!visited.contains(adjVertex.getId())){
                        vertexTracker.add(new Tracker<>(adjVertex, tracker.vertex.getId()));
                        visited.add(adjVertex.getId());
                    }
                    else if (tracker.parentVertexId != adjVertex.getId()){
                        System.out.println("Cycle found !!!");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

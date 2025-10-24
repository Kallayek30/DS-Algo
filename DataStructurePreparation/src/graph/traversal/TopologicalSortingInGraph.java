package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class TopologicalSortingInGraph {

    public static void main(String[] args) {

        Graph<Long> graph = new Graph<>(true);

        graph.addEdge(4, 6);
        graph.addEdge(9, 11);
        graph.addEdge(10, 8);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(8, 2);
        graph.addEdge(8, 9);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        //graph.addEdge(9, 10);


        TopologicalSortingInGraph topoSort = new TopologicalSortingInGraph();
        Deque<Long> visitedStackOrder = topoSort.doTopologicalSortingRecursive(graph);

        while(!visitedStackOrder.isEmpty()){
            System.out.print(visitedStackOrder.pop()+ ",");
        }
        System.out.println();

    }

    public Deque<Long> doTopologicalSortingRecursive(Graph<Long> graph) {

        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        Deque<Long> vertexStack = new ArrayDeque<>();
        if (!allVertex.isEmpty()) {
            Set<Long> visited = new HashSet<>();
            for (Vertex<Long> vertex : allVertex) {
                if (!visited.contains(vertex.getId())) {
                    topoSortUtil(vertex, visited, vertexStack);
                }
            }
        }
        return vertexStack;
    }

    private void topoSortUtil(Vertex<Long> vertex, Set<Long> visited, Deque<Long> vertexStack) {
        visited.add(vertex.getId());
        vertexStack.addLast(vertex.getId());

        Set<Vertex<Long>> adjacentVertex = vertex.getAdjacentVertex();
        if (!adjacentVertex.isEmpty()) {
            for (Vertex<Long> adjVertex : adjacentVertex) {
                if (!visited.contains(adjVertex.getId())) {
                    topoSortUtil(adjVertex, visited, vertexStack);
                }
            }
        }
    }


}

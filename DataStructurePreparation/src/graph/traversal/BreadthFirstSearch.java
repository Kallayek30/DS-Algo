package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class BreadthFirstSearch {

    public static Queue<Vertex<Long>> vertexTracker = new LinkedList<>();

    public static void main(String[] args) {
        Graph<Long> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(5, 6);
        graph.addEdge(1, 3);
        graph.addEdge(3, 8);
        graph.addEdge(3, 4);
        graph.addEdge(6, 3);
        graph.addEdge(8, 11);

        System.out.println("graph looks like this");
        System.out.println(graph);
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        System.out.println("===== traversing recursively =====");
        bfs.traverseBFSRecursive(graph);
        System.out.println("===== traversing iteratively =====");
        bfs.traverseIteratively(graph);
    }

    public void traverseBFSRecursive(Graph<Long> graph) {
        Set<Long> visited = new HashSet<>();
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        if (allVertex.isEmpty()) {
            return;
        }
        for (Vertex<Long> vertex : allVertex) {
            if (!visited.contains(vertex.getId())) {
                bfsUtil(visited, Set.of(vertex));
            }
        }

    }

    private void bfsUtil(Set<Long> visited, Set<Vertex<Long>> currentLevelOfNodes) {
        if (currentLevelOfNodes.isEmpty()) {
            return;
        }
        Set<Vertex<Long>> nextLeveOfNodes = new HashSet<>();
        for (Vertex<Long> vertex : currentLevelOfNodes) {
            System.out.println("Visiting : " + vertex.getId());
            Set<Vertex<Long>> adjacentVertex = vertex.getAdjacentVertex();
            for (Vertex<Long> adjVertex : adjacentVertex) {
                if (!visited.contains(adjVertex.getId())) {
                    visited.add(adjVertex.getId());
                    nextLeveOfNodes.add(adjVertex);
                }
            }
        }
        bfsUtil(visited, nextLeveOfNodes);
    }


    private void traverseIteratively(Graph<Long> graph) {
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        if (allVertex.isEmpty()) {
            return;
        }
        Set<Long> visited = new HashSet<>();
        Queue<Vertex<Long>> vertexTracker = new LinkedList<>();
        for (Vertex<Long> vertex : allVertex) {
            if (!visited.contains(vertex.getId())) {
                vertexTracker.add(vertex);
                while (!vertexTracker.isEmpty()) {
                    Vertex<Long> visitedVertex = vertexTracker.poll();
                    if (!visited.contains(visitedVertex.getId())) {
                        System.out.println("Visiting: " + visitedVertex.getId());
                        visited.add(visitedVertex.getId());
                        if (visitedVertex.getAdjacentVertex() != null && !visitedVertex.getAdjacentVertex().isEmpty()) {
                            vertexTracker.addAll(visitedVertex.getAdjacentVertex());
                        }
                    }
                }
            }

        }

    }
}

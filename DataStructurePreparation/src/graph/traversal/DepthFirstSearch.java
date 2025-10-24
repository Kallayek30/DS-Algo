package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class DepthFirstSearch {


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
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        System.out.println("======== Visiting iteratively ======");
        depthFirstSearch.doDFSIterative(graph);
        System.out.println("======== Visiting recursively ======");
        depthFirstSearch.doDFSRecursive(graph);
    }

    private void doDFSRecursive(Graph<Long> graph) {
        Set<Long> visited = new HashSet<>();
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        if (allVertex.isEmpty()) {
            return;
        }
        for (Vertex<Long> vertex : allVertex) {
            if (!visited.contains(vertex.getId())) {
                dfsUtil(vertex, visited);
            }
        }
    }

    private void dfsUtil(Vertex<Long> vertex, Set<Long> visited) {
        visited.add(vertex.getId());
        System.out.println("Visiting :" + vertex.getId());
        for (Vertex<Long> adjVertex : vertex.getAdjacentVertex()) {
            if (!visited.contains(adjVertex.getId())) {
                dfsUtil(adjVertex, visited);
            }
        }
    }

    public void doDFSIterative(Graph<Long> graph) {
        Set<Long> visited = new HashSet<>();
        Stack<Vertex<Long>> vertexStack = new Stack<>();
        Collection<Vertex<Long>> allVertices = graph.getAllVertex();
        if (!allVertices.isEmpty()) {
            for (Vertex<Long> vertex : allVertices) {
                vertexStack.push(vertex);
                while (!vertexStack.isEmpty()) {
                    if (!visited.contains(vertexStack.peek().getId())) {
                        System.out.println("visiting :" + vertexStack.peek().getId());
                        visited.add(vertexStack.peek().getId());
                        Vertex<Long> visitedVertex = vertexStack.pop();
                        Set<Vertex<Long>> adjacentVertex = visitedVertex.getAdjacentVertex();
                        if (adjacentVertex != null && adjacentVertex.isEmpty()) {
                            continue;
                        }
                        vertexStack.addAll(adjacentVertex);
                    } else {
                        vertexStack.pop();
                    }
                }
            }
        }
    }

}

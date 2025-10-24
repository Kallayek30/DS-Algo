package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class ConnectedComponent {
    public static void main(String[] args) {
        Graph<Long> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(7, 8);

        System.out.println("Max connected components are " + new ConnectedComponent().getNoOfConnectedComponentViaDFS(graph));
        System.out.println("Max connected components are " + new ConnectedComponent().getNoOfConnectedComponentViaBFS(graph));
    }

    private int getNoOfConnectedComponentViaDFS(Graph<Long> graph) {

        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        if (allVertex.isEmpty()) {
            return -1;
        }
        int componentCounter = 0;
        Set<Long> visited = new HashSet<>();
        Stack<Vertex<Long>> vertexStack = new Stack<>();
        for (Vertex<Long> vertex : allVertex) {
            if (!visited.contains(vertex.getId())) {
                vertexStack.push(vertex);
                if(!visited.contains(vertex.getId())){
                    componentCounter++;
                }
                while (!vertexStack.isEmpty()) {
                    Vertex<Long> visitedVertex = vertexStack.pop();
                    Set<Vertex<Long>> adjacentVertex = visitedVertex.getAdjacentVertex();
                    if (!adjacentVertex.isEmpty()) {
                        vertexStack.addAll(adjacentVertex);
                    }
                    visited.add(visitedVertex.getId());
                }
            }
        }
        return componentCounter;
    }

    private int getNoOfConnectedComponentViaBFS(Graph<Long> graph){
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        if (allVertex.isEmpty()) {
            return -1;
        }
        int connectedComponentCounter = 0;
        Set<Long> visited = new HashSet<>();
        Queue<Vertex<Long>> vertexTracker = new LinkedList<>();
        for(Vertex<Long> vertex : allVertex){
            vertexTracker.add(vertex);
            if(!visited.contains(vertex.getId())){
                connectedComponentCounter++;
            }
            while(!vertexTracker.isEmpty()){
                Vertex<Long> visitedVertex = vertexTracker.poll();
                visited.add(visitedVertex.getId());
                Set<Vertex<Long>> adjacentVertex = visitedVertex.getAdjacentVertex();
                if(!adjacentVertex.isEmpty()){
                    vertexTracker.addAll(adjacentVertex);
                }
            }
        }
        return connectedComponentCounter;
    }
}

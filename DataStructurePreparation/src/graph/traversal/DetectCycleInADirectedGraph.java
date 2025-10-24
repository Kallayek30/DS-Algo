package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class DetectCycleInADirectedGraph {

    static class Frame<T> {
        Vertex<T> vertex;
        boolean isBackTrack;

        public Frame(Vertex<T> vertex, boolean isBackTrack) {
            this.isBackTrack = isBackTrack;
            this.vertex = vertex;
        }
    }

    public static void main(String[] args) {
        Graph<Long> graph = new Graph<>(true);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        graph.addEdge(3, 7);
        graph.addEdge(7, 8);
        graph.addEdge(7, 9);
        graph.addEdge(9, 10);
        graph.addEdge(8, 10);
        graph.addEdge(8, 11);
        graph.addEdge(11, 12);

        DetectCycleInADirectedGraph cdg = new DetectCycleInADirectedGraph();
        System.out.println("is cycle found : " + cdg.detectCycleInADirectedGraphUsingDFSIterative(graph));
        System.out.println("is cycle found : " + cdg.detectCycleInADirectedGraphUsingRecursion(graph));


    }

    public boolean detectCycleInADirectedGraphUsingDFSIterative(Graph<Long> graph) {

        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        Map<Long, Integer> pathVisited = new HashMap<>();
        Stack<Frame<Long>> vertexStack = new Stack<>();
        if (!allVertex.isEmpty()) {
            for (Vertex<Long> vertex : allVertex) {
                pathVisited.put(vertex.getId(), 0);
                vertexStack.add(new Frame<>(vertex, false));
                if (isCyclic(vertexStack, pathVisited)) return true;
            }
        }

        return false;
    }


    private boolean isCyclic(Stack<Frame<Long>> vertexStack, Map<Long, Integer> pathVisited) {
        while (!vertexStack.isEmpty()) {
            Frame<Long> visitedVertex = vertexStack.pop();
            if (visitedVertex.isBackTrack) {
                pathVisited.put(visitedVertex.vertex.getId(), 2);
                continue;
            }
            if (pathVisited.getOrDefault(visitedVertex.vertex.getId(), 0) == 1) {
                System.out.println("Cycle found !!!");
                return true;
            }

            if (pathVisited.getOrDefault(visitedVertex.vertex.getId(), 0) == 2) {
                continue;
            }
            pathVisited.put(visitedVertex.vertex.getId(), 1);
            vertexStack.push(new Frame<>(visitedVertex.vertex, true));

            for (Vertex<Long> adjVertex : visitedVertex.vertex.getAdjacentVertex()) {
                if (pathVisited.getOrDefault(adjVertex.getId(), 0) == 1) {
                    System.out.println("Cycle found");
                    return true;
                }
                if (pathVisited.getOrDefault(adjVertex.getId(), 0) == 0) {
                    vertexStack.push(new Frame<>(adjVertex, false));
                }
            }
        }
        return false;
    }


    public boolean detectCycleInADirectedGraphUsingRecursion(Graph<Long> graph) {
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        if (!allVertex.isEmpty()) {
            Set<Long> visited = new HashSet<>();
            Set<Long> pathVisited = new HashSet<>();
            for (Vertex<Long> vertex : allVertex) {
                if(!visited.contains(vertex.getId())){
                    if(isCyclic(vertex, visited, pathVisited)) return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclic(Vertex<Long> vertex, Set<Long> visited, Set<Long> pathVisited) {

        visited.add(vertex.getId());
        pathVisited.add(vertex.getId());

        for(Vertex<Long> adjVertex : vertex.getAdjacentVertex()){
           if(!visited.contains(adjVertex.getId())){
               if(isCyclic(adjVertex, visited, pathVisited)) return true;
           }
           else if(pathVisited.contains(adjVertex.getId())){
               System.out.println("Cycle found !!!");
               return true;
           }
        }

        pathVisited.remove(vertex.getId());
        return false;

    }

}

package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class EventualSafeStates {

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
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(8, 2);
        graph.addEdge(8, 9);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(10, 8);
        EventualSafeStates eventualSafeStates = new EventualSafeStates();
        System.out.println("safe nodes are : " + eventualSafeStates.findEventualSafeState(graph));

    }

    public SortedSet<Long> findEventualSafeState(Graph<Long> graph) {
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        Map<Long, Integer> pathVisited = new HashMap<>();
        Stack<Frame<Long>> vertexStack = new Stack<>();
        Set<Long> visited = new HashSet<>();
        SortedSet<Long> safeNodes = new TreeSet<>();
        if (!allVertex.isEmpty()) {
            for (Vertex<Long> vertex : allVertex) {
                if (!visited.contains(vertex.getId())) {
                    pathVisited.put(vertex.getId(), 0);
                    vertexStack.add(new Frame<>(vertex, false));
                    isCyclic(vertexStack, pathVisited, safeNodes, visited);
                }
            }
        }
        return safeNodes;

    }

    private boolean isCyclic(Stack<Frame<Long>> vertexStack, Map<Long, Integer> pathVisited, Set<Long> safeNodes, Set<Long> visited) {
        while (!vertexStack.isEmpty()) {
            Frame<Long> visitedVertex = vertexStack.pop();
            if (visitedVertex.isBackTrack) {
                pathVisited.put(visitedVertex.vertex.getId(), 2);
                safeNodes.add(visitedVertex.vertex.getId());
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
            visited.add(visitedVertex.vertex.getId());
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


}

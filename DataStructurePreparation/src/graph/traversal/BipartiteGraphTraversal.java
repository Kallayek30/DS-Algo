package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class BipartiteGraphTraversal {


    public static void main(String[] args) {

    }

    public boolean isBipartiteGraphViaBFS(Graph<Long> graph) {
        List<Vertex<Long>> allVertex = (ArrayList<Vertex<Long>>) graph.getAllVertex();
        if (!allVertex.isEmpty()) {
            Queue<Vertex<Long>> vertexTracker = new LinkedList<>();
            Vertex<Long> initialVertex = allVertex.get(0);
            initialVertex.setColorCode(0);
            vertexTracker.add(initialVertex);
            while (!vertexTracker.isEmpty()) {
                Vertex<Long> visitedVertex = vertexTracker.poll();
                Set<Vertex<Long>> adjacentVertex = visitedVertex.getAdjacentVertex();
                if (!adjacentVertex.isEmpty()) {
                    for (Vertex<Long> adjVertex : adjacentVertex) {
                        if (adjVertex.getColorCode() == -1) {
                            adjVertex.setColorCode(visitedVertex.getColorCode() == 1 ? 0 : 1);
                        } else if (adjVertex.getColorCode() == visitedVertex.getColorCode()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    public boolean isBipartiteGraphViaDFS(Graph<Long> graph) {

        return false;
    }
}

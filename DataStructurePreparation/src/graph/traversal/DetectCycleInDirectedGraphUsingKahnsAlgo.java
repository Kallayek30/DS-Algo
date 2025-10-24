package graph.traversal;

import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

public class DetectCycleInDirectedGraphUsingKahnsAlgo {

    public static void main(String[] args) {

        Graph<Long> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);

        new DetectCycleInDirectedGraphUsingKahnsAlgo().doTopologicalSort(graph);
    }

    public void doTopologicalSort(Graph<Long> graph){
        Collection<Vertex<Long>> allVertex = graph.getAllVertex();
        Queue<Vertex<Long>> vertexQueue = new LinkedList<>();
        Map<Long, Integer> inDegreeCounter = new HashMap<>();
        Deque<Long> inOrderVertexStack = new ArrayDeque<>();
        if(!allVertex.isEmpty()){
            for(Vertex<Long> vertex : allVertex){
                Set<Vertex<Long>> adjacentVertex = vertex.getAdjacentVertex();
                if(!adjacentVertex.isEmpty()){
                    for(Vertex<Long> adjVertex : adjacentVertex){
                        inDegreeCounter.put(adjVertex.getId(),
                                inDegreeCounter.getOrDefault(adjVertex.getId(), 0) + 1);
                    }
                }
            }
            for(Vertex<Long> vertex : allVertex){
                if(inDegreeCounter.get(vertex.getId()) == null){
                    vertexQueue.add(vertex);
                }
            }

            while(!vertexQueue.isEmpty()){
                Vertex<Long> vertex = vertexQueue.poll();
                inOrderVertexStack.addLast(vertex.getId());
                if(!vertex.getAdjacentVertex().isEmpty()){
                    for(Vertex<Long> adjVertex : vertex.getAdjacentVertex()){
                        if(inDegreeCounter.get(adjVertex.getId()) != 0){
                            inDegreeCounter.put(adjVertex.getId(), inDegreeCounter.get(adjVertex.getId()) -1);
                            if(inDegreeCounter.get(adjVertex.getId()) == 0){
                                vertexQueue.add(adjVertex);
                            }
                        }
                    }
                }
            }
        }
        if(inOrderVertexStack.size() != graph.getAllVertex().size()){
            System.out.print("Cycle found in the graph !!!");
            return;
        }
        while(!inOrderVertexStack.isEmpty()){
            System.out.print(inOrderVertexStack.pop()+",");
        }
        System.out.println();
    }
}


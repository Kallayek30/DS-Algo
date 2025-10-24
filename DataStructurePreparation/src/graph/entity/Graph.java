package graph.entity;

import java.util.*;

public class Graph<T> {

    private List<Edge<T>> allEdges;
    private Map<Long, Vertex<T>> allVertex;
    boolean isDirected;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        allEdges = new ArrayList<>();
        allVertex = new HashMap<>();
    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    public void setAllEdges(List<Edge<T>> allEdges) {
        this.allEdges = allEdges;
    }


    public void setAllVertex(Map<Long, Vertex<T>> allVertex) {
        this.allVertex = allVertex;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public void addEdge(long id1, long id2){
        addEdge(id1, id2, 0);
    }

    private void addEdge(long id1, long id2, int weight) {
      Vertex<T> vertex1 = null;
      if(allVertex.containsKey(id1)){
          vertex1 = allVertex.get(id1);
      }else{
          vertex1 = new Vertex<>(id1);
          allVertex.put(id1, vertex1);
      }
      Vertex<T> vertex2 = null;
      if(allVertex.containsKey(id2)){
          vertex2 = allVertex.get(id2);
      }else {
          vertex2 = new Vertex<>(id2);
          allVertex.put(id2, vertex2);
      }

      Edge<T> edge = (weight == 0 ) ? new Edge<>(vertex1, vertex2) :
              new Edge<>(vertex1, vertex2, isDirected, weight);
      allEdges.add(edge);
      vertex1.addAdjacentVertices(edge,vertex2);
      if(!isDirected){
          vertex2.addAdjacentVertices(edge, vertex1);
      }
    }

    public void addVertex(Vertex<T> vertex){
        if(allVertex.containsKey(vertex.getId())){
            return;
        }
        allVertex.put(vertex.getId(), vertex);
        if(vertex.getEdges()!= null && !vertex.getEdges().isEmpty()){
            allEdges.addAll(vertex.getEdges());
        }
    }

    public Vertex<T> addSingleVertex(long id){
        if(allVertex.containsKey(id)){
            return allVertex.get(id);
        }
        Vertex<T> vertex = new Vertex<>(id);
        allVertex.put(id, vertex);
        return vertex;
    }

    public void setDataForVertex(long id, T data){
        if(allVertex.containsKey(id)){
            Vertex<T> vertex = allVertex.get(id);
            vertex.setData(data);
        }
    }

    public Collection<Vertex<T>> getAllVertex(){
        return this.allVertex.values();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Edge<T> edge : getAllEdges()){
            builder
                    .append(edge.getVertex1().getId())
                    .append("->")
                    .append(edge.getVertex2().getId())
                    .append("->")
                    .append(edge.getWeight());
            builder.append("\n");
        }
        return builder.toString();
    }


}

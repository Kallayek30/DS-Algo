package graph.entity;

import java.util.*;

public class Vertex<T> {

    long id;
    private T data;
    private Set<Edge<T>> edges = new HashSet<>();
    private Set<Vertex<T>> adjacentVertex = new HashSet<>();
    private int colorCode = -1;


    public Vertex(long id) {
        this.id = id;
    }

    public Vertex (long id, T data){
        this.id = id;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge<T>> edges) {
        this.edges = edges;
    }

    public Set<Vertex<T>> getAdjacentVertex() {
        return adjacentVertex;
    }

    public void setAdjacentVertex(Set<Vertex<T>> adjacentVertex) {
        this.adjacentVertex = adjacentVertex;
    }

    public void addAdjacentVertices(Edge<T> edge, Vertex<T> vertex){
        edges.add(edge);
        adjacentVertex.add(vertex);
    }

    public int getDegree(){
        return edges.size();
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(id);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id != other.id)
            return false;
        return true;
    }

}

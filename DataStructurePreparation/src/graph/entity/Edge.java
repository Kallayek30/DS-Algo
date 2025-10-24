package graph.entity;

import java.util.Objects;

public class Edge<T> {

    private boolean isDirected = false;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private int weight;

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected, int weight){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
        this.weight = weight;
    }

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public Vertex<T> getVertex1() {
        return vertex1;
    }

    public void setVertex1(Vertex<T> vertex1) {
        this.vertex1 = vertex1;
    }

    public Vertex<T> getVertex2() {
        return vertex2;
    }

    public void setVertex2(Vertex<T> vertex2) {
        this.vertex2 = vertex2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return isDirected == edge.isDirected && Objects.equals(vertex1, edge.vertex1) && Objects.equals(vertex2, edge.vertex2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDirected, vertex1, vertex2);
    }
}
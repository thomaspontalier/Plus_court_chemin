package fr.chemin.domain;


import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;

    private List<Edge> edges = new ArrayList<Edge>();

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Edge> getEdges(){
        return edges;
    }

    public void connectTo(Vertex target, int distance) {
        edges.add(new Edge(target, distance));
    }

    public Vertex findEdge(String name){
        String current=edges.get(0).getTarget().getName();
        int i=0;
        Vertex result=null;
        while (current!=name && i<(edges.size()-1)){
            i++;
            current=edges.get(i).getTarget().getName();
        }
        if (current.equals(name)){
            result=edges.get(i).getTarget();
        }
        return result;
    }

    public int getDistance(String to){

        int result=-3;
        String current=edges.get(0).getTarget().getName();
        int i=0;

        while (current!=to && i<(edges.size()-1)){
            i++;
            current=edges.get(i).getTarget().getName();
        }

        if (current.equals(to)){
            result=edges.get(i).getDistance();
        }

        return result;
    }
}

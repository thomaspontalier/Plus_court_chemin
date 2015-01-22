package fr.chemin.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public int getDistance(String from, String to) {

        Vertex depart=findVertex(from);
        Vertex arrivee=findVertex(to);

        List<Vertex> via=new ArrayList<Vertex>();
        via.add(depart);

        int result=-1;

        if (depart!=null && arrivee!=null){
           result=getDistanceVia(depart, arrivee, via);
        }

        return result;
    }

    private Vertex findVertex(String name){
        Vertex result=null;

        if (this.vertices.size()!=0){

            String current=vertices.get(0).getName();
            int i=0;

            while (current!=name && i<(vertices.size()-1)){
                i++;
                current=vertices.get(i).getName();
            }

            if (current.equals(name)){
                result=vertices.get(i);
            }
        }

        return result;
    }

    private int getDistanceVia(Vertex from, Vertex to, List<Vertex> via){
        int result=-2;

        if (from.findEdge(to.getName())!=null){
            result=from.getDistance(to.getName());
        }

        else{
            List<Edge> possibilities=from.getEdges();


            for (Edge possibility:possibilities){

                if (!via.contains(possibility.getTarget()) && vertices.contains(possibility.getTarget())){

                    //Mise à jour de via
                    //On utilise une copie pour pouvoir lancer un appel récurcif sur toutes les villes adjacentes
                    //tout en les considérant comme parcourues
                    List<Vertex> via2=new ArrayList<Vertex>();
                    via2.addAll(via);
                    for (Edge e:possibilities){
                        via2.add(e.getTarget());
                    }

                    int temp=getDistanceVia(possibility.getTarget(), to, via2);
                    if ((possibility.getDistance()+temp<result || result<0) && temp>-1){
                        result=possibility.getDistance()+ temp;
                    }
                }
            }

        }

        return result;
    }
}

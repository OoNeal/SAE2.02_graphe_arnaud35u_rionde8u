package src;

import java.io.IOException;

public class Principale {

    public static void main(String[] args){
        GrapheListe g = new GrapheListe("graphe.txt");

        /*g.ajouterNoeud("A");
        g.ajouterNoeud("B");
        g.ajouterNoeud("C");
        g.ajouterNoeud("D");
        g.ajouterNoeud("E");

        g.ajouterArc("A","B", 12);
        g.ajouterArc("A","D",87);
        g.ajouterArc("B","E",11);
        g.ajouterArc("C","A",19);
        g.ajouterArc("D","B",23);
        g.ajouterArc("D","C",10);
        g.ajouterArc("E","D",43);*/

        System.out.println(g.toGraphviz());
    }
}

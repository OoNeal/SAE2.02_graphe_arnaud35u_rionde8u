package src;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    public Valeur resoudre(Graphe g, String depart) {
        List<Noeud> Q = new ArrayList<>();
        for (String n : g.listeNoeuds()) {
            Noeud noeud = new Noeud(n);
            // noeud.setValeur(Double.POSITIVE_INFINITY);
            //noeud.setParent(null);
            Q.add(noeud);
        }
        Noeud noeudDepart = new Noeud(depart);
        noeudDepart.ajouterArc(depart, 0);
        while (Q != null) {
            Noeud noeud = Q.get(0);
            Q.remove(noeud);
            for (Noeud n : Q) {
                {
                    //D = noeud.distance + poids(noeud, n)
                    double D = noeud.getAdj().get(0).getCout() + g.listeNoeuds().size();
                    if (D < n.getAdj().get(0).getCout()) {
                        // n.setValeur(D);
                        //n.setParent(noeud);
                    }
                }


            }
        }
        return null;
    }
}
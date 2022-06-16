package src;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    /*
    Entrees :
    G un graphe oriente avec une ponderation (poids) positive des arcs
    A un sommet (depart) de G
    Debut
        Q <- {} // utilisation d’une liste de noeuds a traiter
        Pour chaque sommet v de G faire
            v.distance <- Infini
            v.parent <- Indefini
            Q <- Q U {v} // ajouter le sommet v a la liste Q
        Fin Pour
        A.distance <- 0
        Tant que Q est un ensemble non vide faire
            u <- un sommet de Q telle que u.distance est minimale
            Q <- Q \ {u} // enlever le sommet u de la liste Q
            Pour chaque sommet v de Q tel que l’arc (u,v) existe faire
                D <- u.distance + poids(u,v)
                Si D < v.distance
                    Alors v.distance <- D
                        v.parent <- u
                Fin Si
            Fin Pour
        Fin Tant que
     Fin
     */

    /**
     * Permet de calculer le chemin le plus court entre un noeud de départ et les autres noeuds en utilisant la méthode de Dijkstra
     * @param g le graphe
     * @param depart le noeud de départ
     * @return la valeur contenant tout les noeuds et leurs valeurs et parents
     */
    public Valeur resoudre(Graphe g, String depart) {
        Valeur v = new Valeur();
        List<Noeud> ListeATraiter = new ArrayList<>();
        for (String noeud : g.listeNoeuds()) {
                v.setValeur(noeud, Double.MAX_VALUE);
                ListeATraiter.add(new Noeud(noeud));
        }
        v.setValeur(depart, 0);

        while (!ListeATraiter.isEmpty()) {
            //System.out.println(v);
            Noeud noeud = ListeATraiter.get(0);
            double min = Double.MAX_VALUE;
            for (Noeud n : ListeATraiter) {
                min = Math.min(min, v.getValeur(n.getNom()));
                if (min == v.getValeur(n.getNom())) {
                    noeud = n;
                }
            }
            ListeATraiter.remove(noeud);
            for (Noeud noeudATraiter : ListeATraiter) {
                double cout = -1;
                for (Arc arc : g.suivants(noeud.getNom())) {
                    if (arc.getDest().equals(noeudATraiter.getNom())) {
                        cout = arc.getCout();
                    }
                }
                if (cout != -1) {
                    double newDistance = v.getValeur(noeud.getNom()) + cout;
                    if (newDistance < v.getValeur(noeudATraiter.getNom())) {
                        v.setValeur(noeudATraiter.getNom(), newDistance);
                        v.setParent(noeudATraiter.getNom(), noeud.getNom());
                    }
                }
            }
        }
        return v;
    }
}
package src;

public class BellmanFord {

    public Valeur resoudre(Graphe g, String s) {
        // initialisation
        /*Valeur v = new Valeur();
        for (String n : g.listeNoeuds()) {
            v.setValeur(n, Double.POSITIVE_INFINITY);
            v.setParent(n, null);
        }
        v.setValeur(s, 0);
        return  v; */

        int V = g.listeNoeuds().size();

        Valeur v = new Valeur();
        for (String n : g.listeNoeuds()) {
            v.setValeur(n, Double.POSITIVE_INFINITY);
            v.setParent(n, null);
        }

        v.setValeur(s, 0);
        for (Arc a : g.suivants(s)) {
            v.setValeur(a.getDest(), a.getCout());
            v.setParent(a.getDest(), s);
        }

        // calcul
        for (int i = 0; i < V - 1; i++) {
            for (Arc a : g.suivants(s)) {
                if (v.getValeur(a.getDest()) > v.getValeur(s) + a.getCout()) {
                    v.setValeur(a.getDest(), v.getValeur(s) + a.getCout());
                    v.setParent(a.getDest(), s);
                }
            }
        }

        //checks if there exist negative cycles in graph G
        for (Arc a : g.suivants(s)) {
            if (v.getValeur(a.getDest()) > v.getValeur(s) + a.getCout()) {
                return null;
            }
        }
        return v;



    }
}

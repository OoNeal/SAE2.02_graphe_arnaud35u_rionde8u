package src;

// Question 13 : Algorithme de Bellman-Ford
/*
 * Algorithme de Bellman-Ford
 * PointFixe(Graphe g InOut, Noeud depart) {
 *  debut
 *      valeurNoeudDepart = 0
 *      pour tout noeud n de g
 *         si NomNoeudN != depart alors
 *          valeurNoeudN = +infini
 *         finsi
 *      finpour
 *      pour i de 0 à longueurListeNoeuds - 1 faire
 *      pour tout noeud n de g faire
 *          pour tout les arcs a de n faire
 *              si valeurNoeudN + valeurArcA < valeurNoeudDestinationA alors
 *              valeurNoeudDestinationA = valeurNoeudN + valeurArcA
 *              noeudParentDestinationA = noeudN
 *          finsi
 *      finpour
 *  finpour
 * fin
 *
 * Lexique :
 *  noeudN : Noeud<valeurNoeudN,nomNoeudN>, noeud de la liste des noeuds du graphe
 *  valeurNoeudN : int, valeur du noeudN
 *  nomNoeudN : chaine, nom du noeudN
 *  longueurListeNoeuds : int , longueur de la liste des noeuds du graphe
 *  arcA : Arc<valeurArcA,NoeudA>, arc de la liste des arcs du graphe
 *  valeurArcA : int, cout de l'arcA
 *  ValeurNoeudDestinationA : int, valeur du noeud a la destination de l'arc A de la liste des arcs du graphe
 *  NoeudParentDestinationA : Noeud<valeurNoeudDestinationA,nomNoeudDestionationA>, noeud parent du noeud a la destination de l'arc A de la liste des arcs du graphe
 */
public class BellmanFord {

    /**
     * Permet de calculer le chemin le plus court entre un noeud de départ et les autres noeuds en utilisant la méthode de Bellman-Ford
     * @param g le graphe
     * @param depart le noeud de départ
     * @return la valeur contenant tout les noeuds leurs valeurs et les parents des noeuds
     */
    public Valeur resoudre(Graphe g, String depart) {
        Valeur v = new Valeur();
        v.setValeur(depart, 0);
        for (String n : g.listeNoeuds()) {
            if (!n.equals(depart))
                v.setValeur(n, Double.MAX_VALUE);
        }
        for (int i = 0; i < g.listeNoeuds().size() - 1; i++) {
            //System.out.println(v);
            for (String n : g.listeNoeuds()) {
                for (Arc a : g.suivants(n)) {
                    if (v.getValeur(n) + a.getCout() < v.getValeur(a.getDest())) {
                        v.setValeur(a.getDest(), v.getValeur(n) + a.getCout());
                        v.setParent(a.getDest(), n);
                    }
                }
            }
        }
        return v;
    }
}

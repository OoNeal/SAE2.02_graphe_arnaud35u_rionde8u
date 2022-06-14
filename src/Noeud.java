package src;

import java.util.ArrayList;

public class Noeud {

    /**
     * nom correspondant au nom du noeud qui permet de l’identifier
     */
    private String nom;
    /**
     * liste des arcs reliant ce noeud a ses noeuds adjacents
     */
    private ArrayList<Arc> adj;

    /**
     * Constructeur de la classe Noeud
     * @param nom correspondant au nom du nœud qui permet de l’identifier
     */
    public Noeud(String nom) {
        this.nom = nom;
        this.adj = new ArrayList<>();
    }

    /**
     * Méthode permettant d’ajouter un arc dans la liste des arcs adjacents
     * @param destination correspondant au nom du noeud adjacent
     * @param cout correspondant au coût de l’arc reliant ce noeud à son noeud adjacent
     */
    public void ajouterArc(String destination, double cout){
        boolean existe = false;
        for (Arc arc : this.adj)
            if (arc.getDest().equals(destination)) {
                existe = true;
                break;
            }
        if (!existe)
            this.adj.add(new Arc(destination, cout));
    }

    /**
     * Methode permettant de comparer deux noeud
     * @param n correspondant au noeud à comparer
     * @return true s'ils ont le même nom
     */
    public boolean equals(Noeud n){
        return this.nom.equals(n.getNom());
    }

    /**
     * Permet de recuperer le nom du noeud
     * @return le nom du noeud
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de recuperer la liste des arcs adjacents
     * @return la liste des arcs adjacents
     */
    public ArrayList<Arc> getAdj() {
        return adj;
    }
}

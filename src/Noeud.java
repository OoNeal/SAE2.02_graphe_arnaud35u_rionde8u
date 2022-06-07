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
        this.adj = new ArrayList<Arc>();
    }


}

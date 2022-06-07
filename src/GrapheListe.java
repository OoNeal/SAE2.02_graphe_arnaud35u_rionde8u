package src;

import src.Arc;
import src.Graphe;
import src.Noeud;

import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    private List<String> ensNom;
    private List<Noeud> ensNoeuds;

    public GrapheListe(List<String> ensNom, List<Noeud> ensNoeuds) {
        this.ensNom = ensNom;
        this.ensNoeuds = ensNoeuds;
    }

    @Override
    public List<String> listeNoeuds() {
        List<String> liste = new ArrayList<String>();
        for (Noeud noeud : ensNoeuds)
        {
            liste.add(noeud.getNom());

        }
        return liste;
    }

    @Override
    public List<Arc> suivants(String n) {
        List<Arc> liste = new ArrayList<Arc>();
        for (Noeud noeud : ensNoeuds)
        {
            if (noeud.getNom().equals(n))
            {
                liste = noeud.getAdj();
            }
        }
        return liste;
    }

    public void ajouterArc(String depart, String destination, double cout)
    {
        Noeud noeud = new Noeud(depart);
        // verifier que les noeuds existent bien dans le graphe
        if (ensNom.contains(depart) && ensNom.contains(destination))
        {
            noeud.ajouterArc(destination, cout);
            ensNom.add(depart);
            ensNoeuds.add(noeud);
        }

    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Noeud noeud : ensNoeuds)
        {
            sb.append(noeud.getNom()).append(" -> ").append(noeud.getAdj()).append("\n");
        }
        return sb.toString();
    }
}

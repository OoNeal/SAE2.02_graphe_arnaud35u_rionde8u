package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    private final List<String> ensNom;
    private final List<Noeud> ensNoeuds;

    public GrapheListe(){
        this.ensNom = new ArrayList<String>();
        this.ensNoeuds = new ArrayList<Noeud>();
    }
    public GrapheListe(String n) {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();
        this.ensNom.add(n);
        this.construireGraphe(n);
    }

    private void construireGraphe(String nom) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nom));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] split = line.split(" ");
            this.ensNom.add(split[0]);
            this.ensNom.add(split[1]);
            this.ajouterArc(split[0], split[1], Double.parseDouble(split[2]));
        }
    }

    /**
     * Permet d'ajouter le nom d'un noeud dans le graphe
     *
     * @param nom nom du noeud
     */
    public void ajouterNoeud(String nom) {
        if (!this.ensNom.contains(nom)) {
            this.ensNom.add(nom);
        }
    }

    /**
     * Permet d'ajouter un arc dans le graphe
     * Si le noeud de depart existe et le noeud d'arrivee existe, alors on ajoute l'arc au noeud de departw
     * Sinon si le noeud d'arrivee n'existe pas on le cree et on ajoute l'arc
     *
     * @param depart      nom du noeud de depart
     * @param destination nom du noeud de destination
     * @param cout        cout de l'arc
     */
    public void ajouterArc(String depart, String destination, double cout) {
        //si le nom du noeud de depart est bien dans la liste
        if (this.ensNom.contains(depart)) {
            //si la destination n'est pas deja dans la liste, on l'ajoute
            if (!this.ensNom.contains(destination)) {
                this.ensNom.add(destination);
            }

            //on recupere le noeud de depart
            Noeud noeudDepart = new Noeud(depart);
            //si la liste de noeud est vide, on ajoute le noeud de depart et on ajoute l'arc
            if (this.ensNoeuds.size() == 0) {
                this.ensNoeuds.add(noeudDepart);
                noeudDepart.ajouterArc(destination, cout);
            } else {
                // sinon on parcours la liste pour savoir si le noeud de depart existe deja
                boolean trouveNoeud = false;
                boolean trouveArc = false;
                for (Noeud noeud : this.ensNoeuds) {
                    if (noeud.getNom().equals(depart)) {
                        trouveNoeud = true;
                        //on parcours la liste des arcs du noeud de depart
                        for (Arc arc : noeud.getAdj()) {
                            if (arc.getDest().equals(destination)) {
                                trouveArc = true;
                                break;
                            }
                        }
                    }
                }
                //si le noeud de depart n'existe pas, on l'ajoute et on ajoute l'arc
                if (!trouveNoeud) {
                    this.ensNoeuds.add(noeudDepart);
                    noeudDepart.ajouterArc(destination, cout);
                } else {
                    //si l'arc n'existe pas deja, on l'ajoute
                    if (!trouveArc) {
                        noeudDepart.ajouterArc(destination, cout);
                    }
                }
            }
        }
    }

    public GrapheListe(List<String> ensNom, List<Noeud> ensNoeuds) {
        this.ensNom = ensNom;
        this.ensNoeuds = ensNoeuds;
    }

    @Override
    public List<String> listeNoeuds() {
        return ensNom;
    }

    @Override
    public List<Arc> suivants(String n) {
        for (Noeud noeud : ensNoeuds) {
            if (noeud.getNom().equals(n))
                return noeud.getAdj();
        }
        return null;
    }


    public String toGraphviz() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");
        for (Noeud noeud : ensNoeuds) {
            for (Arc arc : noeud.getAdj()) {
                sb.append(noeud.getNom()).append(" -> ")
                        .append(arc.getDest()).append(" [label = ")
                        .append((int) arc.getCout()).append("]\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Noeud noeud : this.ensNoeuds) {
            s.append(noeud.getNom()).append(" -> ");
            for (Arc arc : noeud.getAdj()) {
                s.append(arc).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}

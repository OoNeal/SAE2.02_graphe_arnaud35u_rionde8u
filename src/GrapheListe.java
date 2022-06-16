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
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();
    }
    public GrapheListe(String n) {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();
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
            this.ajouterArc(split[0], split[1], Double.parseDouble(split[2]));
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
        if (!this.ensNom.contains(depart)) {
            this.ensNom.add(depart);
            this.ensNoeuds.add(new Noeud(depart));
        }
        if (!this.ensNom.contains(destination)) {
            this.ensNom.add(destination);
            this.ensNoeuds.add(new Noeud(destination));
        }
        this.ensNoeuds.get(this.ensNom.indexOf(depart)).ajouterArc(destination, cout);
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
            for (Arc arc : noeud.getAdj()) {
                s.append(noeud.getNom()).append(" -> ");
                s.append(arc).append(" ");
                s.append("\n");
            }
        }
        return s.toString();
    }

}

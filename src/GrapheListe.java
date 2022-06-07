package src;

import javax.print.attribute.PrintRequestAttribute;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    private final List<String> ensNom;
    private final List<Noeud> ensNoeuds;

    public GrapheListe() {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();
    }

    /**
     * Constructeur qui prend un nom de fichier en parametre et construit le graphe
     *
     * @param nom nom du fichier a convertir
     */
    public GrapheListe(String nom) {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();
        this.construireGraphe(nom);
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
     * Permet d'ajouter
     * @param nom nom du noeud
     */
    public void ajouterNoeud(String nom) {
        if (!this.ensNom.contains(nom)) {
            this.ensNom.add(nom);
        }
    }

    public void ajouterArc(String depart, String destination, double cout) {
        //si le nom du noeud est bien dans la liste
        if (this.ensNom.contains(depart)) {
            //si la destination n'est pas deja dans la liste, on l'ajoute
            if (!this.ensNom.contains(destination)) {
                this.ensNom.add(destination);
            }

            //on recupere le noeud de depart
            Noeud noeud = new Noeud(depart);
            //si la liste de noeud est vide, on l'ajoute et on ajoute l'arc
            if (this.ensNoeuds.size() == 0) {
                this.ensNoeuds.add(noeud);
                noeud.ajouterArc(destination, cout);
            } else {
                // sinon on parcours la liste pour savoir si le noeud de depart existe deja
                boolean trouve = false;
                for (int i = 0; i < this.ensNoeuds.size() && !trouve; i++) {
                    if (this.ensNoeuds.get(i).equals(noeud)) {
                        this.ensNoeuds.get(i).ajouterArc(destination, cout);
                        trouve = true;
                    }
                }
                //si le noeud de depart n'existe pas, on l'ajoute et on ajoute l'arc
                if (!trouve) {
                    this.ensNoeuds.add(noeud);
                    noeud.ajouterArc(destination, cout);
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
        sb.append("digraph {\n");
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

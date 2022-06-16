package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    /**
     * Liste des noms des noeuds du graphe
     */
    private final List<String> ensNom;
    /**
     * Liste des noeuds du graphe
     */
    private final List<Noeud> ensNoeuds;

    /**
     * Constructeur de la classe GrapheListe
     */
    public GrapheListe() {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();
    }

    /**
     * Constructeur de la classe GrapheListe avec un fichier
     * @param fichier correspondant au chemin du fichier
    */
    public GrapheListe(String fichier) {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();
        this.construireGraphe(fichier);
    }

    /**
     * Methode permettant de construire un GrapheListe avec un fichier
     * @param fichier correspondant au chemin du fichier qui contient les informations du graphe
     */
    private void construireGraphe(String fichier) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fichier));
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
            String[] split2 = split[0].split("\t");
            try {
                this.ajouterArc(split[0], split[1], Double.parseDouble(split[2]));
            } catch (Exception ignored) {
            }
            try {
                this.ajouterArc(split2[0], split2[1], Double.parseDouble(split2[2]));
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Constructeur de la classe GrapheListe de manière aleatoire
     * @param depart correspondant au nom du noeud de depart
     * @param arrivee correspondant au nom du noeud d'arrivee
     * @param nbArcs correspondant au nombre d'arcs du graphe
     */
    public GrapheListe(String depart, String arrivee, int nbArcs) {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();
        genererGraphe(depart, arrivee, nbArcs);
    }

    //Question 24
    /**
     * Methode permettant de construire un GrapheListe de manière aleatoire
     * @param depart correspondant au nom du noeud de depart
     * @param arrivee correspondant au nom du noeud d'arrivee
     * @param nbArcs correspondant au nombre d'arcs du graphe
     */
    public void genererGraphe(String depart, String arrivee, int nbArcs) {
        String nom;
        int nbArcsArrives = (int) (nbArcs - Math.random() * nbArcs / 10);
        int nbNoeuds = (int) (Math.random() * nbArcs);
        double maxLettre = Math.random() * 6;
        while (nbNoeuds > nbArcs || nbNoeuds > 26 || nbNoeuds == 0 || nbNoeuds == 1) {
            nbNoeuds = (int) (Math.random() * nbArcs);
        }
        for (int i = 0; i < nbArcs; i++) {
            // On augmente la valeur de la lettre maximum petit a petit pour avoir un graphe moins dense
            maxLettre += 25.0 / nbArcs;
            // On chosit une lettre aleatoirement
            double lettre = Math.random() * maxLettre;
            if (maxLettre > 26) maxLettre = 21;
            nom = String.valueOf((char) (lettre + 'A'));
            // Si la lettre est egale a l'arrivee ou n'est pas compris entre les deux on rechoisit une lettre
            while (lettre < maxLettre - 5 || lettre > maxLettre + 5 && nom.equals(arrivee)) {
                lettre = Math.random() * maxLettre + 1;
                nom = String.valueOf((char) (lettre + 'A'));
            }
            //S'il n'y a pas d'arc on en rajoute un pour le noeud de départ
            if (i == 0) ajouterArc(depart, nom, Math.random() * 100);
                //Si i est inferieur à nbArcsArrives on ajoute un arc aleatoirement avec un noeud deja existant
            else if (i < nbArcsArrives)
                for (int j = this.ensNoeuds.size() - 1; j > 0; j--) {
                    if (Math.random() < 1.0 / j) {
                        System.out.println(this.ensNoeuds.get(j).getNom());
                        this.ajouterArc(nom, this.ensNoeuds.get(j).getNom(), Math.random() * 100);
                        break;
                    }
                } else ajouterArc(nom, arrivee, Math.random() * 100); //Sinon on ajoute un arc arrivant à la destination
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
        if (depart.equals(destination)) {
            return;
        }
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

    /**
     * Permet de retourner la liste de nom des noeuds du graphe
     *
     * @return la liste de nom des noeuds du graphe
     */
    @Override
    public List<String> listeNoeuds() {
        return ensNom;
    }

    /**
     * Permet de retourner la liste des noeuds du graphe
     *
     * @return la liste des noeuds du graphe
     */
    @Override
    public List<Arc> suivants(String n) {
        for (Noeud noeud : ensNoeuds) {
            if (noeud.getNom().equals(n))
                return noeud.getAdj();
        }
        return null;
    }

    /**
     * Convertie le graphe de manière a etre utilisable sur Graphviz
     * @return le graphe converti en String
     */
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

    /**
     * Methode convertissant le graphe en String
     * @return le graphe en String
     */
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

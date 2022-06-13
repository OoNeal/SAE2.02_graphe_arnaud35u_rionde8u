package test;

import org.junit.jupiter.api.Test;
import src.GrapheListe;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGraphe {

    @Test
    public void test_creerGraphe() {
        // Méthode testée
        GrapheListe g = new GrapheListe();

        // Vérification
        assertEquals(0, g.listeNoeuds().size());
    }

    @Test
    public void test_ajouterNoeud() {
        // Préparation des données
        GrapheListe g = new GrapheListe();

        // Méthode testée
        g.ajouterNoeud("A");

        // Vérification
        assertEquals("A", g.listeNoeuds().get(0));
    }

    @Test
    public void test_ajouterNoeud_dejaExistant() {
        // Préparation des données
        GrapheListe g = new GrapheListe();

        // Méthode testée
        g.ajouterNoeud("A");
        g.ajouterNoeud("A");

        // Vérification
        assertEquals(1, g.listeNoeuds().size());
    }

    @Test
    public void test_ajouterArc() {
        // Préparation des données
        GrapheListe g = new GrapheListe();

        // Méthode testée
        g.ajouterNoeud("A");
        g.ajouterNoeud("B");
        g.ajouterArc("A", "B", 12);

        // Vérification
        assertEquals(2, g.listeNoeuds().size());
        assertEquals("A -> B(12) \n", g.toString());
    }

    @Test
    public void test_ajouterArc_dejaExistant() {
        // Préparation des données
        GrapheListe g = new GrapheListe();

        // Méthode testée
        g.ajouterNoeud("A");
        g.ajouterNoeud("B");
        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "B", 12);

        // Vérification
        assertEquals(2, g.listeNoeuds().size());
        assertEquals("A -> B(12) \n", g.toString());
    }

    @Test
    public void test_ajouterArc_noeudDestinationInconnu() {
        // Préparation des données
        GrapheListe g = new GrapheListe();
        g.ajouterNoeud("A");

        // Méthode testée
        g.ajouterArc("A", "B", 12);

        // Vérification
        assertEquals(2, g.listeNoeuds().size());
        assertEquals("A -> B(12) \n", g.toString());
    }

    @Test
    public void test_ajouterArc_noeudInconnu() {
        // Préparation des données
        GrapheListe g = new GrapheListe();

        // Méthode testée
        g.ajouterArc("A", "B", 12);

        // Vérification
        assertEquals(0, g.listeNoeuds().size());
    }

    @Test
    public void test_toGraphviz() {
        // Préparation des données
        GrapheListe g = new GrapheListe();
        g.ajouterNoeud("A");
        g.ajouterNoeud("B");
        g.ajouterNoeud("C");
        g.ajouterNoeud("D");
        g.ajouterNoeud("E");
        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "D", 87);
        g.ajouterArc("B", "E", 11);
        g.ajouterArc("C", "A", 19);
        g.ajouterArc("D", "B", 23);
        g.ajouterArc("D", "C", 10);
        g.ajouterArc("E", "D", 43);

        // Méthode testée
        String graphe = g.toGraphviz();

        // Vérification
        assertEquals("digraph G {\nA -> B [label = 12]\nB -> E [label = 11]\nC -> A [label = 19]\nD -> B [label = 23]\nE -> D [label = 43]\n}", graphe);
    }
}

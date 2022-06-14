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
    public void test_ajouterArc() {
        // Préparation des données
        GrapheListe g = new GrapheListe();

        // Méthode testées
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
        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "B", 12);

        // Vérification
        assertEquals(2, g.listeNoeuds().size());
        assertEquals("A -> B(12) \n", g.toString());
    }

    @Test
    public void test_toGraphviz() {
        // Préparation des données
        GrapheListe g = new GrapheListe();
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
        assertEquals("digraph G {\nA -> B [label = 12]\nA -> D [label = 87]\nB -> E [label = 11]\nD -> B [label = 23]\nD -> C [label = 10]\nE -> D [label = 43]\nC -> A [label = 19]\n}", graphe);
    }
}

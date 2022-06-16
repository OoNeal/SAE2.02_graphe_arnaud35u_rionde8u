package test;

import org.junit.jupiter.api.Test;
import src.BellmanFord;
import src.GrapheListe;
import src.Valeur;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBellmanFord {

    @Test
    public void test_creerGraphe() {
        // Preparation
        GrapheListe g = new GrapheListe("document/Graphe_exemple1.txt");
        BellmanFord b = new BellmanFord();

        // Méthode testée
        Valeur v = b.resoudre(g, "A");

        // Verification
        assertEquals("A ->  V:0.0 p:null\nB ->  V:12.0 p:A\nC ->  V:76.0 p:D\nD ->  V:66.0 p:E\nE ->  V:23.0 p:B\n", v.toString());
    }
}

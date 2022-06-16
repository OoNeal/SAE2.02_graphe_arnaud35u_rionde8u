package src;

public class MainDijkstra {

    public static void main(String[] args) {
        GrapheListe g = new GrapheListe("document/Graphe_boucle.txt");

        System.out.println("/--------------------- ! Dijkstra ! --------------------\\");
        Dijkstra d = new Dijkstra();
        Valeur v = d.resoudre(g, "A");
        System.out.println(v);

        System.out.println("/--------------------- ! CalculerChemin ! --------------------\\");
        System.out.println(v.calculerChemin("E"));
        System.out.println(v.calculerChemin("D"));
    }
}

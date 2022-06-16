package src;

public class MainBellmanFord {

    public static void main(String[] args){
        GrapheListe g = new GrapheListe("document/Graphe_boucle.txt");

        System.out.println("/--------------------- ! BellmanFord ! --------------------\\");
        BellmanFord b = new BellmanFord();
        Valeur v = b.resoudre(g, "A");
        System.out.println(v);
    }
}

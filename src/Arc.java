package src;

public class Arc {
    /**
     * String dest correspondant au nom du noeud destination de l'arc
     */
    private String dest;

    /**
     * double cout correspond au coût de l'arc
     */
    private double cout;

    /**
     * Constructeur de la classe Arc
     * @param dest correspondant au nom du noeud destination de l'arc
     * @param cout correspond au coût de l'arc
        */
    public Arc(String dest, double cout) {
        this.dest = dest;
        if (cout > 0)
        {
            this.cout = cout;
        }
        else
        {
            this.cout = 0;
        }
    }
}

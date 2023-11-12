class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private int poids;
    private int age = 0;
    private String couleur;
    private int esperanceVie = ESPERANCE_VIE_DEFAUT;
    private boolean clonee = false;
    public Souris(int poids, String couleur) {
        this.poids = poids;
        this.couleur = couleur;
        System.out.println("Une nouvelle souris !");
    }
    public Souris(int poids, String couleur, int age) {
        this(poids, couleur);
        this.age = age;
    }
    public Souris(int poids, String couleur, int age, int esperanceVie) {
        this(poids, couleur, age);
        this.esperanceVie = esperanceVie;
    }
    public Souris(Souris souris) {
        this.poids = souris.poids;
        this.age = souris.age;
        this.couleur = souris.couleur;
        clonee = true;
        esperanceVie = 4 * souris.esperanceVie / 5;
        System.out.println("Clonage d'une souris !");
    }
    public String toString() {
        return "Une souris " + couleur + (clonee ? ", clonee, " : " ") + "de " + age + " mois et pesant " + poids + " grammes";
    }
    public void vieillir() {
        ++age;
        if (clonee && age > (esperanceVie / 2)) {
            couleur = "verte";
        }
    }

    public void evolue() {
        age = esperanceVie;
        if (clonee) {
            couleur = "verte";
        }
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}

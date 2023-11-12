import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2016;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private String code;
    private int annee;
    private double valeur;
    private String pays;
    public Timbre() {
        code = CODE_DEFAUT;
        annee = ANNEE_COURANTE;
        valeur = VALEUR_TIMBRE_DEFAUT;
        pays = PAYS_DEFAUT;
    }
    public Timbre(String code) {
        this();
        this.code = code;
    }
    public Timbre(String code, int annee) {
        this(code);
        this.annee = annee;
    }
    public Timbre(String code, int annee, String pays) {
        this(code, annee);
        this.pays = pays;
    }
    public Timbre(String code, int annee, String pays, double valeur) {
        this(code, annee, pays);
        this.valeur = valeur;
    }

    public double vente() {
        double prixVente;
        if(age() < 5) {
            prixVente = valeur;
        } else {
            prixVente = 2.5 * valeur * age();
        }
        return prixVente;
    }
    public String toString() {
        return "Timbre de code " + code + " datant de " + annee + " (provenance " + pays +") ayant pour valeur faciale " + valeur + " francs";
    }
    public int age() {
        return ANNEE_COURANTE - annee;
    }

    public double getValeurFaciale() {
        return valeur;
    }

    public int getAnnee() {
        return annee;
    }

    public String getCode() {
        return code;
    }

    public String getPays() {
        return pays;
    }
}

class Rare extends Timbre {
    private int nbExemplaires;
    public Rare(int nbExemplaires) {
        super();
        this.nbExemplaires = nbExemplaires;
    }
    public Rare(String code, int nbExemplaires) {
        super(code);
        this.nbExemplaires = nbExemplaires;

    }
    public Rare(String code, int annee, int nbExemplaires) {
        super(code, annee);
        this.nbExemplaires = nbExemplaires;
    }
    public Rare(String code, int annee, String pays, int nbExemplaires) {
        super(code, annee, pays);
        this.nbExemplaires = nbExemplaires;
    }
    public Rare(String code, int annee, String pays, double valeur, int nbExemplaires) {
        super(code, annee, pays, valeur);
        this.nbExemplaires = nbExemplaires;
    }

    public int getExemplaires() {
        return nbExemplaires;
    }
    public String toString() {
        return super.toString() + "\nNombre d'exemplaires -> " + nbExemplaires;
    }
    public double vente() {
        double prixVente;
        if (nbExemplaires < 100)  {
            prixVente = PRIX_BASE_1;
        } else if (nbExemplaires < 1000) {
            prixVente = PRIX_BASE_2;
        } else {
            prixVente = PRIX_BASE_3;
        }
        prixVente = prixVente * (age() / 10.0);
        return prixVente;
    }
}

class Commemoratif extends Timbre {
    public Commemoratif() {
        super();
    }
    public Commemoratif(String code) {
        super(code);
    }
    public Commemoratif(String code, int annee) {
        super(code, annee);
    }
    public Commemoratif(String code, int annee, String pays) {
        super(code, annee, pays);
    }
    public Commemoratif(String code, int annee, String pays, double valeur) {
        super(code, annee, pays, valeur);
    }
    public String toString() {
        return super.toString() + "\nTimbre celebrant un evenement";
    }
    public double vente() {
        return super.vente() * 2;
    }
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}


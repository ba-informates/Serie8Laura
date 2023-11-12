import java.util.ArrayList;

// PROGRAMME PRINCIPAL (A NE PAS MODIFIER)
class Poste {

	public static void  main(String args[]) {
		//Cr'eation d'une boite-aux-lettres
		// 30  est la capacit'e maximale de la
		// boite aux lettres
		// (pas necessaire si vous dêcidez d'utiliser
		// un ArrayList).
		Boite boite = new Boite(30);

		//Creation de divers courriers/colis..
		Lettre lettre1 = new Lettre(200, true, "Chemin des Acacias 28, 1009 Pully", "A3");
		Lettre lettre2 = new Lettre(800, false, "", "A4"); // invalide

		Publicite pub1 = new Publicite(1500, true, "Les Moilles  13A, 1913 Saillon");
		Publicite pub2 = new Publicite(3000, false, ""); // invalide

		Colis colis1 = new Colis(5000, true, "Grand rue 18, 1950 Sion", 30);
		Colis colis2 = new Colis(3000, true, "Chemin des fleurs 48, 2800 Delemont", 70); //Colis invalide !

		boite.ajouterCourrier(lettre1);
		boite.ajouterCourrier(lettre2);
		boite.ajouterCourrier(pub1);
		boite.ajouterCourrier(pub2);
		boite.ajouterCourrier(colis1);
		boite.ajouterCourrier(colis2);


		System.out.println("Le montant total d'affranchissement est de " +
						   boite.affranchir());
		boite.afficher();
		
		System.out.println("La boite contient " + boite.courriersInvalides()
						   + " courriers invalides");
	}
}

class Boite {
	private Courrier[] contenuBoite;
	private double montantAff = 0;
	private int courrierInv = 0;
	public Boite (int capaMax) {
		contenuBoite = new Courrier[capaMax];
	}
	public void ajouterCourrier (Courrier courrier) {
		for (int i = 0; i < contenuBoite.length; ++i) {
			if (contenuBoite[i] == null) {
				contenuBoite[i] = courrier;
				break;
			}
		}
	}
	public double affranchir() {
		for (int i = 0; i < contenuBoite.length && contenuBoite[i] != null; ++i) {
			contenuBoite[i].affranchir();
			montantAff += contenuBoite[i].getPrix();
		}
		return montantAff;
	}
	public int courriersInvalides() {
		for (int i = 0; i < contenuBoite.length && contenuBoite[i] != null; ++i) {
			if (!contenuBoite[i].isValide())
				++courrierInv;
		}
		return courrierInv;
	}
	public void afficher() {
		for (int i = 0; i < contenuBoite.length && contenuBoite[i] != null; ++i) {
			contenuBoite[i].afficher();
		}
	}
}

class Courrier {
	private int poids;
	private boolean express;
	private String adresse;
	private boolean valide = true;
	private double prix = 0;
	public Courrier (int poids, boolean express, String adresse) {
		this.adresse = adresse;
		this.express = express;
		this.poids = poids;
		if (adresse.isEmpty())
			valide = false;
	}

	protected boolean isValide() {
		return valide;
	}

	protected void setValide(boolean valide) {
		this.valide = valide;
	}

	protected void affranchir() {
		if (express) {
			prix *= 2;
		}
	}

	protected void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	protected int getPoids() {
		return poids;
	}
	protected void afficher() {
		if (!valide)
			System.out.println("(Courrier invalide)");
		System.out.println("    Poids : " + poids + " grammes");
		System.out.println("    Express : " + (express ? "oui" : "non"));
		System.out.println("    Destination : " + adresse);
		System.out.println("    Prix : " + prix + " CHF");
	}
}

class Lettre extends Courrier{
	private String format;
	public Lettre(int poids, boolean express, String adresse, String format) {
		super(poids, express, adresse);
		this.format = format;
	}
	protected void affranchir() {
		if (isValide()) {
			if (format.equals("A3"))
				setPrix(3.50 + getPoids() / 1000.0);
			else if (format.equals("A4"))
				setPrix(2.50 + getPoids() / 1000.0);
		}
		super.affranchir();
	}
	protected void afficher() {
		System.out.println("Lettre");
		super.afficher();
		System.out.println("    Format : " + format);
	}
}

class Publicite extends Courrier {
	public Publicite (int poids, boolean express, String adresse) {
		super(poids, express, adresse);
	}
	protected void affranchir() {
		if (isValide())
			setPrix(5 * getPoids() / 1000.0);
		super.affranchir();
	}
	protected void afficher() {
		System.out.println("Publicité");
		super.afficher();
	}
}

class Colis extends Courrier {
	private int volume;
	public Colis (int poids, boolean express, String adresse, int volume) {
		super(poids, express, adresse);
		this.volume = volume;
		if (volume > 50)
			setValide(false);
	}
	protected void affranchir() {
		if (isValide())
			setPrix(0.25 * volume + getPoids() / 1000.0);
		super.affranchir();
	}
	protected void afficher() {
		System.out.println("Colis");
		super.afficher();
		System.out.println("    Volume : " + volume + " litres");
	}
}
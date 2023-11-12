class GestionVehicules {
	// Pour repréesenter l'année courante:
	// Il est aussi bien sur possible d'utiliser la classe Date
	// pour r'ecupérer cette information automatiquement.
	private static int ANNEE_ACTUELLE = 2020;	
		
	public static void main(String[] args) {
		Voiture[] garage = new Voiture[3];
		Avion[] hangar = new Avion[2];

		garage[0] = new Voiture("Peugeot", 2005, 147325.79, 2.5, 5, 180.0, 12000);
		garage[1] = new Voiture("Porsche", 1999, 250000.00, 6.5, 2, 280.0, 81320);
		garage[2] = new Voiture("Fiat", 2001, 7327.30, 1.6, 3, 65.0, 3000);

		hangar[0] = new Avion("Cessna", 1982, 1230673.90, "HELICES", 250);
		hangar[1] = new Avion("Nain Connu", 1993, 4321098.00, "REACTION", 1300);

		for (int i = 0; i < garage.length; i++) {
			garage[i].calculePrix(ANNEE_ACTUELLE);
			garage[i].affiche();
		}

		for (int i = 0; i < hangar.length; i++) {
			hangar[i].calculePrix(ANNEE_ACTUELLE);
			hangar[i].affiche();
		}
	}
}

class Vehicule {

	private String marque;
	private int anneeAchat;
	private double prixAchat;
	private double prixCourant;

	public Vehicule (String marque, int anneeAchat, double prixAchat) {
		this.marque = marque;
		this.anneeAchat = anneeAchat;
		this.prixAchat = prixAchat;
	}

	public void affiche() {
		System.out.println("Marque : " + marque);
		System.out.println("Année d'achat : " + anneeAchat);
		System.out.println("Prix courant : " + prixCourant);
	}

	public void calculePrix (int anneeActuelle) {
		prixCourant = prixAchat / 100 * (100 - (anneeActuelle - anneeAchat));
		if (prixCourant < 0)
			prixCourant = 0;
	}

	public void setPrixCourant(double prixCourant) {
		this.prixCourant = prixCourant;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public int getAnneeAchat() {
		return anneeAchat;
	}

	public String getMarque() {
		return marque;
	}
}

class Voiture extends Vehicule {

	private double cylindree;
	private int nbPorte;
	private double puissance;
	private int kilometrage;
	public Voiture (String marque, int anneeAchat, double prixAchat, double cylindree, int nbPorte, double puissance, int kilometrage) {
		super(marque, anneeAchat, prixAchat);
		this.cylindree = cylindree;
		this.kilometrage = kilometrage;
		this.nbPorte = nbPorte;
		this.puissance = puissance;
	}

	@Override
	public void affiche() {
		System.out.println("---Voiture----");
		super.affiche();
		System.out.println("Cylindrée : " + cylindree);
		System.out.println("Nombre de portes : " + nbPorte);
		System.out.println("Puissance : " + puissance);
		System.out.println("Kilométrage : " + kilometrage);
	}

	public void calculePrix(int anneeActuelle) {
		double prixCourant = getPrixAchat() / 100 * (100 - 2*(anneeActuelle - getAnneeAchat()));
		prixCourant -= (getPrixAchat() / 100) * (5 * (kilometrage / 10000));
		if (getMarque().equals("Renault") || getMarque().equals("Fiat")) {
			prixCourant -= getPrixAchat() / 10;
		} else if (getMarque().equals("Ferrari") || getMarque().equals("Porsche")) {
			prixCourant += getPrixAchat() / 5;
		}
		if (prixCourant < 0)
			prixCourant = 0;
		setPrixCourant(prixCourant);
	}
}

class Avion extends Vehicule {
	private String typeMoteur;
	private int nbHeureVol;
	public Avion (String marque, int anneeAchat, double prixAchat, String typeMoteur, int nbHeureVol) {
		super(marque, anneeAchat, prixAchat);
		this.nbHeureVol = nbHeureVol;
		this.typeMoteur = typeMoteur;
	}
	@Override
	public void affiche() {
		System.out.println("---Avion----");
		super.affiche();
		System.out.println("Type de moteur : " + typeMoteur);
		System.out.println("Nombre d'heures de vol : " + nbHeureVol);
	}

	@Override
	public void calculePrix(int anneeActuelle) {
		double prixCourant;
		if (typeMoteur.equals("HELICES")) {
			prixCourant = (getPrixAchat() / 100) * (100 - 10*(nbHeureVol / 100));
		} else {
			prixCourant = (getPrixAchat() / 100) * (100 - 10*(nbHeureVol / 1000));
		}
		if (prixCourant < 0)
			prixCourant = 0;
		setPrixCourant(prixCourant);
	}
}

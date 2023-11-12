import java.util.ArrayList;

public class Direction {
    public static void main(String[] args) {
        EtudiantRegulier EPFLien1 = new EtudiantRegulier("Gaston Peutimide", 2020, "SSC", 6.0);
        EtudiantRegulier EPFLien2 = new EtudiantRegulier("Yvan Rattrapeur", 2016, "SSC", 2.5);
        EtudiantEchange EPFLien3 = new EtudiantEchange("Bjorn Borgue", 2018, "Informatique", "KTH");
        Enseignant EPFLien4 = new Enseignant("Matthieu Matheu",2015, "LMEP", 10000, "Physique");
        Secretaire EPFLien5 = new Secretaire("Sophie Scribona", 2005, "LMT", 5000);
        EPFLien1.affichageBase();
        EPFLien1.affichage();
        EPFLien2.affichage();
        EPFLien3.affichage();
        EPFLien4.affichage();
        EPFLien5.affichage();
    }
}
class EPFLiens {
    private String name;
    private int year;
    private static ArrayList<EPFLiens> members = new ArrayList<>();
    private static int nbEtudiants;
    private static double yearTot;

    public EPFLiens (String name, int year) {
        this.name = name;
        this.year = year;
        addMember(this);
    }

    public void addMember (EPFLiens member) {
        members.add(member);
        if (member instanceof EtudiantRegulier || member instanceof EtudiantEchange)
            nbEtudiants++;
        yearTot += 2023 - member.year;
    }
    public void affichageBase() {
        System.out.println("Parmis les " + members.size() + " EPFLiens, il y a " + nbEtudiants + " étudiants");
        System.out.println("Ils sont ici en moyenne depuis " + (yearTot/members.size()) + " ans");
        System.out.println("Liste des EPFLiens :");
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
}
class EtudiantRegulier extends EPFLiens {
    private String section;
    private double result;
    public EtudiantRegulier (String name, int year, String section, double result) {
        super(name, year);
        this.section = section;
        this.result = result;
    }

    public void affichage() {
        System.out.println("Etudiant régulier");
        System.out.println("    Nom : " + this.getName());
        System.out.println("    Année : " + this.getYear());
        System.out.println("    Section : " + this.section);
        System.out.println("   Moyenne : " + this.result);
    }
}

class Secretaire extends EPFLiens {
    private String lab;
    private double salary;
    public Secretaire (String name, int year, String lab, double salary) {
        super(name, year);
        this.lab = lab;
        this.salary = salary;
    }
    public void affichage() {
        if (this instanceof Enseignant)
            System.out.println("Enseignant");
        else
            System.out.println("Secrétaire");
        System.out.println("    Nom : " + this.getName());
        System.out.println("    Année : " + this.getYear());
        System.out.println("    Labortoire : " + this.lab);
        System.out.println("    Salaire : " + this.salary);
    }
}

class EtudiantEchange extends EPFLiens {
    private String section;
    private String uni;
    public EtudiantEchange (String name, int year, String section, String uni) {
        super(name, year);
        this.section = section;
        this.uni = uni;
    }

    public void affichage() {
        System.out.println("Etudiant en échange");
        System.out.println("    Nom : " + this.getName());
        System.out.println("    Année : " + this.getYear());
        System.out.println("    Section : " + this.section);
        System.out.println("    Uni d'origine : " + this.uni);
    }
}

class Enseignant extends Secretaire {
    private String section;
    public Enseignant (String name, int year, String lab, double salary, String section) {
        super(name, year, lab, salary);
        this.section = section;
    }
    public void affichage() {
        super.affichage();
        System.out.println("    Section d'enseignement : " + this.section);
    }
}

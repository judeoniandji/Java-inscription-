import java.io.Serializable;
import java.util.ArrayList;

public class Professeur implements Serializable {
    private String nom;
    private String prenom;
    private ArrayList<String> matieres;
    private String grade;
    private String numeroTelephone;
    private String disponibilite;

    public Professeur(String nom, String prenom, ArrayList<String> matieres, String grade, String numeroTelephone, String disponibilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.matieres = matieres;
        this.grade = grade;
        this.numeroTelephone = numeroTelephone;
        this.disponibilite = disponibilite;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public ArrayList<String> getMatieres() {
        return matieres;
    }

    public void setMatieres(ArrayList<String> matieres) {
        this.matieres = matieres;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", matieres=" + matieres +
                ", grade='" + grade + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", disponibilite='" + disponibilite + '\'' +
                '}';
    }
}

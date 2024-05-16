import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static ArrayList<Professeur> professeurs = new ArrayList<>();
    private static ArrayList<Cours> cours = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void ajouterProfesseur(String nom, String prenom, ArrayList<String> matieres, String grade, String numeroTelephone, String disponibilite) {
        for (Professeur prof : professeurs) {
        
            if (prof.getNom().equals(nom) && prof.getNumeroTelephone().equals(numeroTelephone)) {
                System.out.println("Ce professeur existe déjà.");
                return;
            }
        }
    
        professeurs.add(new Professeur(nom, prenom, matieres, grade, numeroTelephone, disponibilite));
        sauvegarderDonnees();
    }

    public static void listerProfesseurs() {
        if (professeurs.isEmpty()) {
            System.out.println("Aucun professeur enregistré.");
            return;
        }
        for (Professeur prof : professeurs) {
            System.out.println(prof);
        }
    }

    public static void creerCours(int id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, Professeur professeur, String salle) {
        for (Cours c : cours) {
            if (c.getId() == id) {
                System.out.println("Ce cours existe déjà.");
                return;
            }
        }

        cours.add(new Cours(id, date, heureDebut, heureFin, professeur, salle));
        sauvegarderDonnees();
    }

    public static void listerCours() {
        Collections.sort(cours, Comparator.comparing(Cours::getDate));
        for (Cours c : cours) {
            System.out.println(c);
        }
    }

    public static void listerCoursProfesseur(String nomProfesseur) {
        for (Cours c : cours) {
            if (c.getProfesseur().getNom().equals(nomProfesseur)) {
                System.out.println(c);
            }
        }
    }

    public static void sauvegarderDonnees() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("donnees.dat"));
            oos.writeObject(professeurs);
            oos.writeObject(cours);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void chargerDonnees() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("donnees.dat"));
            professeurs = (ArrayList<Professeur>) ois.readObject();
            cours = (ArrayList<Cours>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
        
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
            professeurs = new ArrayList<>();
            cours = new ArrayList<>();
        }
    }

    public static void menu() {
        while (true) {
            System.out.println("Choisissez une action à effectuer :");
            System.out.println("1. Ajouter un professeur");
            System.out.println("2. Lister les professeurs");
            System.out.println("3. Créer un cours");
            System.out.println("4. Lister tous les cours");
            System.out.println("5. Lister les cours d'un professeur");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom du professeur : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom du professeur : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Matières enseignées par le professeur (séparées par des virgules) : ");
                    ArrayList<String> matieres = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));
                    System.out.print("Grade du professeur : ");
                    String grade = scanner.nextLine();
                    System.out.print("Numéro de téléphone du professeur : ");
                    String numeroTelephone = scanner.nextLine();
                    System.out.print("Disponibilité du professeur : ");
                    String disponibilite = scanner.nextLine();
                    ajouterProfesseur(nom, prenom, matieres, grade, numeroTelephone, disponibilite);
                    break;
                case 2:
                    listerProfesseurs();
                    break;
                case 3:
                    System.out.print("ID du cours : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Date du cours (format YYYY-MM-DD) : ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Heure de début du cours (format HH:MM) : ");
                    LocalTime heureDebut = LocalTime.parse(scanner.nextLine());
                    System.out.print("Heure de fin du cours (format HH:MM) : ");
                    LocalTime heureFin = LocalTime.parse(scanner.nextLine());
                    System.out.print("Nom du professeur : ");
                    String nomProfesseur = scanner.nextLine();
                    System.out.print("Salle du cours : ");
                    String salle = scanner.nextLine();
                    creerCours(id, date, heureDebut, heureFin, new Professeur(nomProfesseur, "", new ArrayList<>(), "", "", ""), salle);
                    break;
                case 4:
                    listerCours();
                    break;
                case 5:
                    System.out.print("Nom du professeur : ");
                    String nomProf = scanner.nextLine();
                    listerCoursProfesseur(nomProf);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    public static void main(String[] args) {

        chargerDonnees();

        
        menu();
    }
}

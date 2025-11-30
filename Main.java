import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        ArrayList<Joueur> joueurs = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.print("Donner le nombre maximal de joueurs qui peuvent enregistrer  ");
        n = s.nextInt();
        while (true) {
            for (int i = 0; i < n; i++) {
                System.out.print("Nom joueur " + (i + 1) + "= ");
                String nom = s.next();
                System.out.print("Prenom joueur " + (i + 1) + "= ");
                String prenom = s.next();
                System.out.print("Age joueur " + (i + 1) + "= ");
                int age = s.nextInt();
                Joueur joueur = new Joueur(nom, prenom, age);
                for (Joueur j : joueurs) {
                    if (j.getNom().equals(joueur.getNom()) && j.getPrenom().equals(joueur.getPrenom())
                            && j.getAge() == joueur.getAge()) {
                        joueur = j;
                    }
                }
                int k = joueurs.indexOf(joueur);
                System.out.println(k);
                if (k != -1 && k + 3 <= joueurs.size()) {
                    joueur.setVie(4);
                }
                joueurs.add(joueur);
                afficherMenu1();
                int x = s.nextInt();
                while (true) {
                    switch (x) {
                        case 0:
                            joueur.message_enregistrement(s);
                            break;
                        case 1:
                            joueur.message_connexion(s);
                            break;
                        case 2:
                            joueur.jouer(s);
                            break;
                        case 3:
                            joueur.message_deconnexion();
                            break;
                        case 4:
                            System.out.println("Votre score = " + joueur.getScore());
                            break;
                        case 5:
                            System.out.println("Vos essais pour jouer = " + joueur.getVie());
                            break;
                        case 6:
                            System.out.println("Votre niveau = " + joueur.getNiveau());
                            break;
                        case 7:
                            System.out.println("- * : Définitivement");
                            System.out.println("- + : Resté enregistré");
                            char y = s.next().charAt(0);
                            if (y == '*') {
                                joueurs.remove(joueur);
                                joueur.setEnregistre(false);
                            }
                            joueur.message_Quitter();
                            break;
                    }
                    boolean test = false;
                    if (!(joueur.connecte())) {
                        test = true;
                    }
                    if (x == 7 && test) {
                        break;
                    }
                    afficherMenu1();
                    x = s.nextInt();
                }
            }
            if (joueurs.size() == n && jeuTermine(joueurs)) {
                System.out.println("Jeu Terminé !!");
                break;
            }
        }

        ArrayList<Joueur> ListeEnregistres = new ArrayList<Joueur>();
        ArrayList<Joueur> ListeJoueursAyantJouer = new ArrayList<Joueur>();
        ArrayList<Integer> ListeScores = new ArrayList<Integer>();
        ArrayList<Integer> ListeNiveaux = new ArrayList<Integer>();
        ArrayList<Joueur> ListeJoueursGagnants = new ArrayList<Joueur>();
        ArrayList<Joueur> ListeJoueursPerdants = new ArrayList<Joueur>();
        for (Joueur j : joueurs) {
            if (j.enregistre()) {
                ListeEnregistres.add(j);
            }
        }
        for (Joueur j : joueurs) {
            if (j.jouer()) {
                ListeJoueursAyantJouer.add(j);
            }
        }
        for (Joueur j : joueurs) {
            ListeScores.add(j.getScore());
        }
        for (Joueur j : joueurs) {
            ListeNiveaux.add(j.getNiveau());
        }
        int max_niveau = 0;
        for (int niveau : ListeNiveaux) {
            if (max_niveau < niveau) {
                max_niveau = niveau;
            }
        }
        int max_score = 0;
        for (int score : ListeScores) {
            if (max_score < score) {
                max_score = score;
            }
        }
        for (Joueur j : joueurs) {
            if (j.getNiveau() == max_niveau && j.getScore() == max_score) {
                ListeJoueursGagnants.add(j);
            }
            ListeJoueursPerdants.add(j);
        }
        afficherMenu2();
        int z = s.nextInt();
        while (z != 6) {
            afficherMenu2();
            z = s.nextInt();
            switch (z) {
                case 0:
                    System.out.println(ListeEnregistres);
                    break;
                case 1:
                    System.out.println(ListeJoueursAyantJouer);
                    break;
                case 2:
                    System.out.println(ListeScores);
                    break;
                case 3:
                    System.out.println(ListeNiveaux);
                    break;
                case 4:
                    System.out.println(ListeJoueursGagnants);
                    break;
                case 5:
                    System.out.println(ListeJoueursPerdants);
                    break;
                case 6:
                    System.out.println("Vous avez quitter ");
                    break;
            }
        }
        s.close();
    }

    public static void afficherMenu1() {
        System.out.println("- 0 : Enregistrer");
        System.out.println("- 1 : Connecter");
        System.out.println("- 2 : Jouer");
        System.out.println("- 3 : Déconnecter");
        System.out.println("- 4 : Afficher le score");
        System.out.println("- 5 : Afficher le nombre des essais encore valables pour jouer");
        System.out.println("- 6 : Afficher le niveau");
        System.out.println("- 7 : Quitter");
    }

    public static void afficherMenu2() {
        System.out.println("- 0 : Afficher la liste de joueurs enregistrés ");
        System.out.println("- 1 : Afficher la liste de joueurs ayant au moins jouer à un jeu ");
        System.out.println("- 2 : Afficher le score de chaque joueur ");
        System.out.println("- 3 : Afficher le niveau de chaque joueur ");
        System.out.println("- 4 : Afficher la liste de joueurs gagnants ");
        System.out.println("- 5 : Afficher la liste de joueurs perdants ");
        System.out.println("- 6 : Quitter  ");
    }

    public static boolean jeuTermine(ArrayList<Joueur> joueurs) {
        int n = 0;
        for (Joueur j : joueurs) {
            if (j.getVie() == 0) {
                n++;
            }
        }
        if (n == joueurs.size()) {
            return true;
        }
        return false;
    }

}

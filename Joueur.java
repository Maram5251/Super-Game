import java.util.Scanner;

public class Joueur {
    private String nom;
    private String prenom;
    private int age;
    private static int cmp = 0;
    private int numero;
    private int niveau = 1;
    private int Score = 0;
    private int vie = 4;
    private int min_score_niveau = 10;
    private boolean gagnant = false;
    private boolean connecte = false;
    private boolean enregistre = false;
    private boolean jouer = false;
    private String mot_de_passe;

    public Joueur(String nom, String prenom, int age) {
        cmp++;
        numero = cmp;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEnregistre(boolean enregistre) {
        this.enregistre = enregistre;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public boolean enregistre() {
        return (this.enregistre);
    }

    public boolean connecte() {
        return (this.connecte);
    }

    public boolean jouer() {
        return (this.jouer);
    }

    public int getVie() {
        return (this.vie);
    }

    public String getMp() {
        return (this.mot_de_passe);
    }

    public void setScore(int score) {
        this.Score = score;
    }

    public String getNom() {
        return (this.nom);
    }

    public String getPrenom() {
        return (this.prenom);
    }

    public int getAge() {
        return (this.age);
    }

    public int getNum() {
        return (this.numero);
    }

    public int getScore() {
        return (this.Score);
    }

    public int getNiveau() {
        return (this.niveau);
    }

    public void message_gagnant() {
        if (gagnant && connecte && enregistre) {
            System.out.println("Félicitations" + this.nom + this.prenom + ", Vous avez gagner , votre niveau = "
                    + this.niveau + " et votre score = " + this.Score);
        } else {
            System.out.println("Désolé ," + this.nom + this.prenom + " Vous avez perdu , votre niveau = " + this.niveau
                    + " et votre score = " + this.Score);
        }
    }

    public void message_enregistrement(Scanner s) {
        if (!enregistre && mot_de_passe == null) {
            if (age >= 12) {
                System.out.println("Donner un mot de passe ");
                this.mot_de_passe = s.next();
                System.out.println("Bonjour ! " + this.nom + " " + this.prenom
                        + ", Soyez le bienvenu ! vous etes désormais enregistré en tant que joueur");
                enregistre = true;
            } else {
                System.out.println("Désolé " + this.nom + " " + this.prenom
                        + ", Vous n'avez pas l'autorisation d'enregistrer , vous etes <12 (seulement " + this.age
                        + ")");
            }
        } else {
            System.out.println("Bonjour ! " + this.nom + " " + this.prenom
                    + "  Vous etes déjà enregistrés , Vérifiez si vous etes bien connectés ");
            enregistre = true;
        }
    }

    public void message_connexion(Scanner s) {
        if (!enregistre) {
            System.out.println("Bonjour ! " + this.nom + " " + this.prenom + "svp vous devez enregistrer d'abord");
        } else {
            if (connecte) {
                System.out.println("Bonjour ! " + this.nom + " " + this.prenom + " vous etes déjà connecté ");
            } else {
                System.out.println("Vous etes enregistrés , Donnez votre mot de passe pour pouvoir connecter ");
                while (true) {
                    String mp = s.next();
                    if ((enregistre && this.mot_de_passe.equals(mp))) {
                        System.out.println(
                                "Bonjour ! " + this.nom + " " + this.prenom + " vous etes désormais connecté ");
                        connecte = true;
                        break;
                    } else {
                        System.out.println("mot de passe invalide , Esaayer encore");
                    }
                }
            }
        }
    }

    public void message_deconnexion() {
        if (!enregistre) {
            System.out
                    .println("Bonjour ! " + this.nom + " " + this.prenom + "Vous etes non enregistré et non connecté ");
        } else {
            if (connecte) {
                System.out.println("Bonjour ! " + this.nom + " " + this.prenom + " vous etes déconnecté ");
                connecte = false;
            } else {
                System.out.println("Bonjour ! " + this.nom + " " + this.prenom + " vous etes déjà non connecté ");
            }
        }
    }

    public void message_Quitter() {
        if (enregistre) {
            if (connecte) {
                System.out.println(
                        " " + this.nom + " " + this.prenom + " Vous devez déconnecter d'abord pour pouvoir quitter ");
            } else {
                System.out.println("Au revoir ! " + this.nom + " " + this.prenom
                        + ", Vous avez quitter en tant que joueur en restant enregistré ");
            }
        } else {
            System.out.println(" " + this.nom + " " + this.prenom
                    + ", Vous avez quitter , Vous pouvez enregistrer pour pouvoir accéder aux jeux  ");
        }
    }

    public void jouer(Scanner s) {
        boolean Quitter = false;
        if (connecte) {
            while (this.vie > 0 && !Quitter) {
                System.out.println("Bienvenue , Vous pouvez choisir parmi les jeux suivantes");
                System.out.println("- a : Jeu deviner un nombre");
                System.out.println("- b : Jeu de mémoire");
                System.out.println("- : Quitter les jeux");
                char y = s.next().charAt(0);

                switch (y) {
                    case 'a':
                        System.out.println("Jeu deviner un nombre");
                        System.out.println("Donner le nombre maximal");
                        int nombre = s.nextInt();
                        Jeu jeu1 = new Jeu(nombre, this);
                        jeu1.devinerNombre(s);
                        jouer = true;
                        break;
                    case 'b':
                        System.out.println("jeu de mémoire");
                        Jeu jeu2 = new Jeu(this);
                        jeu2.jeuDeMemoire(s);
                        jouer = true;
                        break;
                    default:
                        Quitter = true;
                        break;
                }
            }
            if (this.vie == 0) {
                System.out.println("Désolé " + this.nom + " " + this.prenom
                        + " , Vous n'avez pas encore de essais valables pour jouer..Laissez l'opportunité aux 3 autres joueurs ");
            }
        } else {
            System.out.println("Désolé , Vous n'etes pas encore connecté");
        }
    }

    public void passerAuNiveauSuivant() {
        if (enregistre && connecte && (Score > min_score_niveau)) {
            niveau++;
            min_score_niveau += 10;
            Score = 0;
            System.out.println(
                    "Félicitations ! " + this.nom + " " + this.prenom + " Vous avez passez au niveau " + this.niveau);
            this.vie += 1;
        } else {
            System.out.println("Score insuffisant pour passer au niveau suivant.");
            this.vie -= 1;
        }
    }
}
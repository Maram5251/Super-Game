import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Jeu {
    private int nombre_a_deviner;
    private int nombre;
    private int nombre_max;
    private Joueur joueur;

    public Jeu(Joueur joueur) {
        this.joueur = joueur;
    }

    public Jeu(int nombre_max, Joueur joueur) {
        this.nombre_max = nombre_max;
        this.nombre_a_deviner = (int) (Math.random() * nombre_max);
        this.joueur = joueur;
    }

    public void devinerNombre(Scanner s) {
        System.out.println("Donner un nombre entre 0 et " + this.nombre_max);
        nombre = s.nextInt();
        while (true) {
            if (nombre < nombre_a_deviner) {
                System.out.println("Le nombre donné est plus petit que le nombre à deviner, Essayer encore une fois");
                System.out.println("Donner un nombre entre 0 et " + this.nombre_max);
                nombre = s.nextInt();
                joueur.setScore(joueur.getScore() - 2);
                System.out.println("Score - 2");
            } else if (nombre > nombre_a_deviner) {
                System.out.println("Le nombre donné est plus grand que le nombre à deviner, Essayer encore une fois");
                System.out.println("Donner un nombre entre 0 et " + this.nombre_max);
                nombre = s.nextInt();
                joueur.setScore(joueur.getScore() - 3);
                System.out.println("Score -3");
            } else {
                System.out.println("Félicitations ! " + this.joueur.getNom() + " " + this.joueur.getPrenom()
                        + " Vous avez devinez le nombre " + this.nombre_a_deviner);
                joueur.setScore(joueur.getScore() + 5);
                System.out.println("Score +5");
                break;
            }
        }
        joueur.passerAuNiveauSuivant();
    }

    public void jeuDeMemoire(Scanner s) {
        ArrayList<String> images_valables = new ArrayList<String>();
        ArrayList<String> List1 = new ArrayList<String>();
        ArrayList<String> List2 = new ArrayList<String>();
        boolean[] matched1 = new boolean[4];
        boolean[] matched2 = new boolean[4];
        for (int i = 0; i < 4; i++) {
            images_valables.add("image" + i);
        }

        Collections.shuffle(images_valables);
        List1.addAll(images_valables);
        List2.addAll(images_valables);
        Collections.shuffle(List2);
        System.out.println(List1);
        System.out.println(List2);
        int tentatives = 0;
        int choix_correctes = 0;
        while (choix_correctes < 4) {
            System.out.println("Donner un indice (0-3)");
            int x1 = s.nextInt();
            while (x1 < 0 || x1 > 3 || matched1[x1]) {
                System.out.println("Indice invalide ou déjà utilisé, essayez encore:");
                x1 = s.nextInt();
            }
            System.out.println("Donner un indice (0-3) ");
            int x2 = s.nextInt();
            while (x2 < 0 || x2 > 3 || matched2[x2]) {
                System.out.println("Indice invalide ou déjà utilisé, essayez encore:");
                x2 = s.nextInt();
            }
            if (List1.get(x1).equals(List2.get(x2))) {
                System.out.println("Choix correcte  " + List1.get(x1) + "  dans les positions  " + x1 + " " + x2);
                matched1[x1] = true;
                matched2[x2] = true;
                choix_correctes++;
            } else {
                System.out.println("Choix non correcte , Essayer une autre fois ");
                tentatives++;
            }

        }
        int score = 0;
        switch (tentatives) {
            case 0:
                System.out.println("Score + 10");
                score += 10;
                break;
            case 1:
                System.out.println("Score + 9");
                score += 9;
                break;
            case 2:
                System.out.println("Score + 8");
                score += 8;
                break;
            case 3:
                System.out.println("Score + 7");
                score += 7;
                break;
        }
        joueur.setScore(joueur.getScore() + score);
        System.out.println("Jeu terminé ! Ton score : " + joueur.getScore());
        joueur.passerAuNiveauSuivant();
    }
}

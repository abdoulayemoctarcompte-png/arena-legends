package arene;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int choix = -1;

        do {
            System.out.println("\n=== ARENA LEGENDS ===");
            System.out.println("1. Lancer un dé");
            System.out.println("2. Calculer un rang");
            System.out.println("3. Test de coup critique");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                scanner.next(); 
                choix = -1;
                continue;
            }

            switch (choix) {
                case 1:
                    lancerDe(scanner, random);
                    break;
                case 2:
                    calculerRang(scanner);
                    break;
                case 3:
                    testerCoupsCritiques(random);
                    break;
                case 0:
                    System.out.println("Fermeture du programme...");
                    break;
                default:
                    System.out.println("Erreur : Choix hors menu.");
            }
        } while (choix != 0);

        scanner.close();
    }
    private static void lancerDe(Scanner scanner, Random random) {
        int faces = 0;
        while (faces < 4 || faces > 20) {
            System.out.print("Entrez le nombre de faces du dé (entre 4 et 20) : ");
            try {
                faces = scanner.nextInt();
                if (faces < 4 || faces > 20) {
                    System.out.println("Erreur : Le nombre de faces doit être compris entre 4 et 20.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Entrée invalide.");
                scanner.next();
            }
        }
        int resultat = random.nextInt(faces) + 1;
        System.out.println("Résultat du lancer (" + faces + " faces) : " + resultat);
    }

    // 3. Calculer un rang selon les points[cite: 33]
    private static void calculerRang(Scanner scanner) {
        System.out.print("Entrez le nombre de points : ");
        try {
            int points = scanner.nextInt();
            if (points < 0) {
                System.out.println("Erreur : Un nombre négatif est refusé.");
            } else if (points < 100) {
                System.out.println("Rang : Bronze");
            } else if (points <= 499) {
                System.out.println("Rang : Argent");
            } else if (points <= 1499) {
                System.out.println("Rang : Or");
            } else {
                System.out.println("Rang : Légende");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Entrée invalide.");
            scanner.next();
        }
    }

    // 4 & 5. Test de coup critique sur 10 000 attaques + Piège (série consécutive)[cite: 33, 34]
    private static void testerCoupsCritiques(Random random) {
        int totalAttaques = 10000;
        int nbCritiques = 0;
        int serieActuelle = 0;
        int maxSerie = 0;

        for (int i = 0; i < totalAttaques; i++) {
            boolean estCritique = random.nextInt(100) < 15; // 15% de chance

            if (estCritique) {
                nbCritiques++;
                serieActuelle++;
                if (serieActuelle > maxSerie) {
                    maxSerie = serieActuelle;
                }
            } else {
                serieActuelle = 0;
            }
        }

        double pourcentageReel = (double) nbCritiques / totalAttaques * 100;
        System.out.println("Nombre d'attaques : " + totalAttaques);
        System.out.println("Nombre de critiques : " + nbCritiques);
        System.out.printf("Pourcentage réel obtenu : %.2f%%\n", pourcentageReel);
        System.out.println("Plus longue série consécutive de critiques : " + maxSerie);
    }
}
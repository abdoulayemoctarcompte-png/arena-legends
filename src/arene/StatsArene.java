package arene;

import java.util.Random;

public class StatsArene {

    public static void main(String[] args) {
        // Tableau de scores fourni par le sujet[cite: 34]
        int[] scores = {42, 87, 15, 99, 63, 87, 5, 71, 99, 34, 50, 28};

        System.out.println("=== TEST DE LA PARTIE 2 ===");
        System.out.println("Moyenne : " + moyenne(scores));
        System.out.println("Maximum : " + max(scores));
        System.out.println("Minimum : " + min(scores));

        // Test du tri décroissant
        int[] scoresCopie = scores.clone();
        trierDecroissant(scoresCopie);
        System.out.print("Trié décroissant : ");
        for (int s : scoresCopie) {
            System.out.print(s + " ");
        }
        System.out.println();

        // Test sans doublons
        int[] uniques = sansDoublons(scores);
        System.out.print("Sans doublons (taille " + uniques.length + ") : ");
        for (int u : uniques) {
            System.out.print(u + " ");
        }
        System.out.println();

        // Test de la grille 2D et distance de Manhattan
        char[][] grille = new char[8][8];
        initialiserGrille(grille);
        System.out.println("\nGrille de l'arène :");
        afficherGrille(grille);
        System.out.println("Distance de Manhattan entre A et B : " + distance(grille));
    }

    // 1. Moyenne, Max et Min (sans utiliser Arrays)[cite: 34]
    public static double moyenne(int[] t) {
        if (t == null || t.length == 0) return 0;
        double somme = 0;
        for (int val : t) {
            somme += val;
        }
        return somme / t.length;
    }

    public static int max(int[] t) {
        int m = t[0];
        for (int i = 1; i < t.length; i++) {
            if (t[i] > m) {
                m = t[i];
            }
        }
        return m;
    }

    public static int min(int[] t) {
        int m = t[0];
        for (int i = 1; i < t.length; i++) {
            if (t[i] < m) {
                m = t[i];
            }
        }
        return m;
    }

    // 2. Tri à bulles décroissant codé à la main avec arrêt anticipé[cite: 34]
    public static void trierDecroissant(int[] t) {
        boolean echange;
        do {
            echange = false;
            for (int i = 0; i < t.length - 1; i++) {
                if (t[i] < t[i + 1]) {
                    int temp = t[i];
                    t[i] = t[i + 1];
                    t[i + 1] = temp;
                    echange = true;
                }
            }
        } while (echange);
    }

    // 3. Suppression des doublons (nouveau tableau de taille exacte)[cite: 34]
    public static int[] sansDoublons(int[] t) {
        int count = 0;
        for (int i = 0; i < t.length; i++) {
            boolean unique = true;
            for (int j = 0; j < i; j++) {
                if (t[i] == t[j]) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i < t.length; i++) {
            boolean unique = true;
            for (int j = 0; j < i; j++) {
                if (t[i] == t[j]) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                result[index++] = t[i];
            }
        }
        return result;
    }

    // 4. Grille 2D (8x8), placement aléatoire de 6 obstacles '#' et de 'A' et 'B'[cite: 34]
    public static void initialiserGrille(char[][] grille) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = '.';
            }
        }

        Random rand = new Random();

        int obstaclesPlaces = 0;
        while (obstaclesPlaces < 6) {
            int r = rand.nextInt(8);
            int c = rand.nextInt(8);
            if (grille[r][c] == '.') {
                grille[r][c] = '#';
                obstaclesPlaces++;
            }
        }

        while (true) {
            int r = rand.nextInt(8);
            int c = rand.nextInt(8);
            if (grille[r][c] == '.') {
                grille[r][c] = 'A';
                break;
            }
        }

        while (true) {
            int r = rand.nextInt(8);
            int c = rand.nextInt(8);
            if (grille[r][c] == '.') {
                grille[r][c] = 'B';
                break;
            }
        }
    }

    public static void afficherGrille(char[][] grille) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                System.out.print(grille[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 5. Distance de Manhattan en parcourant la grille une seule fois[cite: 34]
    public static int distance(char[][] grille) {
        int xA = -1, yA = -1, xB = -1, yB = -1;

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if (grille[i][j] == 'A') {
                    xA = i;
                    yA = j;
                } else if (grille[i][j] == 'B') {
                    xB = i;
                    yB = j;
                }
            }
        }

        if (xA == -1 || xB == -1) return -1;

        return Math.abs(xA - xB) + Math.abs(yA - yB);
    }
}
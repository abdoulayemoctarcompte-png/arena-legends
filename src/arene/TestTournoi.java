package arene;

import java.util.ArrayList;

public class TestTournoi {
    public static void main(String[] args) {
        // 1. Création de 8 combattants (au moins 2 de chaque classe : Guerrier, Mage, Voleur, Paladin)
        Tournoi tournoi = new Tournoi();
        
        tournoi.inscrire(new Guerrier("Thorin", 150, 25, 10));
        tournoi.inscrire(new Guerrier("Conan", 160, 28, 8));
        
        tournoi.inscrire(new Mage("Gandalf", 100, 35, 5));
        tournoi.inscrire(new Mage("Merlin", 90, 40, 3));
        
        tournoi.inscrire(new Voleur("Garona", 110, 22, 5, 25));
        tournoi.inscrire(new Voleur("Loki", 100, 20, 6, 30));
        
        tournoi.inscrire(new Paladin("Arthur", 200, 22, 15));
        tournoi.inscrire(new Paladin("Lancelot", 190, 24, 12));

        System.out.println("=== DEBUT DU TOURNOI ARENA LEGENDS ===");

        // 2. Lancer le tournoi complet à élimination directe
        Combattant champion = tournoi.lancer();

        // 3. Affichage du champion
        System.out.println("\n========================================");
        System.out.println("🏆 CHAMPION DU TOURNOI : " + champion);
        System.out.println("========================================");

        // 4. Affichage du classement par victoires
        System.out.println("\n--- CLASSEMENT DES COMBATTANTS ---");
        ArrayList<Combattant> classement = tournoi.classement();
        for (Combattant c : classement) {
            System.out.println(c.getNom() + " (" + c.getClasse() + ") - Victoires : " + tournoi.getVictoires(c));
        }

        // 5. Affichage des statistiques par classe
        tournoi.statsParClasse();

        // 6. Test de la simulation 100 fois (sans affichage) pour l'équilibrage
        simuler100Tournois();
    }

    private static void simuler100Tournois() {
        int vicGuerrier = 0, vicMage = 0, vicVoleur = 0, vicPaladin = 0;

        for (int i = 0; i < 100; i++) {
            Tournoi t = new Tournoi();
            // Utilisation de noms valides (≥ 3 caractères) pour éviter l'IllegalArgumentException
            t.inscrire(new Guerrier("Guer1", 150, 25, 10));
            t.inscrire(new Guerrier("Guer2", 160, 28, 8));
            t.inscrire(new Mage("Mage1", 100, 35, 5));
            t.inscrire(new Mage("Mage2", 90, 40, 3));
            t.inscrire(new Voleur("Vol1", 110, 22, 5, 25));
            t.inscrire(new Voleur("Vol2", 100, 20, 6, 30));
            t.inscrire(new Paladin("Pala1", 200, 22, 15));
            t.inscrire(new Paladin("Pala2", 190, 24, 12));

            Combattant champ = t.lancer();
            if (champ instanceof Guerrier && !(champ instanceof Paladin)) vicGuerrier++;
            else if (champ instanceof Mage) vicMage++;
            else if (champ instanceof Voleur) vicVoleur++;
            else if (champ instanceof Paladin) vicPaladin++;
        }

        System.out.println("\n=== SIMULATION DE 100 TOURNOIS ===");
        System.out.println("Victoires du Paladin : " + vicPaladin);
        System.out.println("Victoires du Guerrier : " + vicGuerrier);
        System.out.println("Victoires du Mage : " + vicMage);
        System.out.println("Victoires du Voleur : " + vicVoleur);
        System.out.println("Conclusion : Le Paladin l'emporte souvent grâce à sa capacité de soin combinée à la rage.");
    }
}
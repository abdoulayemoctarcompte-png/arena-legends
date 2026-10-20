package arene;

import java.util.Random;

public class Voleur extends Combattant {
    private final int esquive; // en % (10 à 40)
    private final Random random = new Random();

    public Voleur(String nom, int pvMax, int attaque, int defense, int esquive) {
        super(nom, pvMax, attaque, defense);
        this.esquive = esquive;
    }

    @Override
    public String getClasse() {
        return "Voleur";
    }

    @Override
    public int attaquer(Combattant cible) {
        int degatsTotal = getAttaque();
        
        // 25% de chance de frapper deux fois[cite: 36]
        if (random.nextInt(100) < 25) {
            degatsTotal *= 2;
        }

        cible.subirDegats(degatsTotal);
        return degatsTotal;
    }

    @Override
    public void subirDegats(int d) {
        // Peut esquiver totalement selon le pourcentage d'esquive[cite: 36]
        if (random.nextInt(100) < esquive) {
            // Esquive réussie, aucun dégât pris
            return;
        }
        super.subirDegats(d);
    }
}
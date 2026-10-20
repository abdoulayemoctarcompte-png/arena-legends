package arene;

public class Paladin extends Guerrier {

    public Paladin(String nom, int pvMax, int attaque, int defense) {
        super(nom, pvMax, attaque, defense);
    }

    @Override
    public String getClasse() {
        return "Paladin";
    }

    @Override
    public int attaquer(Combattant cible) {
        // Réutilise toute la logique du Guerrier via super.attaquer(...)
        int degatsInfliges = super.attaquer(cible);

        // Se soigne de 10% des dégâts infligés[cite: 36]
        int soin = (int) (degatsInfliges * 0.10);
        soigner(soin);

        return degatsInfliges;
    }
}
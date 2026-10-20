package arene;

public class Guerrier extends Combattant {
    private int rage; // 0 à 100

    public Guerrier(String nom, int pvMax, int attaque, int defense) {
        super(nom, pvMax, attaque, defense);
        this.rage = 0;
    }

    @Override
    public String getClasse() {
        return "Guerrier";
    }

    @Override
    public int attaquer(Combattant cible) {
        int degats = getAttaque();
        rage += 20;

        if (rage >= 100) {
            degats *= 2; // Dégâts doublés
            rage = 0;    // Remise à zéro
        }

        cible.subirDegats(degats);
        return degats;
    }

    public int getRage() {
        return rage;
    }
}
package arene;

public class Mage extends Combattant {
    private int mana; // Départ 100[cite: 36]

    public Mage(String nom, int pvMax, int attaque, int defense) {
        super(nom, pvMax, attaque, defense);
        this.mana = 100;
    }

    @Override
    public String getClasse() {
        return "Mage";
    }

    @Override
    public int attaquer(Combattant cible) {
        int degats;
        if (mana >= 30) {
            mana -= 30;
            degats = getAttaque() * 2;
            cible.subirDegatsBruts(degats); // Ignore la défense[cite: 36]
        } else {
            degats = getAttaque() + 2;
            mana += 15;
            cible.subirDegats(degats);
        }
        return degats;
    }

    public int getMana() {
        return mana;
    }
}
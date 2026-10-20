package arene;

import java.util.Arrays;

public abstract class Combattant {
    private final String nom;
    private final int pvMax;
    private int pv;
    private final int attaque;
    private final int defense;
    
    private static int nbCombattants = 0;
    private final int[] historiqueDegats = new int[5];
    private int indexHistorique = 0;

    public Combattant(String nom, int pvMax, int attaque, int defense) {
        if (nom == null || nom.length() < 3 || nom.length() > 15) {
            throw new IllegalArgumentException("Nom invalide, reçu: " + nom);
        }
        if (pvMax < 50 || pvMax > 300) {
            throw new IllegalArgumentException("pvMax invalide, reçu: " + pvMax);
        }
        if (attaque < 5 || attaque > 50) {
            throw new IllegalArgumentException("attaque invalide, reçu: " + attaque);
        }
        if (defense < 0 || defense > 30) {
            throw new IllegalArgumentException("defense invalide, reçu: " + defense);
        }

        this.nom = nom;
        this.pvMax = pvMax;
        this.pv = pvMax;
        this.attaque = attaque;
        this.defense = defense;

        nbCombattants++;
    }

    // Méthodes abstraites exigées par la Partie 4
    public abstract int attaquer(Combattant cible);
    public abstract String getClasse();

    public String getNom() { return nom; }
    public int getPvMax() { return pvMax; }
    public int getPv() { return pv; }
    public int getAttaque() { return attaque; }
    public int getDefense() { return defense; }
    public static int getNbCombattants() { return nbCombattants; }

    public int[] getHistoriqueDegats() {
        return Arrays.copyOf(historiqueDegats, historiqueDegats.length);
    }

    public void subirDegats(int d) {
        int degatsReels = d - defense;
        if (degatsReels < 1) degatsReels = 1;
        pv -= degatsReels;
        if (pv < 0) pv = 0;

        historiqueDegats[indexHistorique % historiqueDegats.length] = degatsReels;
        indexHistorique++;
    }

    // Méthode protected pour que le Mage puisse ignorer la défense
    protected void subirDegatsBruts(int d) {
        pv -= d;
        if (pv < 0) pv = 0;

        historiqueDegats[indexHistorique % historiqueDegats.length] = d;
        indexHistorique++;
    }

    public void soigner(int s) {
        if (estKo() || s <= 0) return;
        pv += s;
        if (pv > pvMax) pv = pvMax;
    }

    public boolean estKo() {
        return pv <= 0;
    }

    @Override
    public String toString() {
        return nom + " (" + getClasse() + ") [" + pv + "/" + pvMax + " PV] ATK " + attaque + " DEF " + defense;
    }
}
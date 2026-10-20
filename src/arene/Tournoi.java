package arene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Tournoi {
    private final ArrayList<Combattant> participants = new ArrayList<>();
    private final Map<Combattant, Integer> victoiresMap = new HashMap<>();

    public boolean inscrire(Combattant c) {
        if (participants.size() >= 8 || c == null) {
            return false;
        }
        for (Combattant existing : participants) {
            if (existing.getNom().equalsIgnoreCase(c.getNom())) {
                return false;
            }
        }
        participants.add(c);
        victoiresMap.put(c, 0);
        return true;
    }

    public boolean desinscrire(String nom) {
        Iterator<Combattant> iterator = participants.iterator();
        while (iterator.hasNext()) {
            Combattant c = iterator.next();
            if (c.getNom().equalsIgnoreCase(nom)) {
                iterator.remove();
                victoiresMap.remove(c);
                return true;
            }
        }
        return false;
    }

    public List<Combattant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public Combattant duel(Combattant a, Combattant b) {
        System.out.println("\n--- DUEL : " + a.getNom() + " (" + a.getClasse() + ") VS " + b.getNom() + " (" + b.getClasse() + ") ---");
        
        Combattant attaquant = (a.getAttaque() >= b.getAttaque()) ? a : b;
        Combattant defenseur = (attaquant == a) ? b : a;

        int tour = 0;
        while (!a.estKo() && !b.estKo() && tour < 50) {
            int degats = attaquant.attaquer(defenseur);
            System.out.println(attaquant.getNom() + " attaque " + defenseur.getNom() + " et inflige " + degats + " dégâts. (" + defenseur + ")");
            
            Combattant temp = attaquant;
            attaquant = defenseur;
            defenseur = temp;
            tour++;
        }

        Combattant vainqueur;
        if (a.estKo() && !b.estKo()) {
            vainqueur = b;
        } else if (b.estKo() && !a.estKo()) {
            vainqueur = a;
        } else {
            double pctA = (double) a.getPv() / a.getPvMax();
            double pctB = (double) b.getPv() / b.getPvMax();
            vainqueur = (pctA >= pctB) ? a : b;
        }

        victoiresMap.put(vainqueur, victoiresMap.get(vainqueur) + 1);
        System.out.println("=> Vainqueur du duel : " + vainqueur.getNom() + "\n");
        return vainqueur;
    }

    public Combattant lancer() {
        if (participants.size() < 2) {
            System.out.println("Il faut au moins 2 participants pour lancer le tournoi.");
            return participants.isEmpty() ? null : participants.get(0);
        }

        ArrayList<Combattant> enLice = new ArrayList<>(participants);
        int round = 1;

        while (enLice.size() > 1) {
            System.out.println("\n=== TOUR " + round + " (Restants : " + enLice.size() + ") ===");
            Collections.shuffle(enLice);
            ArrayList<Combattant> gagnants = new ArrayList<>();

            for (int i = 0; i < enLice.size() - 1; i += 2) {
                Combattant c1 = enLice.get(i);
                Combattant c2 = enLice.get(i + 1);
                
                Combattant vainqueur = duel(c1, c2);
                vainqueur.soigner(999);
                gagnants.add(vainqueur);
            }

            if (enLice.size() % 2 != 0) {
                gagnants.add(enLice.get(enLice.size() - 1));
            }

            enLice = gagnants;
            round++;
        }

        return enLice.get(0);
    }

    public ArrayList<Combattant> classement() {
        ArrayList<Combattant> liste = new ArrayList<>(participants);
        boolean echange;
        do {
            echange = false;
            for (int i = 0; i < liste.size() - 1; i++) {
                int vicA = victoiresMap.get(liste.get(i));
                int vicB = victoiresMap.get(liste.get(i + 1));
                if (vicA < vicB) {
                    Combattant temp = liste.get(i);
                    liste.set(i, liste.get(i + 1));
                    liste.set(i + 1, temp);
                    echange = true;
                }
            }
        } while (echange);
        return liste;
    }

    public void statsParClasse() {
        int vicGuerrier = 0;
        int vicMage = 0;
        int vicVoleur = 0;
        int vicPaladin = 0;

        for (Combattant c : participants) {
            int v = victoiresMap.get(c);
            String classe = c.getClasse();
            switch (classe) {
                case "Guerrier": vicGuerrier += v; break;
                case "Mage": vicMage += v; break;
                case "Voleur": vicVoleur += v; break;
                case "Paladin": vicPaladin += v; break;
            }
        }

        System.out.println("\n=== STATISTIQUES PAR CLASSE ===");
        System.out.println("Guerrier : " + vicGuerrier + " victoires");
        System.out.println("Mage     : " + vicMage + " victoires");
        System.out.println("Voleur   : " + vicVoleur + " victoires");
        System.out.println("Paladin  : " + vicPaladin + " victoires");
    }

    public int getVictoires(Combattant c) {
        return victoiresMap.getOrDefault(c, 0);
    }
}
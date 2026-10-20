# Réponses aux questions de réflexion - TP Arena Legends

## Partie 1
**Question : Pourquoi un do...while est-il plus adapté qu'un while pour un menu ?**
Un `do...while` garantit que le bloc d'instructions s'exécute au moins une fois avant de vérifier la condition de sortie, ce qui permet d'afficher le menu à l'utilisateur avant d'évaluer son premier choix.

## Partie 2
**Question : Quelle est la différence entre copier un tableau avec int[] b = a; et le recopier case par case ?**
Écrire `int[] b = a;` copie uniquement la référence mémoire : `a` et `b` pointent vers le même tableau en mémoire. Une copie case par case (ou via `.clone()`) crée un nouveau tableau indépendant avec ses propres valeurs.

## Partie 3
**Question : Pourquoi un setter setPv(int pv) casserait-il l'encapsulation même s'il vérifie les bornes ?**
Un setter autoriserait n'importe quel code extérieur à modifier directement l'état des points de vie de manière arbitraire, contournant ainsi les règles métier fondamentales (comme l'interdiction de se soigner en état K.O. ou le calcul de la défense).

## Partie 4
**Question : Dans Combattant c = new Mage(...); c.attaquer(x);, quelle méthode est appelée et pourquoi ? Expliquez la différence entre type déclaré et type réel.**
C'est la méthode `attaquer()` de la classe `Mage` qui est appelée grâce au polymorphisme dynamique (liaison tardive). Le type déclaré est `Combattant`, tandis que le type réel est `Mage`. Java s'appuie sur le type réel à l'exécution pour choisir la bonne version de la méthode redéfinie.

## Partie 5 (Simulation 100 tournois)
**Quelle classe gagne le plus souvent ? Est-ce équilibré ?**
D'après la simulation des 100 tournois, le **Paladin** gagne généralement le plus souvent grâce à son pool de PV élevé, sa défense solide et sa capacité à se soigner à chaque attaque tout en profitant de la rage du guerrier. Ce n'est donc pas totalement équilibré, le Paladin ayant un avantage net par rapport au Voleur ou au Mage classique.
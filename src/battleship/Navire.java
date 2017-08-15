package battleship;

import java.util.LinkedList;

/**
 * Created by Max on 2017-08-05.
 */
public class Navire {
    public static final int LONGUEUR_PORTE_AVION = 5;
    public static final int LONGUEUR_CROISEUR = 4;
    public static final int LONGUEUR_CONTRE_T = 3;
    public static final int LONGUEUR_SOUS_MARIN = 2;
    public static final int LONGUEUR_TORPILLEUR = 1;
    private LinkedList<Position> positions;
    private int longueur;
    private String nom;

    public Navire(LinkedList<Position> positions, String nom, int longueur) {
        this.positions = positions;
        this.nom = nom;
        this.longueur = longueur;
    }

    public int getLongueur() {
        return longueur;
    }
    
    public LinkedList<Position> getPositions() {
        return this.positions;
    }

    public void setPositions(LinkedList<Position> positions) {
        this.positions = positions;
    }

    public boolean estCoule() {
        return this.positions.isEmpty();
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static boolean positionsSontValide(Position posDebut, Position posFin, int longueurDuNavire) {

        //les deux positions sont sur la même ligne ou colonne
        boolean estVertical = posDebut.getPosY() == posFin.getPosY();
        boolean estHorizontal = posDebut.getPosX() == posFin.getPosX();

        //l'écart entre les valeurs x ou y est le bon
        boolean ecartEstValide =
                Math.abs(posDebut.getPosX() - posFin.getPosX()) == longueurDuNavire
                || Math.abs(posDebut.getPosY() - posFin.getPosY()) == longueurDuNavire;

        return (estVertical || estHorizontal) && ecartEstValide;
    }
}

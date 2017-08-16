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
    public static enum Nom{
        PORTE_AVION,
        CROISEUR,
        CONTRE_T,
        SOUS_MARIN,
        TORPILLEUR
    }
    private LinkedList<Position> positions;
    private int longueur;
    private Nom nom;
    

    public Navire(LinkedList<Position> positions, int longueur) {
        this.positions = positions;
        this.longueur = longueur;
        switch(longueur){
            case LONGUEUR_PORTE_AVION:
                this.nom = Nom.PORTE_AVION;
                break;
            case LONGUEUR_CROISEUR:
                this.nom = Nom.CROISEUR;
                break;
            case LONGUEUR_CONTRE_T:
                this.nom = Nom.CONTRE_T;
                break;
            case LONGUEUR_SOUS_MARIN:
                this.nom = Nom.SOUS_MARIN;
                break;
            case LONGUEUR_TORPILLEUR:
                this.nom = Nom.TORPILLEUR;
                break;
        }
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

    public Nom getNom() {
        return this.nom;
    }

    public void setNom(Nom nom) {
        this.nom = nom;
    }

    public static boolean positionsSontValides(Position posDebut, Position posFin, int longueurDuNavire) {

        //les deux positions sont sur la même ligne ou colonne
        boolean estVertical = posDebut.getPosY() == posFin.getPosY();
        boolean estHorizontal = posDebut.getPosX() == posFin.getPosX();

        //l'écart entre les valeurs x ou y est le bon
        boolean ecartEstValide =
                Math.abs(posDebut.getPosX() - posFin.getPosX()) == longueurDuNavire - 1
                || Math.abs(posDebut.getPosY() - posFin.getPosY()) == longueurDuNavire - 1;

        return (estVertical || estHorizontal) && ecartEstValide;
    }
}

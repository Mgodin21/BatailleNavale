package battleship;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Max on 2017-08-06.
 */
public abstract class AJoueur implements IJoueur{
    private Queue<Position> derniersCoups;
    private Stack<Position> ciblesPotentielles;
    protected ArrayList<Navire> listeNavires;
    protected GrilleJeu grille;

    //constructeur
    public AJoueur (GrilleJeu grille){
        this.grille = grille;
    }

    //accesseurs
    public Queue<Position> getDerniersCoups() {
        return derniersCoups;
    }

    public Stack<Position> getCiblesPotentielles() {
        return ciblesPotentielles;
    }

    public void setCiblesPotentielles(Stack<Position> ciblesPotentielles) {
        this.ciblesPotentielles = ciblesPotentielles;
    }

    public GrilleJeu getGrille() {
        return grille;
    }

    public void setGrille(GrilleJeu grillePositions) {
        this.grille = grillePositions;
    }

    /**
     * vérifier la liste de positions du navire pour s'assurer qu'il n'y a pas
     * d'overlap avec un autre navire
     *
     * @param navire navire à placer
     * @return true si il n'y a pas d'overlap
     */
    boolean placeNavire(Navire navire){
        return true;//////placeholder
    }


    /**
     * Identifie quel joueur reçoit le tire
     *
     * @param cible joueur cible
     */
    public void tire(IJoueur cible){
        //cible.recoitTire(unePositionBidon);
    }


    /**
     * reçoit un tir adverse à une position sur la grille
     *
     * @param cible position du tir
     */
    public void recoitTire(Position cible){
    }
    
    public void ajouterNavire(Navire navire){
        listeNavires.add(navire);
    }
}

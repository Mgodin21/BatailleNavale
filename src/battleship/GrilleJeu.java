package battleship;

import javafx.beans.Observable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;
import javafx.beans.InvalidationListener;

/**
 * Panneau qui contient une grille de jeu (grille de boutons)
 */
public class GrilleJeu extends JPanel implements Observable{

    //position du dernier clic sur la grille
    public static Position posDernierClic;

    private ArrayList<Observer> observateurs;
    
    //un bouton a été cliqué, true lors d'un clic, false quand position reçue
    public static boolean boutonEstClique;
    
    public Position debut;

    private BoutonCustom[][] grilleBoutons;

    //tableau des lettres à insérer en haut de colonnes
    private static final char[] LETTRES_X = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    //constructeur
    public GrilleJeu(int taille){
        /*
         * Stratégie: pour chaque position dans la grille, crée un bouton
         * contenant la position de celui-ci. S'il s'agit de la première
         * colonne, change le texte pour indique le numéro de ligne, puis
         * le désactive. S'il s'agit de la première ligne, change la
         * position en lettre et le désactive.
         */
        this.setLayout(new GridLayout(taille, taille));

        this.grilleBoutons = new BoutonCustom[taille][taille];

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grilleBoutons[i][j] = new BoutonCustom(changerIntEnLettre(j), i);
                this.add(grilleBoutons[i][j]);
                if (i == 0) {
                    grilleBoutons[i][j].setText("" + changerIntEnLettre(j));
                    grilleBoutons[i][j].setEnabled(false);
                }
                if (j == 0 && i > 0) {
                    grilleBoutons[i][j].setText("" + i);
                    grilleBoutons[i][j].setEnabled(false);
                }
            }
        }
        grilleBoutons[0][0].setEnabled(false);
    }

    public void changerTexteBouton(Position position, String texte){
        grilleBoutons[position.getPosY()][changerLettreEnInt(position.getPosX())].setText(texte);
    }
    
    public String getTexteBouton(Position position){
        return grilleBoutons[position.getPosY()][changerLettreEnInt(position.getPosX())].getText();
    }

    /**
     * changer un char en son numéro de colonne correspondant
     *
     * @param lettre lettre à changer en indice
     * @return indice de la lettre dans le tableau de lettres
     */
    public static int changerLettreEnInt(char lettre) {
        //parcours la tableau de char et retourne la position du char
        //correspondant
        for (int i = 0; i < LETTRES_X.length; i++) {
            if (LETTRES_X[i] == lettre) {
                return i;
            }
        }
        return -1;
    }

    /**
     * changer un numéro de colonne en sa lettre correspondante
     *
     * @param chiffre indice de la lettre dans le tableau de lettres
     * @return lettre à l'indice reçu
     */
    public static char changerIntEnLettre(int chiffre) {
        return LETTRES_X[chiffre];
        ///////si chiffre trop grand exception
    }

    /**
     * retourne la position du dernier bouton cliqué dans la grille de jeu
     *
     * @return position du dernier bouton cliqué
     */
    public Position getPosDernierClic(){
        return posDernierClic;
    }

    /**
     * efface les valeurs de le grille de jeu (sauf les identifiants des lignes
     * et colonnes
     */
    public void reinitialiser(){
        for (int i =1; i<grilleBoutons.length; i++){
            for (int j =1; j<grilleBoutons.length; j++){
                grilleBoutons[i][j].setText("");
                grilleBoutons[i][j].position.setTouché(false);
            }
        }
    }

    /**
     * active ou désactive la grille de jeu
     *
     * @param etat true pour activer, false pour désactiver
     */
    public void estActive(boolean etat){
        for (int i =1; i<grilleBoutons.length; i++){
            for (int j =1; j<grilleBoutons.length; j++){
                grilleBoutons[i][j].setEnabled(etat);
            }
        }
    }

    @Override
    public void addListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * classe qui introduit une position à un JButton
     */
    private class BoutonCustom extends JButton {

        //identifie la position du bouton
        private Position position;

        //constructeur
        private BoutonCustom(char posX, int posY) {
            this.position = new Position(posX, posY);
            this.setBackground(Color.WHITE);

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BoutonCustom btn = (BoutonCustom) e.getSource();

                    //retient la position du bouton cliqué
                    posDernierClic = btn.position;
                    boutonEstClique = true;
                    switch (Partie.getInstance().getEtape()){
                        case PLACER_PORTE_AVION:
                            if(debut == null)
                                debut = posDernierClic;
                            else{
                                if(Navire.positionsSontValide(debut, posDernierClic, Navire.LONGUEUR_PORTE_AVION)){
                                        Partie.getInstance().placerNavireHumain(debut, posDernierClic);  
                                }
                                debut = null;
                            }
                            break;
                            
                            case PLACER_CONTRE_T:
                            if(debut == null)
                                debut = posDernierClic;
                            else{
                                if(Navire.positionsSontValide(debut, posDernierClic, Navire.LONGUEUR_CONTRE_T)){
                                        Partie.getInstance().placerNavireHumain(debut, posDernierClic);  
                                }
                            
                                debut = null;
                            }
                            break;
                            
                            case PLACER_CROISEUR:
                            if(debut == null)
                                debut = posDernierClic;
                            else{
                                if(Navire.positionsSontValide(debut, posDernierClic, Navire.LONGUEUR_CROISEUR)){
                                        Partie.getInstance().placerNavireHumain(debut, posDernierClic);  
                                
                                }
                                debut = null;
                            }
                            break;
                            
                            case PLACER_SOUS_MARIN:
                            if(debut == null)
                                debut = posDernierClic;
                            else{
                                if(Navire.positionsSontValide(debut, posDernierClic, Navire.LONGUEUR_SOUS_MARIN)){
                                        Partie.getInstance().placerNavireHumain(debut, posDernierClic);  
                                }
                                debut = null;
                            }
                            break;
                            
                            case PLACER_TORPILLEUR:
                            if(debut == null)
                                debut = posDernierClic;
                            else{
                                if(Navire.positionsSontValide(debut, posDernierClic, Navire.LONGUEUR_TORPILLEUR)){
                                        Partie.getInstance().placerNavireHumain(debut, posDernierClic);  
                                }
                                debut = null;
                            }
                            break;
                    }
                }
            });
        }
    }
}

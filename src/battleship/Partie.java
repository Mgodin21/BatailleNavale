/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author mgodin
 */
public class Partie {
    private JoueurHumain joueur;
    private JoueurArtificiel ai;
    private Etape etape;
    
    public Partie(){
        joueur = new JoueurHumain(new GrilleJeu(10));
        ai = new JoueurArtificiel(new GrilleJeu(10));
        etape = Etape.DEBUT;
    }

    public JoueurHumain getJoueur() {
        return joueur;
    }

    public void setJoueur(JoueurHumain joueur) {
        this.joueur = joueur;
    }

    public JoueurArtificiel getAi() {
        return ai;
    }

    public void setAi(JoueurArtificiel ai) {
        this.ai = ai;
    }
    
    
}

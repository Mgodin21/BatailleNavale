/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Observer;

/**
 *
 * @author mgodin
 */
public final class Partie {

    private static final Partie INSTANCE = new Partie();
    private Observer parent;
    public final String NAVIRE = "+";
    public final String TOUCHE = "X";
    public final String RATE = "O";

    private JoueurHumain joueur;
    private JoueurArtificiel ai;
    private Etape etape;

    public Partie() {
        joueur = new JoueurHumain(new GrilleJeu(10));
        ai = new JoueurArtificiel(new GrilleJeu(10));
        etape = Etape.DEBUT;
    }

    public static Partie getInstance() {
        return INSTANCE;
    }

    public void setParent(Observer ob) {
        this.parent = ob;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
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

    public void placerNavireHumain(Position depart, Position fin) {
        if (depart.getPosX() != fin.getPosX() && depart.getPosY() == fin.getPosY()) {
            if (depart.getPosX() < fin.getPosX()) {
                for (int i = GrilleJeu.changerLettreEnInt(depart.getPosX()); i <= GrilleJeu.changerLettreEnInt(fin.getPosX()); i++) {
                    joueur.getGrille().changerTexteBouton(new Position(GrilleJeu.changerIntEnLettre(i), depart.getPosY()), NAVIRE);
                }
            } else {
                for (int i = GrilleJeu.changerLettreEnInt(depart.getPosX()); i >= GrilleJeu.changerLettreEnInt(fin.getPosX()); i--) {
                    joueur.getGrille().changerTexteBouton(new Position(GrilleJeu.changerIntEnLettre(i), depart.getPosY()), NAVIRE);
                }
            }
        } else if (depart.getPosY() != fin.getPosY() && depart.getPosX() == fin.getPosX()) {
            if (depart.getPosY() < fin.getPosY()) {
                for (int i = depart.getPosY(); i <= fin.getPosY(); i++) {
                    joueur.getGrille().changerTexteBouton(new Position(depart.getPosX(), i), NAVIRE);
                }
            } else {
                for (int i = depart.getPosY(); i >= fin.getPosY(); i--) {
                    joueur.getGrille().changerTexteBouton(new Position(depart.getPosX(), i), NAVIRE);
                }
            }
        }
    }

    public boolean peutPlacerNavire(Position depart, Position fin) {
        if (depart.getPosX() != fin.getPosX() && depart.getPosY() == fin.getPosY()) {
            if (depart.getPosX() < fin.getPosX()) {
                for (int i = GrilleJeu.changerLettreEnInt(depart.getPosX()); i <= GrilleJeu.changerLettreEnInt(fin.getPosX()); i++) {
                    if(joueur.getGrille().getTexteBouton(new Position(GrilleJeu.changerIntEnLettre(i), depart.getPosY())) != "")
                        return false;
                }
            } else {
                for (int i = GrilleJeu.changerLettreEnInt(depart.getPosX()); i >= GrilleJeu.changerLettreEnInt(fin.getPosX()); i--) {
                    if(joueur.getGrille().getTexteBouton(new Position(GrilleJeu.changerIntEnLettre(i), depart.getPosY())) != "")
                        return false;
                }
            }
        } else if (depart.getPosY() != fin.getPosY() && depart.getPosX() == fin.getPosX()) {
            if (depart.getPosY() < fin.getPosY()) {
                for (int i = depart.getPosY(); i <= fin.getPosY(); i++) {
                    (joueur.getGrille().getTexteBouton(new Position(depart.getPosX(), i));
                }
            } else {
                for (int i = depart.getPosY(); i >= fin.getPosY(); i--) {
                    joueur.getGrille().getTexteBouton(new Position(depart.getPosX(), i));
                }
            }
        }

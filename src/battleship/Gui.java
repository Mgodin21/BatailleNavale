package battleship;

import com.sun.jmx.mbeanserver.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * GUI du jeu de bataille navale
 *
 * @author Maxime Lacombe
 * @version 06-08-2017
 */
public class Gui implements Observer{

    public static JButton[] boutonsGauche = new JButton[8];
    public static boolean[] boutonsActive = {true,true,true,true,true,true,true,true};
    
    

    //position et taille de la fenêtre principale
    private final Rectangle TAILLE_FENETRE = new Rectangle(200, 200, 1000, 800);

    //taille de la grille de jeu
    private final int TAILLE_GRILLE = 11;

    //largeur de la colonne des derniers tirs à droite
    private final int LARGEUR_PANNEAU_DROITE = 250;
    private Dimension dimensionPanneauDroite = new Dimension(LARGEUR_PANNEAU_DROITE, 0);

    //largeur de la colonne de gauche
    private final int LARGEUR_PANNEAU_GAUCHE = 200;
    private Dimension dimensionPanneauGauche = new Dimension(LARGEUR_PANNEAU_GAUCHE, 0);

    private Partie partie;
    
    private PanelDeLabels derniersCoupsHumain;
    private PanelDeLabels derniersCoupsAI;

    private JLabel infosPartie;

    public PanelDeLabels getDerniersCoupsHumain() {
        return derniersCoupsHumain;
    }

    public PanelDeLabels getDerniersCoupsAI() {
        return derniersCoupsAI;
    }

    public JLabel getInfosPartie() {
        return infosPartie;
    }

    public void setInfosPartie(JLabel infosPartie) {
        this.infosPartie = infosPartie;
    }

    //constructeur
    public Gui() {
        partie = Partie.getInstance();
        partie.setParent(this);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Bataille Navale");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setBounds(TAILLE_FENETRE);
                frame.setVisible(true);
                creerGui(frame.getContentPane());
            }
        });
    }

    /**
     * création des différents éléments du GUI, configuration du positionnement
     * et des séparations dans ces éléments
     *
     * @param panel
     */
    private void creerGui(Container panel) {

        //séparation horizontale de la fenêtre en deux
        panel.setLayout(new GridLayout(2, 1));

        //création des panneaux haut et bas et ajout dans la fenêtre
        JPanel panelHaut = new JPanel();
        JPanel panelBas = new JPanel();
        panel.add(panelHaut);
        panel.add(panelBas);

        //séparation du panneau du haut et création des panneaux des différents
        //sections et ajout au panneau du haut
        panelHaut.setLayout(new BorderLayout());
        JPanel paneHautGauche = new JPanel();
        panelHaut.add(paneHautGauche, BorderLayout.WEST);
        panelHaut.add(partie.getAi().getGrille(), BorderLayout.CENTER);

        //séparation du panneau du bas et création des panneaux des différents
        //sections et ajout au panneau du bas
        panelBas.setLayout(new BorderLayout());
        JPanel paneBasBas = new JPanel();
        JPanel paneBasGauche = new JPanel();
        panelBas.add(partie.getJoueur().getGrille(), BorderLayout.CENTER);
        panelBas.add(paneBasBas, BorderLayout.SOUTH);

        //ajout du Label au panneau du haut
        JLabel labelHumain = new JLabel("Grille Adversaire");
        labelHumain.setFont(labelHumain.getFont().deriveFont(18f));
        labelHumain.setHorizontalAlignment(JLabel.CENTER);
        panelHaut.add(labelHumain, BorderLayout.NORTH);

        //ajout du Label au panneau du bas
        JLabel labelAI = new JLabel("Ma Grille");
        labelAI.setFont(labelAI.getFont().deriveFont(18f));
        labelAI.setHorizontalAlignment(JLabel.CENTER);
        panelBas.add(labelAI, BorderLayout.NORTH);

        //remplit les panneaux gauches et droits avec les bons éléments
        remplirPanneauHumainGauche(paneHautGauche);
        derniersCoupsHumain = new PanelDeLabels("Derniers coups humain",dimensionPanneauDroite);
        derniersCoupsAI = new PanelDeLabels("Derniers coups AI",dimensionPanneauDroite);

        panelHaut.add(derniersCoupsAI, BorderLayout.EAST);
        panelBas.add(derniersCoupsHumain, BorderLayout.EAST);

        //ajoute un panneau à gauche de la grille du bas pour aligner les grilles
        paneBasGauche.setPreferredSize(dimensionPanneauGauche);
        panelBas.add(paneBasGauche, BorderLayout.WEST);

        //ajoute un JLabel qui affiche les informations sur la partie en bas à
        //gauche
        paneBasBas.setLayout(new FlowLayout(FlowLayout.LEFT));
        infosPartie = new JLabel("infos sur la partie (placeholder)");
        paneBasBas.add(infosPartie);
    }

    /**
     * Configuration du panneau de gauche, création et placement des boutons
     *
     * A REVOIR
     *      PROCEDURE POUR CREER LES BOUTONS(?)
     *      OBSERVER / OBSERVABLE POUR APPEL DE PROCEDURE?
     *
     * @param paneGauche JPannel de gauche
     */
    private void remplirPanneauHumainGauche(JPanel paneGauche) {
        //grille de 8 éléments verticaux
        paneGauche.setLayout(new GridLayout(8, 1));
        paneGauche.setPreferredSize(dimensionPanneauGauche);

        //création des boutons
        final JButton boutonPlacerPA = new JButton("Placer un porte-avion");
        boutonsGauche[0] = boutonPlacerPA;
        boutonPlacerPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partie.setEtape(Etape.PLACER_PORTE_AVION);
                boutonsGauche[1].setEnabled(false);
                boutonsGauche[2].setEnabled(false);
                boutonsGauche[3].setEnabled(false);
                boutonsGauche[4].setEnabled(false);
                partie.getAi().grille.bloquerGrille();
                
            }
        });

        JButton boutonPlacerCroiseur = new JButton("Placer un Croiseur");
        boutonsGauche[1] = boutonPlacerCroiseur;
        boutonPlacerCroiseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partie.setEtape(Etape.PLACER_CROISEUR);
                boutonsGauche[0].setEnabled(false);
                boutonsGauche[2].setEnabled(false);
                boutonsGauche[3].setEnabled(false);
                boutonsGauche[4].setEnabled(false);
                partie.getAi().grille.bloquerGrille();
            }
        });

        JButton boutonPlacerContreT = new JButton("Placer un Contre T");
        boutonsGauche[2] = boutonPlacerContreT;
        boutonPlacerContreT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partie.setEtape(Etape.PLACER_CONTRE_T);
                boutonsGauche[0].setEnabled(false);
                boutonsGauche[1].setEnabled(false);
                boutonsGauche[3].setEnabled(false);
                boutonsGauche[4].setEnabled(false);
                partie.getAi().grille.bloquerGrille();
            }
        });

        JButton boutonPlacerSousMarin = new JButton("Placer un Sous-marin");
        boutonsGauche[3] = boutonPlacerSousMarin;
        boutonPlacerSousMarin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partie.setEtape(Etape.PLACER_SOUS_MARIN);
                boutonsGauche[0].setEnabled(false);
                boutonsGauche[1].setEnabled(false);
                boutonsGauche[2].setEnabled(false);
                boutonsGauche[4].setEnabled(false);
                partie.getAi().grille.bloquerGrille();
            }
        });

        JButton boutonPlacerTorpilleur = new JButton("Placer un Torpilleur");
        boutonsGauche[4] = boutonPlacerTorpilleur;
        boutonPlacerTorpilleur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partie.setEtape(Etape.PLACER_TORPILLEUR);
                boutonsGauche[0].setEnabled(false);
                boutonsGauche[1].setEnabled(false);
                boutonsGauche[2].setEnabled(false);
                boutonsGauche[3].setEnabled(false);
                partie.getAi().grille.bloquerGrille();
            }
        });

        JButton boutonAnnuler = new JButton("Annuler");
        boutonsGauche[5] = boutonAnnuler;
        boutonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Annuler");
            }
        });

        JButton boutonAide = new JButton("Aide");
        boutonsGauche[6] = boutonAide;
        boutonAide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aide");
            }
        });

        JButton boutonRecommencer = new JButton("Recommencer");
        boutonsGauche[7] = boutonRecommencer;
        boutonRecommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Recommencer");
            }
        });

        //ajout des boutons au panneau de gauche
        for (int i=0;i<boutonsGauche.length;i++){
            paneGauche.add(boutonsGauche[i]);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        EUpdate update;
        if(arg.toString().equals(EUpdate.SUCCES.toString()))
        {
            update = EUpdate.SUCCES;
        }
        else if (arg.toString().equals(EUpdate.ECHEC.toString()))
        {
            update = EUpdate.ECHEC;
        }
        modifierEtatBoutons(Util.cast(arg));
    }
    
    public void updateObserver(Object o, EUpdate update){
        
        modifierEtatBoutons(update);
    }
    
    public void modifierEtatBoutons(EUpdate Update){
        int i = 0;
        partie.getAi().grille.debloquerGrille();
        switch(Partie.getInstance().getEtape())
        {
            case PLACER_PORTE_AVION:
                if (Update == EUpdate.SUCCES) {
                    boutonsGauche[0].setEnabled(false);
                    boutonsActive[0] = false;
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                } 
                else{
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                }
                break;
            case PLACER_CROISEUR:
                if (Update == EUpdate.SUCCES) {
                    boutonsGauche[1].setEnabled(false);
                    boutonsActive[1] = false;
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                } 
                else{
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                }
                break;
            case PLACER_CONTRE_T:
                if (Update == EUpdate.SUCCES) {
                    boutonsGauche[2].setEnabled(false);
                    boutonsActive[2] = false;
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                } 
                else{
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                }
                break;
            case PLACER_SOUS_MARIN:
                if (Update == EUpdate.SUCCES) {
                    boutonsGauche[3].setEnabled(false);
                    boutonsActive[3] = false;
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                } 
                else{
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                }
                break;
            case PLACER_TORPILLEUR:
                if (Update == EUpdate.SUCCES) {
                    boutonsGauche[4].setEnabled(false);
                    boutonsActive[4] = false;
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                } 
                else{
                    i = 0;
                    for(JButton bouton : boutonsGauche){
                        if(boutonsActive[i]){
                            bouton.setEnabled(true);
                        }
                        i++;
                    }
                }
                break;}
        attaqueSiBateauPlaces();
    }
    
    public void reinitialiserBoutons (){
        int i = 0;
        for (JButton bouton : boutonsGauche) {
            bouton.setEnabled(true);
            boutonsActive[i] = true;
            i++;
        }
    }

    private void attaqueSiBateauPlaces() {
        for(int i = 0; i < 5 ; i++){
            if(boutonsGauche[i].isEnabled())
                return;
        }
        partie.setEtape(Etape.ATTAQUER);
    }
}
    
            

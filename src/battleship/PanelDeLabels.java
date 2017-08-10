package battleship;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * panneau contenant une liste de labels pour afficher les derniers clics d'un
 * joueur à droite
 */
public class PanelDeLabels extends JPanel {

    private ArrayList<JLabel> labels;

    //constructeur
    public PanelDeLabels(String titre, Dimension dimension) {
        /*
         * Statégie: crée un JPannel et y ajoute une JLabel titre et 5 JLabels
         *           qui affichent les derniers coups du joueur. La taille du
         *           texte des Labels est placé à 18 et la largeur de la fenêtre
         *           est configurée avec le paramètre dimension.
         *
         */
        JLabel labelTitre = new JLabel(titre);
        labelTitre.setHorizontalAlignment(JLabel.CENTER);
        labelTitre.setFont(labelTitre.getFont().deriveFont(18f));
        this.add(labelTitre);
        this.labels = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i < 5; i++) {
            labels.add(new JLabel());
            labels.get(i).setFont(labels.get(i).getFont().deriveFont(18f));
            this.add(labels.get(i));
        }

        this.setPreferredSize(dimension);
    }

    /**
     * ajouter un élément à l'affichage des derniers coups
     * Change seulement l'affichage, ne change pas la file de chaque joueur
     *
     * @param position position de l'événement à afficher
     * @param evenement ce qui s'est passé (touché, coulé, etc)
     */
    public void ajouter(Position position, String evenement){
        /*
         * Stratégie: décaler tous les labels vers le bas et ajouter le nouvel
         *            événement au JLabel du haut. L'élément du bas est oublié
         *            car on veut seulement retenir 5 éléments
         */

        String texteLabel = position.getPosX() + ", " +
                position.getPosY() + ", " + evenement;
        for (int i=labels.size()-1; i>0;i--){
            labels.get(i).setText(labels.get(i-1).getText());
        }
        this.labels.get(0).setText(texteLabel);
    }

    /**
     * effacer tous les labels
     */
    public void reinitialiser(){
        for (int i=0; i < labels.size();i++){
            labels.get(i).setText("");
        }
    }
}

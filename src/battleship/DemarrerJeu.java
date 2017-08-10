package battleship;

/**
 * Created by Max on 2017-08-05.
 */
public class DemarrerJeu {

    public static void main(String[] args) {

        //création du Gui
        Gui gui = new Gui();

        //ne pas enlever, laisse le temps au Gui de se creer;
        pause(200);

        //création des joueurs et association avec leur grille
        JoueurHumain joueurHumain = new JoueurHumain(gui.getGrilleHumain());
        JoueurArtificiel joueurAI = new JoueurArtificiel(gui.getGrilleAI());

    }

    /**
     * faire une pause
     *
     * @param temps en milisecondes
     */
    public static void pause(int temps) {

        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}


package battleship;

/**
 * position sur la grille de jeu
 *
 * Created by Maxime Lacombe on 2017-08-05.
 */
public class Position {
    private char posX;
    private int posY;
    private boolean touche;

    //constructeur
    public Position(char posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.touche = false;
    }

    //accesseurs
    public char getPosX() {
        return posX;
    }

    public void setPosX(char posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean estTouché() {
        return touche;
    }

    public void setTouché(boolean estTouché) {
        this.touche = estTouché;
    }

    @Override
    public String toString() {
        return "Position{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", estTouché=" + touche +
                '}';
    }
}

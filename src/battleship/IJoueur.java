package battleship;

/**
 * Created by Max on 2017-08-06.
 */
public interface IJoueur {
    void tire(IJoueur cible);
    void recoitTire(Position position);
    void ajouterNavire(Navire navire);
}

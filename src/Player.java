import java.util.List;

abstract class Player {
    protected String name;
    protected boolean hasDamagedShip;
    protected Field field;
    protected List<Ship> listOfShips;
    protected boolean isAlive;
}
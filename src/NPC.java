import java.util.ArrayList;

public class NPC extends Player{
    private int level;

    public NPC(int level) {
        if (level == 0)
            this.name = "Rookie";
        if (level == 1)
            this.name = "Amateur";
        if (level == 2)
            this.name = "Professional";
        if (level > 2)
            this.name = "Nightmare";
        this.field = new Field("P2Field.txt");
        this.hasDamagedShip = false;
        this.listOfShips = new ArrayList<>();
        this.isAlive = true;
        this.level = level;
    }
}

import java.io.IOException;
import java.util.ArrayList;

public class PC extends Player {
    public PC(String name) {
        this.name = name;
        this.field = new Field("P1Field.txt");
        this.hasDamagedShip = false;
        this.listOfShips = new ArrayList<>();
        this.isAlive = true;
    }

    private void setVerticalShip(Ship ship) {
        if (isShipValid(ship)) {
            for (int i = 0; i < ship.getRank(); i++) {
                field.setCell(ship.getNose()[0] + i, ship.getNose()[1], "ship");
            }
            setDots(ship);
        }
        else {
            System.out.println("Корабль не может быть поставлен в этом месте!");
        }
    }
    private void setHorizontalShip(Ship ship) {
        if (isShipValid(ship)) {
            for (int i = 0; i < ship.getRank(); i++) {
                field.setCell(ship.getNose()[0], ship.getNose()[1] + i, "ship");
            }
            setDots(ship);
        }
        else {
            System.out.println("Корабль не может быть поставлен в этом месте!");
        }
    }
    public void setShip(Ship ship) throws IOException {
        if (ship.getIsVertical()) {
            setVerticalShip(ship);
        } else {
            setHorizontalShip(ship);
        }
        listOfShips.add(ship);
        field.refreshField();
    }


    private boolean isCordsValid(int x, int y) {
        return ((x < 10 && x > -1) && (y < 10 && y > -1));
    }
    private boolean isCellValid(int x, int y) {
        return (field.getField()[x][y] == '~' || field.getField()[x][y] == '.');
    }
    private boolean verticalValid(Ship ship) {
        if (ship.getNose()[0] + ship.getRank() > 10)
            return false;
        for (int i = 0; i < ship.getRank() + 2; i++) {
            if (isCordsValid(ship.getNose()[0] + i - 1, ship.getNose()[1] - 1))
                if (!isCellValid(ship.getNose()[0] + i - 1, ship.getNose()[1] - 1))
                    return false;
            if (isCordsValid(ship.getNose()[0] + i - 1, ship.getNose()[1] + 1))
                if (!isCellValid(ship.getNose()[0] + i - 1, ship.getNose()[1] + 1))
                    return false;
        }
        if (isCordsValid(ship.getNose()[0] - 1, ship.getNose()[1]))
            if (!isCellValid(ship.getNose()[0] - 1, ship.getNose()[1]))
                return false;
        if (isCordsValid(ship.getNose()[0] + ship.getRank(), ship.getNose()[1]))
            return isCellValid(ship.getNose()[0] + ship.getRank(), ship.getNose()[1]);
        return true;
    }
    private boolean horizontalValid(Ship ship) {
        if (ship.getNose()[1] + ship.getRank() > 10)
            return false;
        for (int i = 0; i < ship.getRank() + 2; i++) {
            if (isCordsValid(ship.getNose()[0] - 1, ship.getNose()[1] + i - 1))
                if (!isCellValid(ship.getNose()[0] - 1, ship.getNose()[1] + i - 1))
                    return false;
            if (isCordsValid(ship.getNose()[0] + 1, ship.getNose()[1] + i - 1))
                if (!isCellValid(ship.getNose()[0] + 1, ship.getNose()[1] + i - 1))
                    return false;
        }
        if (isCordsValid(ship.getNose()[0], ship.getNose()[1] - 1))
            if (!isCellValid(ship.getNose()[0], ship.getNose()[1] - 1))
                return false;
        if (isCordsValid(ship.getNose()[0], ship.getNose()[1] + ship.getRank()))
            return isCellValid(ship.getNose()[0], ship.getNose()[1] + ship.getRank());
        return true;
    }
    public boolean isShipValid(Ship ship) {
        if (ship.getIsVertical()) {
            return verticalValid(ship);
        } else {
            return horizontalValid(ship);
        }
    }


    private void verticalDots(Ship ship) {
        for (int i = 0; i < ship.getRank() + 2; i++) {
            if (isCordsValid(ship.getNose()[0] + i - 1, ship.getNose()[1] - 1))
                field.setCell(ship.getNose()[0] + i - 1, ship.getNose()[1] - 1, ".");
            if (isCordsValid(ship.getNose()[0] + i - 1, ship.getNose()[1] + 1))
                field.setCell(ship.getNose()[0] + i - 1, ship.getNose()[1] + 1, ".");
        }
        if (isCordsValid(ship.getNose()[0] - 1, ship.getNose()[1]))
            field.setCell(ship.getNose()[0] - 1, ship.getNose()[1], ".");
        if (isCordsValid(ship.getNose()[0] + ship.getRank(), ship.getNose()[1]))
            field.setCell(ship.getNose()[0] + ship.getRank(), ship.getNose()[1], ".");
    }
    private void horizontalDots(Ship ship) {
        for (int i = 0; i < ship.getRank() + 2; i++) {
            if (isCordsValid(ship.getNose()[0] - 1, ship.getNose()[1] + i - 1))
                field.setCell(ship.getNose()[0] - 1, ship.getNose()[1] + i - 1, ".");
            if (isCordsValid(ship.getNose()[0] + 1, ship.getNose()[1] + i - 1))
                field.setCell(ship.getNose()[0] + 1, ship.getNose()[1] + i - 1, ".");
        }
        if (isCordsValid(ship.getNose()[0], ship.getNose()[1] - 1))
            field.setCell(ship.getNose()[0], ship.getNose()[1] - 1, ".");
        if (isCordsValid(ship.getNose()[0], ship.getNose()[1] + ship.getRank()))
            field.setCell(ship.getNose()[0], ship.getNose()[1] + ship.getRank(), ".");
    }
    private void setDots(Ship ship) {
        if (ship.getIsVertical()) {
            verticalDots(ship);
        } else {
            horizontalDots(ship);
        }
    }
}
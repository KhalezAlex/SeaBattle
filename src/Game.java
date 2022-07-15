import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    PC pc;
    PC npc;


    private boolean isStrValid(String str) {
        if (str.charAt(0) >= 'a' && str.charAt(0) <= 'j') {
            str = str.substring(1);
            if(str.charAt(str.length() - 1) == 'v' || str.charAt(str.length() - 1) == 'h')
                str = str.substring(0,str.length() - 1);
            else return false;
            for (int i = 0; i < str.length(); i++)
                if (!Character.isDigit(str.charAt(i))){
                    return false;
                }
            return Integer.parseInt(str) > 0 && Integer.parseInt(str) < 11;
        } else return false;
    }
    private void setShip(int rank) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        if (!isStrValid(str) || !pc.isShipValid(new Ship(rank, str))) {
            refreshConsole();
            System.out.print("Неверные данные! Установите снова корабль " + rank + " ранга: ");
            setShip(rank);
        }
        else {
            pc.setShip(new Ship(rank, str));
            refreshConsole();
        }
    }
    private void setOfShips() throws IOException {
        for (int i = 4; i > 0; i--) {
            for (int j = 0; j <= 4 - i; j++){
                System.out.println("Установите корабль " + i + " ранга:");
                setShip(i);
            }
        }
    }
    public void setPC() throws IOException {
        String name;
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите свое имя: ");
        if (!Objects.equals(name = sc.next(), "")) {
            pc = new PC(name);
            pc.field.refreshField();
            refreshConsole();
            System.out.println("Игрок " + pc.name + " готов к расстановке кораблей!");
            setOfShips();
        }
        else
            setPC();
    }

    public void refreshConsole() {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(Character.toString((char) 97 + i).concat("  "));
            for (int j = 0; j < 10; j++) {
                System.out.print(Character.toString(pc.field.getField()[i][j]).concat(" "));
            }
            System.out.print(" ".concat(Character.toString((char) 97 + i).concat("\n")));
        }
    }
}
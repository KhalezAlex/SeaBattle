import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Field {
    private final char[][] field = new char[10][10];
    private final String outputFile;


    public char[][] getField() {
        return field;
    }

    public void setCell(int i, int j, String value) {
        if (value.equals("ship")) {
            this.field[i][j] = '/';
        }
        if (value.equals("shot")) {
            this.field[i][j] = 'X';
        }
        if (value.equals("miss")) {
            this.field[i][j] = 'O';
        }
        if (value.equals(".")) {
            this.field[i][j] = '.';
        }
    }

    public Field(String file) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }
        outputFile = file;
    }

    public void refreshField() throws IOException {
        Path path = Path.of(outputFile);
        Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING);
        Files.writeString(path, "   1 2 3 4 5 6 7 8 9 10\n", StandardOpenOption.APPEND);
        for (int i = 0; i < 10; i++) {
            Files.writeString(path, Character.toString((char) 97 + i).concat("  "), StandardOpenOption.APPEND);
            for (int j = 0; j < 10; j++) {
                Files.writeString(path, Character.toString(field[i][j]).concat(" "), StandardOpenOption.APPEND);
            }
            Files.writeString(path, " ".concat(Character.toString((char) 97 + i).concat("\n")), StandardOpenOption.APPEND);
        }
        Files.writeString(path, "   1 2 3 4 5 6 7 8 9 10\n", StandardOpenOption.APPEND);
    }
}
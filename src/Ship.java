public class Ship {
    private final int rank;
    private final int[] nose;
    private final boolean isVertical;
    private final boolean isDead;

    public int getRank() {
        return rank;
    }
    public int[] getNose() {
        return nose;
    }
    public boolean getIsVertical() {
        return isVertical;
    }
    public boolean getIsDead() {
        return isDead;
    }

    public Ship(int rank, String str) {
        this.rank = rank;
        isVertical = str.charAt(str.length() - 1) == 'v';
        str = str.substring(0, str.length() - 1);
        this.nose = new int[] {(int) str.charAt(0) - 97, Integer.parseInt(str.substring(1)) - 1};
        this.isDead = false;
    }
}

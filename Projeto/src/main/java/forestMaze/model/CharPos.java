package forestMaze.model;

import java.util.Objects;

public class CharPos {
    private final int x;
    private final int y;
    private final char unicode;

    public CharPos(int x, int y) {
        this.x = x;
        this.y = y;
        this.unicode = ' ';
    }

    public CharPos(int x, int y, char txt) {
        this.x = x;
        this.y = y;
        this.unicode = txt;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getUnicode() { return unicode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharPos position = (CharPos) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

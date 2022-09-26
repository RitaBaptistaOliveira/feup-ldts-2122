import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {

    Position position;

    public Element(){
        position = new Position();
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Position getPosition() {
        return position;
    }
    public abstract void draw(TextGraphics graphics);

}

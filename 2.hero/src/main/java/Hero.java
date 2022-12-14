import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element{

    public Hero(int x, int y){
        position = new Position(x, y);
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Position getPosition() {
        return position;
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight(){
        return new Position(position.getX() + 1, position.getY());
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#CA7DF9"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}

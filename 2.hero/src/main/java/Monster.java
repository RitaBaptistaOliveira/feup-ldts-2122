import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Element{

    public Monster(int x, int y) {
        position = new Position(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ED474A"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "W");
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Position getPosition() {
        return position;
    }

    public void move(Hero hero) {
        int posX = hero.getPosition().getX();
        int posY = hero.getPosition().getY();
        int monsterPosX = position.getX();
        int monsterPosY = position.getY();
        Position pos;
        if(monsterPosX < posX){
            if(monsterPosY < posY){pos = new Position(monsterPosX+1,monsterPosY+1);}
            else if(monsterPosY == posY){pos = new Position(monsterPosX+1,monsterPosY);}
            else{pos = new Position(monsterPosX+1,monsterPosY-1);}
        }
        else if(monsterPosX > posX){
            if(monsterPosY < posY){pos = new Position(monsterPosX-1,monsterPosY+1);}
            else if(monsterPosY == posY){pos = new Position(monsterPosX-1,monsterPosY);}
            else{pos = new Position(monsterPosX-1,monsterPosY-1);}
        }
        else{
            if(monsterPosY < posY){pos = new Position(monsterPosX,monsterPosY+1);}
            else{pos = new Position(monsterPosX,monsterPosY-1);}
        }
        setPosition(pos);
    }
}

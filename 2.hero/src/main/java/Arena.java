import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        hero = new Hero(10,10);
        this.walls = createWalls();
        this.monsters = createMonsters();
        this.coins = createCoins();

    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for(int i=0; i<5; i++){
            Monster newMonster = new Monster(random.nextInt(width-2) + 1, random.nextInt(height-2)+1);
            if(!monsters.contains(newMonster) && !newMonster.getPosition().equals(hero.getPosition()))
                monsters.add(newMonster);
        }
        return monsters;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for(int i=0; i<5; i++){
            Coin newCoin = new Coin(random.nextInt(width-2) + 1,random.nextInt(height-2)+1);
            if(!coins.contains(newCoin) && !newCoin.getPosition().equals(hero.getPosition()))
                coins.add(newCoin);
        }
        return coins;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#564592"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monster monster: monsters)
            monster.draw(graphics);
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
            retrieveCoins();
        }
    }

    private boolean canHeroMove(Position position) {
        int posX = position.getX();
        int posY = position.getY();
        if(posX >= 0 && posX < width && posY < height && posY >= 0){
            for (Wall wall : walls) {
                if (wall.getPosition().equals(hero.getPosition())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void retrieveCoins(){
        for (Coin coin:coins){
            if (coin.getPosition().equals(hero.getPosition())){
                coins.remove(coin);
                break;
            }
        }
    }

    public void moveMonsters(){
        for(Monster monster : monsters){
            monster.move(hero);
        }
    }

    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }
}

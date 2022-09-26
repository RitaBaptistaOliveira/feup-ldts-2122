import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

public class Game {
    private Screen screen;
    private Arena arena;
    TextGraphics graphics;
    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arena = new Arena(40,20);
        graphics = screen.newTextGraphics();
    }

    public void draw() throws IOException {
        screen.clear();
        arena.draw(graphics);
        screen.refresh();
    }

    public void run() throws IOException {
        KeyStroke key;
        do{
            draw();
            key = screen.readInput();
            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            processKey(key);
            arena.moveMonsters();
            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }
        } while(!(key.getKeyType() == KeyType.EOF));
    }

    private void processKey(KeyStroke key){
        arena.processKey(key);
    }

}
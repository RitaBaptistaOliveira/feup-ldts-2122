package forestMaze.ui;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import forestMaze.controller.reader.ArtReader;
import forestMaze.model.CharPos;
import forestMaze.model.menu.Menu;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.viewer.text.TextViewer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Lanterna implements UI {
    private int width, height;
    private final Screen screen;
    private final ArrayList<CharPos> border;

    public Lanterna(Screen screen) {
        this.screen = screen;
        this.border = new ArtReader("UIborder").read();
    }

    public Lanterna(int width, int height, String type) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig;
        if (type.equals("G")) {
            fontConfig = loadFont(15);
        } else {
            fontConfig = loadFont(23);
        }
        this.width = width;
        this.height = height;
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        this.border = new ArtReader("UIborder").read();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadFont(int size) throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/bp.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, size);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    @Override
    public menuCommand getNextMenuCommand() throws IOException {
        KeyStroke keyStroke = screen.readInput();
        if (keyStroke == null) return menuCommand.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return menuCommand.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return menuCommand.QUIT;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return menuCommand.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return menuCommand.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return menuCommand.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return menuCommand.LEFT;

        if (keyStroke.getKeyType() == KeyType.Enter) return menuCommand.SELECT;

        return menuCommand.NONE;
    }

    @Override
    public void drawArt(GameArt gameArt) {
        for (CharPos character : gameArt.getArt()) {
            drawCharacter(character.getX(), character.getY()+17, character.getUnicode(), "#FFFFFF");
        }
    }

    public String getNextAction(TextViewer textViewer, UI tui) throws IOException {
        StringBuilder command = new StringBuilder();
        do {
            KeyStroke keyStroke = screen.readInput();
            if (keyStroke.getKeyType() == KeyType.Enter) return command.toString();
            if (keyStroke.getKeyType() == KeyType.Character) command.append(keyStroke.getCharacter());
            if (keyStroke.getKeyType() == KeyType.Backspace) {
                if (command.length() != 0) command.deleteCharAt(command.length() - 1);
            }
            drawInput(command.toString());
            refresh();
            textViewer.drawElements(tui);
        } while (command.toString().length() <= 30);
        return command.toString();
    }

    public void drawInput(String input) {
        for (int x = 0; x < input.length(); x++) {
            drawCharacter(x + 2, 7, input.charAt(x), "#FFFFFF");
        }
    }

    @Override
    public void drawMenu(Menu menu) {
        for (CharPos text : menu.getMenuArt()) {
            drawCharacter(text.getX(), text.getY()+17, text.getUnicode(), "#FFFFFF");
        }
    }

    @Override
    public void drawBorders() {
        for (CharPos text : this.border) {
            drawCharacter(text.getX(), text.getY(), text.getUnicode(), "#FFFFFF"); }
    }

    @Override
    public void drawStoryText(GameText gameText) {
        int maxTextHeight = 5;
        int currentHeight = 0;
        for (String line : gameText.getStoryLines()) {
            if (currentHeight != maxTextHeight) {
                for (int x = 0; x < line.length(); x++) {
                    drawCharacter(x + 2, currentHeight + 1, line.charAt(x), "#FFFFFF");
                }
                currentHeight++;
            }
        }
    }

    @Override
    public void drawActionText(GameText gameText) {
        int maxTextHeight = 2;
        int currentHeight = 0;
        int drawX = 0;
        String line = gameText.getActionLines();
        for (int x = 0; x < line.length(); x++) {
            if (currentHeight != maxTextHeight) {
                drawCharacter(drawX + 2, currentHeight + 8, line.charAt(x), "#FFFFFF");
                drawX++;
                if (drawX == 100) {
                    drawX = 0;
                    currentHeight++;
                }
            }
        }
    }

    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y, "" + c);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
package forestMaze.ui;

import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.menu.Menu;
import forestMaze.model.game.gameText.GameText;

import java.io.IOException;

public interface UI {

    void drawBorders();
    //menu UI
    void drawMenu(Menu menu);
    //game UI
    void drawArt(GameArt gameArt);
    //text UI
    void drawStoryText(GameText gameText);
    void drawActionText(GameText gameText);

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    menuCommand getNextMenuCommand()  throws IOException;

    enum menuCommand {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
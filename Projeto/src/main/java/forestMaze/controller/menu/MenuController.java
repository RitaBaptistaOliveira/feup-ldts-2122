package forestMaze.controller.menu;

import forestMaze.Game;
import forestMaze.model.menu.Menu;
import forestMaze.ui.UI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuController {
    private final Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void step(Game game, UI.menuCommand action) throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException, InterruptedException {
        switch(action) {
            case LEFT:
                getMenu().previousEntry();
                break;
            case RIGHT:
                getMenu().nextEntry();
                break;
            case SELECT:
                if (getMenu().isSelectedExit()) game.setMenuState(null);
                if (getMenu().isSelectedStart()) game.startStory();
                break;
        }
    }
}
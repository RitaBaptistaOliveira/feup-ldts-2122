package forestMaze.model.menu;

import forestMaze.Game;
import forestMaze.controller.menu.MenuController;
import forestMaze.ui.UI;
import forestMaze.viewer.Viewer;
import forestMaze.viewer.menu.MenuViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuState {
    private final Menu menu;
    private final MenuController controller;
    private final Viewer<Menu> viewer;

    public MenuState(Menu menu) {
        this.menu = menu;
        this.viewer = getViewer();
        this.controller = getController();
    }

    private Viewer<Menu> getViewer() {
        return new MenuViewer(getMenu());
    }

    private MenuController getController() {
        return new MenuController(getMenu());
    }

    public Menu getMenu() {
        return menu;
    }

    public void step(Game game, UI ui) throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException, InterruptedException {
        viewer.draw(ui);
        UI.menuCommand action = ui.getNextMenuCommand();
        controller.step(game, action);
        viewer.draw(ui);
    }
}
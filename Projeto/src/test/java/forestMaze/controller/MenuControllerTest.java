package forestMaze.controller;

import forestMaze.Game;
import forestMaze.controller.menu.MenuController;
import forestMaze.model.menu.Menu;
import forestMaze.ui.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuControllerTest {
    private MenuController menuController;
    private Menu menu;
    private Game game;

    @BeforeEach
    void setUp() {
        menu = new Menu("main");
        menuController = new MenuController(menu);
        game = Mockito.mock(Game.class);
    }

    @Test
    void step() throws UnsupportedAudioFileException, LineUnavailableException, IOException, URISyntaxException, InterruptedException {
        menuController.step(game, UI.menuCommand.RIGHT);

        assertEquals(true, menu.isSelectedExit());
        assertEquals(false, menu.isSelectedStart());

        menuController.step(game, UI.menuCommand.RIGHT);

        assertEquals(false, menu.isSelectedExit());
        assertEquals(true, menu.isSelectedStart());
    }
}

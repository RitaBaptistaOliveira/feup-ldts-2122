package forestMaze.model;

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

public class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu("main");
    }

    @Test
    void nextEntry() {
        menu.nextEntry();

        assertEquals(true, menu.isSelectedExit());
        assertEquals(false, menu.isSelectedStart());

        menu.nextEntry();

        assertEquals(false, menu.isSelectedExit());
        assertEquals(true, menu.isSelectedStart());

        menu.previousEntry();

        assertEquals(true, menu.isSelectedExit());
        assertEquals(false, menu.isSelectedStart());
    }

}

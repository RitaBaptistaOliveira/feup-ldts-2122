package forestMaze.viewer;

import forestMaze.model.CharPos;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.room.Room;
import forestMaze.model.loaders.RoomLoader;
import forestMaze.ui.Lanterna;
import forestMaze.ui.UI;
import forestMaze.viewer.art.ArtViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ArtViewerTest {
    private UI gui;
    private ArtViewer viewer;
    private GameArt art;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        Room room1 = Mockito.mock(Room.class);
        this.gui = Mockito.mock(Lanterna.class);
        this.art = new GameArt(room1.getRoomArt());
        this.viewer = new ArtViewer(art);
    }

    @Test
    void drawArt() {
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawArt(viewer.getModel());
    }
}


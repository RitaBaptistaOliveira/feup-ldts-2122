package forestMaze.ui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LanternaTest {
    private Screen screen;
    private Lanterna gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        gui = new Lanterna(screen);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
    }

    @Test
    void drawCharacter() {
        //ainda vale a pena ter isto? kkkk

    }
}

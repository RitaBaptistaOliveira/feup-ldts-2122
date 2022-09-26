package forestMaze.viewer.text;

import forestMaze.model.CharPos;
import forestMaze.model.game.gameText.GameText;
import forestMaze.ui.UI;
import forestMaze.viewer.CharacterViewer;
import forestMaze.viewer.Viewer;

import java.util.ArrayList;

public class TextViewer extends Viewer<GameText> {
    public TextViewer(GameText text) { super(text); }

    @Override
    public void drawElements(UI ui) {
        ui.clear();
        ui.drawBorders();
        ui.drawStoryText(getModel());
        ui.drawActionText(getModel());
    }

    private <T extends CharPos> void drawElements(UI gui, ArrayList<T> elements, CharacterViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends CharPos> void drawElement(UI gui, T element, CharacterViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}


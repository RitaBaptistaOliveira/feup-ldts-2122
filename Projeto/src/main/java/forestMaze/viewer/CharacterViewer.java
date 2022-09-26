package forestMaze.viewer;

import forestMaze.model.CharPos;
import forestMaze.ui.UI;

public interface CharacterViewer<T extends CharPos> {
    void draw(T element, UI gui);
}

package forestMaze.viewer.art;

import forestMaze.model.game.gameText.GameArt;
import forestMaze.ui.UI;
import forestMaze.viewer.Viewer;

public class ArtViewer extends Viewer<GameArt> {
    public ArtViewer(GameArt gameArt) {
        super(gameArt);
    }

    @Override
    public void drawElements(UI ui) {
        ui.clear();
        ui.drawArt(getModel());
    }
}

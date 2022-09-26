package forestMaze.viewer.menu;

import forestMaze.model.menu.Menu;
import forestMaze.ui.UI;
import forestMaze.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(UI ui) {
        ui.drawMenu(getModel());
    }
}
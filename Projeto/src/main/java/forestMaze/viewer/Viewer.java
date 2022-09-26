package forestMaze.viewer;

import forestMaze.ui.UI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw(UI gui) throws IOException {
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }

    protected abstract void drawElements(UI gui);
}

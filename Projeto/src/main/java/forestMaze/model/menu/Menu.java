package forestMaze.model.menu;

import forestMaze.controller.reader.ArtReader;
import forestMaze.model.CharPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private final String filename;
    private ArrayList<CharPos> menuArt;
    private final List<String> entries;
    private int currentEntry = 0;

    public Menu(String filename) {
        this.filename = filename;
        this.entries = Arrays.asList("Start", "Exit");
        this.menuArt = new ArtReader("menu\\" + filename + currentEntry).read();
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
        this.menuArt = new ArtReader("menu\\" + filename + currentEntry).read();
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
        this.menuArt = new ArtReader("menu\\" + filename + currentEntry).read();
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public ArrayList<CharPos> getMenuArt() {
        return menuArt;
    }
}
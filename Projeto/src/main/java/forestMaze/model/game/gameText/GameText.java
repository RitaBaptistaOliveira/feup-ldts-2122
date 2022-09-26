package forestMaze.model.game.gameText;

import forestMaze.controller.reader.TextReader;

import java.util.ArrayList;

public class GameText {
    private ArrayList<String> storyLines;
    private String actionLines;

    public GameText(String filename) {
        this.storyLines = new TextReader(filename).read();
        this.actionLines = "";
    }

    public ArrayList<String> getStoryLines() {
        return storyLines;
    }

    public void setStoryLines(ArrayList<String> storyLines) {
        this.storyLines = storyLines;
    }

    public String getActionLines() {
        return actionLines;
    }

    public void setActionLines(String actionLines) {
        this.actionLines = actionLines;
    }
}
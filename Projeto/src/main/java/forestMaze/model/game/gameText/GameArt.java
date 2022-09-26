package forestMaze.model.game.gameText;

import forestMaze.controller.reader.ArtReader;
import forestMaze.model.CharPos;

import java.util.ArrayList;

public class GameArt {
    private ArrayList<CharPos> art;

    public GameArt (ArrayList<CharPos> art) {
        this.art = art;
    }

    public GameArt(String filename) {
        this.art = new ArtReader(filename).read();
    }

    public ArrayList<CharPos> getArt() {
        return art;
    }

    public void setArt(ArrayList<CharPos> art) {
        this.art = art;
    }
}
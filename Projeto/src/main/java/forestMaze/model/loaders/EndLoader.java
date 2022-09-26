package forestMaze.model.loaders;

import forestMaze.controller.reader.ArtReader;
import forestMaze.controller.reader.TextReader;
import forestMaze.model.CharPos;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;

import java.util.ArrayList;

public class EndLoader {
    public EndLoader(){}

    public void loadEnd(GameArt gameArt, GameText gameText) {
        ArrayList<CharPos> art = new ArtReader("endscreen").read();
        ArrayList<String> end = new TextReader("ending").read();
        gameArt.setArt(art);
        gameText.setStoryLines(end);
    }
}

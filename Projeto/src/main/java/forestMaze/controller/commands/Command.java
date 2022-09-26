package forestMaze.controller.commands;

import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.state.RoomState;
import forestMaze.model.game.gameText.GameText;

import java.util.ArrayList;

public abstract class Command {
    public ArrayList<RoomState> roomStates;
    public Player player;
    public String itemName;
    public GameText gameText;
    public GameArt gameArt;

    public Command() {}

    public Command(Player player, String itemName, GameText gameText, GameArt gameArt){
        this.player = player;
        this.itemName = itemName;
        this.gameText = gameText;
        this.gameArt = gameArt;
    }

    public Command(Player player, ArrayList<RoomState> roomStates, GameText gameText, GameArt gameArt){
        this.player = player;
        this.roomStates = roomStates;
        this.gameText = gameText;
        this.gameArt = gameArt;
    }

    public Command(Player player, GameText gameText, GameArt gameArt){
        this.player = player;
        this.gameText = gameText;
        this.gameArt = gameArt;
    }

    public GameText getGameText(){
        return gameText;
    }

    public GameArt getGameArt(){
        return gameArt;
    }

    public void execute() {}
}


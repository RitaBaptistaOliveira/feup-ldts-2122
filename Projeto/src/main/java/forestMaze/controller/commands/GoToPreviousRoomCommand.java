package forestMaze.controller.commands;

import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.state.RoomState;
import forestMaze.model.game.gameText.GameText;

import java.util.ArrayList;

public class GoToPreviousRoomCommand extends Command {

    public GoToPreviousRoomCommand(Player player, ArrayList<RoomState> roomStates, GameText gameText, GameArt gameArt) {
        super(player, roomStates, gameText, gameArt);
    }

    @Override
    public void execute() {
        if(player.getState() == roomStates.get(0)){
            gameText.setActionLines("There is no previous room.");
            return;
        }
        for (int i = 0; i < roomStates.size(); i++) {
            if (player.getState() == roomStates.get(i)){
                player.setState(roomStates.get(i-1));
                gameText.setStoryLines(roomStates.get(i-1).getState().getDescription());
                gameArt.setArt(roomStates.get(i-1).getState().getRoomArt());
                return;
            }
        }
        gameText.setActionLines("You can't go to the previous room.");
    }
}


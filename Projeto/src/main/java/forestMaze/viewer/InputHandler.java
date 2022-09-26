package forestMaze.viewer;

import forestMaze.controller.commands.*;

import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.state.RoomState;
import forestMaze.model.game.gameText.GameText;
import forestMaze.ui.Lanterna;
import forestMaze.viewer.text.TextViewer;

import java.io.IOException;
import java.util.ArrayList;

public class InputHandler {
    private final Player player;
    private final GameText gameText;
    private final GameArt gameArt;
    private final ArrayList<RoomState> roomStates;

    public InputHandler(Player player, GameText gameText, GameArt gameArt, ArrayList<RoomState> roomStates) {
        this.player = player;
        this.gameText = gameText;
        this.gameArt = gameArt;
        this.roomStates = roomStates;
    }

    public Command getNextCommand(Lanterna TUI, TextViewer tViewer) throws IOException{
        String input = TUI.getNextAction(tViewer, TUI);
        System.out.println(input);

        if(input.length() > 4 && input.startsWith("Hold")) return new HoldCommand(player, input.substring(5), gameText, gameArt);
        else if(input.length() > 7 && input.startsWith("Pick up")) return new PickUpCommand(player, input.substring(8), gameText, gameArt);
        else if(input.length() > 7 && input.startsWith("Look at")) return new LookAtCommand(player,input.substring(8),gameText, gameArt);
        else if(input.length() > 6 && input.startsWith("Use on")) return new UseOnCommand(player, input.substring(7), gameText, gameArt);
        else if(input.length() > 13 && input.startsWith("Interact with")) return new InteractWithCommand(player, input.substring(14), gameText, gameArt);
        else if(input.length() >= 15 && input.startsWith("Go to next room")) return new GoToNextRoomCommand(player, roomStates, gameText, gameArt);
        else if(input.length() >= 19 && input.startsWith("Go to previous room")) return new GoToPreviousRoomCommand(player,roomStates,gameText,gameArt);
        else if(input.length() >= 14 && input.startsWith("Show inventory")) return new ShowInventoryCommand(player, gameText, gameArt);

        return new DoNothingCommand();
    }
}
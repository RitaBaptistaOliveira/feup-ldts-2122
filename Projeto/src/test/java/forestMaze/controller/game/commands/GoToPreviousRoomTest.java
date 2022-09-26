package forestMaze.controller.game.commands;

import forestMaze.controller.commands.*;
import forestMaze.model.CharPos;
import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.model.game.room.Room;
import forestMaze.model.game.state.RoomState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class GoToPreviousRoomTest {
    Player player;
    Room room1, room2;
    RoomState state1, state2;
    ArrayList<RoomState> states;
    GameText gameText;
    GameArt gameArt;

    @BeforeEach
    void commandsSetup(){
        room1 = mock(Room.class);
        room2 = mock(Room.class);
        state1 = mock(RoomState.class);
        state2 = mock(RoomState.class);
        states = mock(ArrayList.class);
        gameText = new GameText("storytext");
        gameArt = new GameArt(new ArrayList<>());
    }

    @Test
    void goToPreviousRoomInvalid(){
        player = new Player(state1);
        Mockito.when(states.get(0)).thenReturn(state1);

        GoToPreviousRoomCommand command = new GoToPreviousRoomCommand(player, states, gameText, gameArt);
        command.execute();

        Assertions.assertEquals("There is no previous room.", command.getGameText().getActionLines());

    }

    @Test
    void goToPreviousRoomValid(){
        int i = 1;
        player = new Player(state2);

        ArrayList<String> description = new ArrayList<>();
        description.add("Success!");

        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        Mockito.when(states.get(0)).thenReturn(state1);

        Mockito.when(states.size()).thenReturn(2);
        Mockito.when(states.get(i)).thenReturn(state2);
        Mockito.when(states.get(0)).thenReturn(state1);
        Mockito.when(states.get(0).getState()).thenReturn(room1);
        Mockito.when(states.get(0).getState().getDescription()).thenReturn(description);
        Mockito.when(states.get(0).getState().getRoomArt()).thenReturn(art);

        GoToPreviousRoomCommand command = new GoToPreviousRoomCommand(player, states, gameText, gameArt);
        command.execute();

        Assertions.assertEquals(state1, player.getState());
        Assertions.assertEquals("Success!", command.getGameText().getStoryLines().get(0));
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
    }
}
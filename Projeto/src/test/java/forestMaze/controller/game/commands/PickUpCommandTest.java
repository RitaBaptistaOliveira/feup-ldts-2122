package forestMaze.controller.game.commands;

import forestMaze.controller.commands.PickUpCommand;
import forestMaze.model.CharPos;
import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.model.game.item.*;
import forestMaze.model.game.room.Room;
import forestMaze.model.game.state.RoomState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class PickUpCommandTest {

    Player player;
    Room room;
    RoomState state;
    GameText gameText;
    GameArt gameArt;

    @BeforeEach
    void commandsSetup() {
        room = mock(Room.class);
        state = mock(RoomState.class);
        gameText = new GameText("storytext");
        gameArt = new GameArt(new ArrayList<>());

    }

    @Test
    void pickUpEmptyString() {
        player = mock(Player.class);
        PickUpCommand command= new PickUpCommand(player, "", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You need to write an item!", command.getGameText().getActionLines());
    }

    @Test
    void pickUpInvalidItem(){
        player = mock(Player.class);
        HoldableItem item = mock(HoldableItem.class);
        ArrayList<HoldableItem> holdableItems = new ArrayList<>();
        holdableItems.add(item);

        Mockito.when(player.getState()).thenReturn(state);
        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getHoldableItems()).thenReturn(holdableItems);
        Mockito.when(item.getName()).thenReturn("notItemName");

        PickUpCommand command= new PickUpCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("The itemName can't be picked up...",  command.getGameText().getActionLines());
    }

    @Test
    void pickUpValidItem(){
        player = new Player(state);
        String description = "Success!";
        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        HoldableItem item = mock(HoldableItem.class);
        ArrayList<HoldableItem> holdableItems = new ArrayList<>();
        holdableItems.add(item);

        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getHoldableItems()).thenReturn(holdableItems);
        Mockito.when(item.getName()).thenReturn("itemName");
        Mockito.when(item.getAvailable()).thenReturn(true);
        Mockito.when(item.getArt()).thenReturn(art);

        PickUpCommand command= new PickUpCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You picked up the itemName!",  command.getGameText().getActionLines());
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
    }
}

package forestMaze.controller.game.commands;

import forestMaze.controller.commands.HoldCommand;
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

public class HoldCommandTest {
    Player player;
    Room room;
    RoomState state;
    GameText gameText;
    GameArt gameArt;
    String itemName;

    @BeforeEach
    void commandsSetup() {
        room = mock(Room.class);
        state = mock(RoomState.class);
        gameText = new GameText("storytext");
        gameArt = new GameArt(new ArrayList<>());
    }

    @Test
    void HoldEmptyString() {
        player = mock(Player.class);
        HoldCommand command = new HoldCommand(player, "", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You need to write an item!", command.getGameText().getActionLines());
    }

    @Test
    void HoldInvalidItem() {
        player = mock(Player.class);
        itemName = "itemName";
        Mockito.when(player.existsInInventory(itemName)).thenReturn(-1);

        HoldCommand command = new HoldCommand(player, itemName, gameText, gameArt);
        command.execute();

        Assertions.assertEquals("The itemName is not in your inventory!", command.getGameText().getActionLines());
    }

    @Test
    void HoldValidItem() {
        player = new Player(state);
        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        HoldableItem item = mock(HoldableItem.class);
        player.addItem(item);

        Mockito.when(item.getName()).thenReturn("itemName");
        Mockito.when(player.existsInInventory("itemName")).thenReturn(1);
        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getRoomArt()).thenReturn(art);

        HoldCommand command = new HoldCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertEquals(1, player.getItemInHand());
        Assertions.assertEquals("You're now holding the itemName.", command.getGameText().getActionLines());
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
   }
}

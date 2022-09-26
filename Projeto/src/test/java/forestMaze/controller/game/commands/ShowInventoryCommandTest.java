package forestMaze.controller.game.commands;

import forestMaze.controller.commands.ShowInventoryCommand;
import forestMaze.model.CharPos;
import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.model.game.item.HoldableItem;
import forestMaze.model.game.room.Room;
import forestMaze.model.game.state.RoomState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class ShowInventoryCommandTest {
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
        player = mock(Player.class);
    }
    @Test
    void showInventoryTest(){
        String description = "Success!";
        StringBuilder output = new StringBuilder(description);

        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        HoldableItem item = mock(HoldableItem.class);
        ArrayList<HoldableItem> holdableItems = new ArrayList<>();
        holdableItems.add(item);

        Mockito.when(player.getState()).thenReturn(state);
        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getRoomArt()).thenReturn(art);
        Mockito.when(player.getInventory()).thenReturn(holdableItems);
        Mockito.when(item.getName()).thenReturn("itemName");

        ShowInventoryCommand command = new ShowInventoryCommand(player, gameText, gameArt);
        command.execute();

        Assertions.assertEquals("itemName " , command.getGameText().getActionLines());
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
    }
}

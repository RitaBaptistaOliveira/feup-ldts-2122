package forestMaze.controller.game.commands;

import forestMaze.controller.commands.InteractWithCommand;
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

public class InteractWithCommandTest {
    Player player;
    Room room;
    RoomState state;
    GameText gameText;
    GameArt gameArt;

    @BeforeEach
    void commandsSetup() {
        room = mock(Room.class);
        gameText = new GameText("storytext");
        gameArt = new GameArt(new ArrayList<>());
    }

    @Test
    void interactWithEmptyString() {
        state = mock(RoomState.class);
        player = mock(Player.class);
        InteractWithCommand command = new InteractWithCommand(player, "", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You need to write an item!", command.getGameText().getActionLines());
    }

    @Test
    void interactWithValidUsedItem() {
        state = mock(RoomState.class);
        player = new Player(state);

        InteractableItem item = mock(InteractableItem.class);
        ArrayList<InteractableItem> items = new ArrayList<>();
        items.add(item);

        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getInteractableItems()).thenReturn(items);
        Mockito.when(item.getName()).thenReturn("itemName");
        Mockito.when(item.getInteractWithItemId()).thenReturn(-1);
        Mockito.when(item.isUsed()).thenReturn(true);

        InteractWithCommand command = new InteractWithCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You can't use that itemName any more.", command.getGameText().getActionLines());
    }

    @Test
    void interactWithValidUnusedExitItem() {
        state = new RoomState(false, room);
        player = new Player(state);

        String description = "Success!";

        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        InteractableItem item = mock(InteractableItem.class);
        ArrayList<InteractableItem> items = new ArrayList<>();
        items.add(item);

        HoldableItem holdableItem = mock(HoldableItem.class);
        ArrayList<HoldableItem> holdableItems = new ArrayList<>();
        holdableItems.add(holdableItem);

        Mockito.when(player.getState().getState().getInteractableItems()).thenReturn(items);
        Mockito.when(item.getName()).thenReturn("itemName");
        Mockito.when(item.getInteractWithItemId()).thenReturn(-1);
        Mockito.when(item.isUsed()).thenReturn(false);

        Mockito.when(holdableItem.getId()).thenReturn(0);
        Mockito.when(item.getHoldableItemId()).thenReturn(1);

        Mockito.when(item.getInteractionDescription()).thenReturn(description);
        Mockito.when(item.getArt()).thenReturn(art);

        Mockito.when(item.getId()).thenReturn(2);
        Mockito.when(player.getState().getState().getExitId()).thenReturn(2);

        InteractWithCommand command = new InteractWithCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertTrue(player.getState().getCanExit());
        Assertions.assertEquals("Success!", command.getGameText().getActionLines());
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
    }
}

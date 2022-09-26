package forestMaze.controller.game.commands;

import forestMaze.controller.commands.LookAtCommand;
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

public class LookAtCommandTest {
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
    void LookatEmptyString(){
        player = mock(Player.class);
        LookAtCommand command = new LookAtCommand(player, "", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You need to write an item!", command.getGameText().getActionLines());
    }

    @Test
    void LookAtInvalidItem() {
        player = mock(Player.class);
        itemName = "itemName";

        InteractableItem interactableItem = mock(InteractableItem.class);
        ArrayList<InteractableItem> interactableItems = new ArrayList<>();
        interactableItems.add(interactableItem);

        HoldableItem holdableItem = mock(HoldableItem.class);
        ArrayList<HoldableItem> holdableItems = new ArrayList<>();
        holdableItems.add(holdableItem);

        Mockito.when(player.getState()).thenReturn(state);
        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getHoldableItems()).thenReturn(holdableItems);
        Mockito.when(player.getState().getState().getInteractableItems()).thenReturn(interactableItems);

        Mockito.when(holdableItem.getName()).thenReturn("notItemName");
        Mockito.when(interactableItem.getName()).thenReturn("notItemName");

        LookAtCommand command = new LookAtCommand(player, itemName, gameText, gameArt);
        command.execute();

        Assertions.assertEquals("There's nothing called itemName", command.getGameText().getActionLines());
    }


    @Test
    void LookAtValidHoldableItem() {
        player = mock(Player.class);
        itemName = "itemName";

        String description = "Success!";

        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        HoldableItem holdableItem = mock(HoldableItem.class);
        ArrayList<HoldableItem> holdableItems = new ArrayList<>();
        holdableItems.add(holdableItem);

        Mockito.when(player.getState()).thenReturn(state);
        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getHoldableItems()).thenReturn(holdableItems);
        Mockito.when(holdableItem.getName()).thenReturn("itemName");
        Mockito.when(holdableItem.getDescription()).thenReturn(description);
        Mockito.when(holdableItem.getArt()).thenReturn(art);

        LookAtCommand command = new LookAtCommand(player, itemName, gameText, gameArt);
        command.execute();

        Assertions.assertEquals("Success!", command.getGameText().getActionLines());
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
    }

    @Test
    void LookAtValidInteractableItem() {
        player = mock(Player.class);
        itemName = "itemName";

        String description = "Success!";
        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        InteractableItem interactableItem = mock(InteractableItem.class);
        ArrayList<InteractableItem> interactableItems = new ArrayList<>();
        interactableItems.add(interactableItem);

        HoldableItem holdableItem = mock(HoldableItem.class);
        ArrayList<HoldableItem> holdableItems = new ArrayList<>();
        holdableItems.add(holdableItem);

        Mockito.when(player.getState()).thenReturn(state);
        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getHoldableItems()).thenReturn(holdableItems);
        Mockito.when(player.getState().getState().getInteractableItems()).thenReturn(interactableItems);
        Mockito.when(interactableItem.getDescription()).thenReturn(description);
        Mockito.when(interactableItem.getArt()).thenReturn(art);

        Mockito.when(holdableItem.getName()).thenReturn("notItemName");
        Mockito.when(interactableItem.getName()).thenReturn("itemName");

        LookAtCommand command = new LookAtCommand(player, itemName, gameText, gameArt);
        command.execute();

        Assertions.assertEquals("Success!", command.getGameText().getActionLines());
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
    }
}

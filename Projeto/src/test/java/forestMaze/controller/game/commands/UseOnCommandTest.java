package forestMaze.controller.game.commands;

import forestMaze.controller.commands.*;
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

public class UseOnCommandTest {
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
    void UseOnEmptyString() {
        player = mock(Player.class);
        UseOnCommand command = new UseOnCommand(player, "", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You need to write an item!", command.getGameText().getActionLines());
    }

    @Test
    void UseOnInvalidItem() {
        player = new Player(state);
        InteractableItem interactableItem = mock(InteractableItem.class);
        ArrayList<InteractableItem> items = new ArrayList<>();
        items.add(interactableItem);

        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getInteractableItems()).thenReturn(items);
        Mockito.when(interactableItem.getName()).thenReturn("notItemName");

        UseOnCommand command = new UseOnCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You tried to do something with the itemName, but nothing happened...", command.getGameText().getActionLines());
    }

    @Test
    void UseOnUsedItem() {
        player = new Player(state);
        InteractableItem interactableItem = mock(InteractableItem.class);
        ArrayList<InteractableItem> items = new ArrayList<>();
        items.add(interactableItem);

        Mockito.when(player.getState().getState()).thenReturn(room);
        Mockito.when(player.getState().getState().getInteractableItems()).thenReturn(items);
        Mockito.when(interactableItem.getName()).thenReturn("itemName");
        Mockito.when(interactableItem.isUsed()).thenReturn(true);

        UseOnCommand command = new UseOnCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("You can't do anything else with the itemName." , command.getGameText().getActionLines());
    }

    @Test
    void UseOnUnusedItem() {
        state = new RoomState(false, room);
        player = new Player(state);
        player.setItemInHand(1);
        String description = "Success!";

        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        InteractableItem interactableItem = mock(InteractableItem.class);
        ArrayList<InteractableItem> items = new ArrayList<>();
        items.add(interactableItem);

        HoldableItem item = mock(HoldableItem.class);
        ArrayList<HoldableItem> holdableItems = new ArrayList<>();
        holdableItems.add(item);

        Mockito.when(player.getState().getState().getInteractableItems()).thenReturn(items);
        Mockito.when(interactableItem.getName()).thenReturn("itemName");
        Mockito.when(interactableItem.isUsed()).thenReturn(false);

        Mockito.when(interactableItem.getInteractWithItemId()).thenReturn(1);
        Mockito.when(interactableItem.getId()).thenReturn(1);
        Mockito.when(player.getState().getState().getExitId()).thenReturn(0);
        Mockito.when(player.getState().getState().getHoldableItems()).thenReturn(holdableItems);

        Mockito.when(item.getId()).thenReturn(1);
        Mockito.when(interactableItem.getHoldableItemId()).thenReturn(1);

        Mockito.when(interactableItem.getInteractionDescription()).thenReturn(description);
        Mockito.when(interactableItem.getArt()).thenReturn(art);

        UseOnCommand command = new UseOnCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertEquals("Success!" , command.getGameText().getActionLines());
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
    }

    @Test
    void UseOnExitItem() {
        state = new RoomState(false, room);
        player = new Player(state);
        player.setItemInHand(1);
        String description = "Success!";

        CharPos charPos = new CharPos(1, 2, 'c');
        ArrayList<CharPos> art = new ArrayList<>();
        art.add(charPos);

        InteractableItem interactableItem = mock(InteractableItem.class);
        ArrayList<InteractableItem> items = new ArrayList<>();
        items.add(interactableItem);

        Mockito.when(player.getState().getState().getInteractableItems()).thenReturn(items);
        Mockito.when(interactableItem.getName()).thenReturn("itemName");
        Mockito.when(interactableItem.isUsed()).thenReturn(false);
        Mockito.when(interactableItem.getId()).thenReturn(0);

        Mockito.when(interactableItem.getInteractWithItemId()).thenReturn(1);
        Mockito.when(player.getState().getState().getExitId()).thenReturn(0);
        Mockito.when(interactableItem.getInteractionDescription()).thenReturn(description);
        Mockito.when(interactableItem.getArt()).thenReturn(art);

        UseOnCommand command = new UseOnCommand(player, "itemName", gameText, gameArt);
        command.execute();

        Assertions.assertTrue(player.getState().getCanExit());
        Assertions.assertEquals("Success!" , command.getGameText().getActionLines());
        Assertions.assertEquals(1, command.getGameArt().getArt().get(0).getX());
        Assertions.assertEquals(2, command.getGameArt().getArt().get(0).getY());
        Assertions.assertEquals('c', command.getGameArt().getArt().get(0).getUnicode());
    }

}

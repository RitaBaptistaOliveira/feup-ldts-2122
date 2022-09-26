package forestMaze;

import forestMaze.controller.commands.Command;
import forestMaze.controller.audio.AudioController;
import forestMaze.model.audio.Audio;
import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.model.game.room.Room;
import forestMaze.model.game.state.RoomState;
import forestMaze.model.loaders.EndLoader;
import forestMaze.model.loaders.RoomLoader;
import forestMaze.model.menu.Menu;
import forestMaze.model.menu.MenuState;
import forestMaze.ui.Lanterna;
import forestMaze.viewer.InputHandler;
import forestMaze.viewer.art.ArtViewer;
import forestMaze.viewer.text.TextViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Game {
    private final Lanterna gui;
    private final Lanterna tui;

    private Audio backgroundAudio;

    private MenuState menuState;
    private ArrayList<MenuState> menus;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new Lanterna(170, 55, "G");
        this.tui = new Lanterna(170, 10, "T");
        this.menus = new ArrayList<>();
        this.menus.add(new MenuState(new Menu("main")));
        this.menuState = menus.get(0);
    }

    public void setMenuState(MenuState state) {
        this.menuState = state;
    }

    public static void exit() {
        System.exit(0);
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        new Game().startMenu();
        exit();
    }

    private void startMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException, URISyntaxException, InterruptedException {
        backgroundAudio = new Audio("menu");
        AudioController audioController = new AudioController(backgroundAudio);
        audioController.play();

        while (menuState != null) {
            menuState.step(this, gui);
            gui.refresh();
        }
        gui.close();
        tui.close();
    }

    public void startStory() throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException, InterruptedException {
        backgroundAudio = new Audio("game");
        AudioController audioController = new AudioController(backgroundAudio);
        audioController.play();

        //Loading rooms
        Room room1 = new RoomLoader(1).loadRoom();
        Room room2 = new RoomLoader(2).loadRoom();


        //Creating RoomStates
        RoomState state1 = new RoomState(false, room1);
        RoomState state2 = new RoomState(false, room2);
        ArrayList<RoomState> roomStates = new ArrayList<>();
        roomStates.add(state1);
        roomStates.add(state2);

        Player player = new Player(state1);
        EndLoader ending = new EndLoader();
        GameText gameText = new GameText("storytext");
        GameArt gameArt = new GameArt(new ArrayList<>());
        InputHandler inputHandler = new InputHandler(player, gameText, gameArt,roomStates);
        TextViewer tViewer = new TextViewer(gameText);
        ArtViewer artViewer = new ArtViewer(gameArt);
        updateScreen(tViewer,artViewer);

        Thread.sleep(25000);
        gameArt.setArt(room1.getRoomArt());
        gameText.setStoryLines(room1.getDescription());
        updateScreen(tViewer, artViewer);

        while (!gameText.getActionLines().equals("You reached the end of this demo!")){
            Command command = inputHandler.getNextCommand(tui, tViewer);
            command.execute();
            gui.refresh();
            tui.refresh();
            tViewer.draw(tui);
            artViewer.draw(gui);
        }
        ending.loadEnd(gameArt,gameText);
        updateScreen(tViewer, artViewer);
        Thread.sleep(7000);
    }

    private void updateScreen(TextViewer tViewer, ArtViewer artViewer) throws IOException {
        tViewer.draw(tui);
        artViewer.draw(gui);
        gui.refresh();
        tui.refresh();
    }
}
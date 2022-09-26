package forestMaze.model.game.state;

import forestMaze.model.game.room.Room;

public class RoomState extends State<Room> {
    public RoomState(boolean canExit, Room room) {
        super(canExit, room);
    }
}
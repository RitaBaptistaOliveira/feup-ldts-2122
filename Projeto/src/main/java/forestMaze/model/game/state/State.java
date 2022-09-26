package forestMaze.model.game.state;

public abstract class State<T> {
    private final T state;
    private boolean canExit;

    public State(boolean canExit, T state){
        this.canExit = canExit;
        this.state = state;
    }

    public boolean getCanExit() { return canExit; }

    public void setCanExit(boolean canExit) {
        this.canExit = canExit;
    }

    public T getState() {
        return state;
    }
}

public class Configuration {
    private State state;
    private int pos;

    public Configuration(State state, int pos) {
        this.pos = pos;
        this.state = state;
    }

    public int getPosition() {
        return pos;
    }

    public State getState() {
        return state;
    }
}

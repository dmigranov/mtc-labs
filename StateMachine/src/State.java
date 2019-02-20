import java.util.HashMap;
import java.util.Map;

public class State {
    private boolean isFinal;
    private Map<Character, State> neighbourStates = new HashMap<>();

    public State(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public void addNeighbour(char c, State state)
    {
        neighbourStates.put(c, state);
    }

    public State getNeighbour(char c)
    {
        return neighbourStates.get(c);
    }

    public boolean isFinal() {
        return isFinal;
    }
}

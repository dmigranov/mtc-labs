import java.util.HashMap;
import java.util.Map;

public class State {
    boolean isFinal;
    int number;
    private Map<Character, State> neighbourStates = new HashMap<>();

    public State(int number, boolean isFinal) {
        this.number = number;
        this.isFinal = false;
    }

    public void setFinal()
    {
        isFinal = true;
    }

    public void addNeighbour(char c, State state)
    {
        neighbourStates.put(c, state);
    }
}

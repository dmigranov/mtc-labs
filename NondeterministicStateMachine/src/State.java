import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
    private boolean isFinal;
    private Map<Character, List<State>> neighbourStates = new HashMap<>();

    public State(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public void addNeighbour(char c, State state)
    {
        //neighbourStates.put(c, state);
        List<State> neighbourList = neighbourStates.get(c);
        if (neighbourList != null)
            neighbourList.add(state);
        else
        {
            neighbourList = new ArrayList<>();
            neighbourList.add(state);
            neighbourStates.put(c, neighbourList);
        }
    }

    public List<State> getNeighbours(char c)
    {
        return neighbourStates.get(c);
    }

    public boolean isFinal() {
        return isFinal;
    }
}

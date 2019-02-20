import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StateMachine {
    private Map<Integer, State> states = new HashMap<>();
    State startState;

    State currentState;

    public StateMachine(Reader reader) throws IOException
    {
        startState = new State(0, false);
        states.pu

        BufferedReader bufferedReader = new BufferedReader(reader);
        String finalStatesString = bufferedReader.readLine();              //список финальных состояний
        String[] splitted = finalStatesString.split(" ");
        for(String s : splitted)
        {
            int number =
        }
    }
}

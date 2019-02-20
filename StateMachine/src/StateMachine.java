import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StateMachine {
    private Map<Integer, State> states = new HashMap<>();

    State currentState;

    public StateMachine(Reader reader) throws IOException
    {
        /*State startState = new State( false);
        states.put(0, startState);*/


        BufferedReader bufferedReader = new BufferedReader(reader);
        String finalStatesString = bufferedReader.readLine();              //список финальных состояний
        String[] splitted = finalStatesString.split(" ");
        for(String s : splitted)
        {
            int number = Integer.parseInt(s);
            states.put(number, new State(true));
        }
        if(!states.containsKey(0))
        {
            State startState = new State( false);
            states.put(0, startState);
        }


    }
}

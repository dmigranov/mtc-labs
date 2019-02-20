import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class StateMachine {
    private Map<Integer, State> states = new HashMap<>();

    private State currentState;

    public StateMachine(Reader reader) throws IOException
    {
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

        String s;
        while((s = bufferedReader.readLine()) != null)
        {
            splitted = s.split(" ");
            int fromNumber = Integer.parseInt(splitted[0]);
            int toNumber = Integer.parseInt(splitted[2]);
            char c = splitted[1].charAt(0);

            State from = states.get(fromNumber);
            if (from == null) //такого ещё нет
            {
                from = new State(false);
                states.put(fromNumber, from);
            }

            State to = states.get(toNumber);
            if (to == null) //такого ещё нет
            {
                to = new State(false);
                states.put(toNumber, to);
            }

            from.addNeighbour(c, to);
        }

    }


    boolean parse(Reader reader) throws IOException, StateMachineException
    {
        currentState = states.get(0);

        int t;
        while((t = reader.read()) != - 1)
        {
            char c = (char)t;

            currentState = currentState.getNeighbour(c);
            if(currentState == null)    //соседа по такому символу нет...
                //return false;
                //break;  //типа если при чтении
                throw new StateMachineException("Transition is impossible");
        }
        return currentState.isFinal();
    }
}

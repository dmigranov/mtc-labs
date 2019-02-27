import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

class NondeterministicStateMachine {
    private Map<Integer, State> states = new HashMap<>();

    public NondeterministicStateMachine(Reader reader) throws IOException
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
            //возможно, стоит сделать как в тетрадке (формат)? спросить
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


    boolean parse(Reader reader) throws IOException
    {
        Deque<Configuration> stack = new ArrayDeque<>();
        String s = new BufferedReader(reader).readLine();

        Configuration configuration = new Configuration(states.get(0), 0);  //-1
        stack.push(configuration);

        while(!stack.isEmpty())
        {
            configuration = stack.pop();
            int pos = configuration.getPosition();
            State state = configuration.getState();
            if(pos == s.length())   //прочли всё
                if(state.isFinal())
                    return true;
                else
                    continue;

            char c = s.charAt(pos);
            List<State> neighbours = state.getNeighbours(c);
            if(neighbours == null)
                continue;

            for(State neighbour : neighbours)
            {
                stack.push(new Configuration(neighbour, pos+1));
            }
        }

        return false;
    }
}

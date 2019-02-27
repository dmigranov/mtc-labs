import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws IOException, StateMachineException
    {
        if(args.length < 2)
            System.exit(1);
        Reader stateMachineReader = new FileReader(args[0]);
        Reader wordReader = new FileReader(args[1]);

        NondeterministicStateMachine machine = new NondeterministicStateMachine(stateMachineReader);
        boolean parsed = machine.parse(wordReader);
        if(parsed)
            System.out.println("OK");
        else
            System.out.println("Not OK");
    }
}

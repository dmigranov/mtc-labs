import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class StateMachineTest {

    @org.junit.jupiter.api.Test
    void parse() throws IOException {
        Reader stateMachineReader;
        StateMachine machine;

        stateMachineReader = new FileReader("machine1.txt");
        machine = new StateMachine(stateMachineReader);
        assertTrue(machine.parse(new StringReader("aaabbaba")));
        assertFalse(machine.parse(new StringReader("aaabbava")));
        assertFalse(machine.parse(new StringReader("babaaa")));
        assertFalse(machine.parse(new StringReader("aabaabaaa")));


        stateMachineReader = new FileReader("machine2.txt");
        machine = new StateMachine(stateMachineReader);
        assertFalse(machine.parse(new StringReader("aaabbaba")));
        assertFalse(machine.parse(new StringReader("aabbava")));    //так должно быть? вопрос в том, как быть с переходами, не определёнными в файле
        assertTrue(machine.parse(new StringReader("babaaa")));
        assertTrue(machine.parse(new StringReader("aabaabaaa")));

        stateMachineReader = new FileReader("machine3.txt");
        machine = new StateMachine(stateMachineReader);
        assertTrue(machine.parse(new StringReader("abbaababba")));
        assertTrue(machine.parse(new StringReader("baabbaaabb")));
        assertFalse(machine.parse(new StringReader("baabbaaaba")));
        assertFalse(machine.parse(new StringReader("aaabbaaabb")));
    }
}
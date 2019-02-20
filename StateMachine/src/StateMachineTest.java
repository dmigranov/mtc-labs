import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class StateMachineTest {

    @org.junit.jupiter.api.Test
    void parse() throws IOException, StateMachineException {
        Reader stateMachineReader;

        StateMachine m1;
        stateMachineReader = new FileReader("machine1.txt");
        m1 = new StateMachine(stateMachineReader);
        assertTrue(m1.parse(new StringReader("aaabbaba")));
        assertThrows(StateMachineException.class, () -> m1.parse(new StringReader("aaabbava")));
        assertFalse(m1.parse(new StringReader("babaaa")));
        assertFalse(m1.parse(new StringReader("aabaabaaa")));

        StateMachine m2;
        stateMachineReader = new FileReader("machine2.txt");
        m2 = new StateMachine(stateMachineReader);
        assertFalse(m2.parse(new StringReader("aaabbaba")));
        assertThrows(StateMachineException.class, () -> m2.parse(new StringReader("aabbava")));    //так должно быть? вопрос в том, как быть с переходами, не определёнными в файле
        assertTrue(m2.parse(new StringReader("babaaa")));
        assertTrue(m2.parse(new StringReader("aabaabaaa")));

        StateMachine m3;
        stateMachineReader = new FileReader("machine3.txt");
        m3 = new StateMachine(stateMachineReader);
        assertTrue(m3.parse(new StringReader("abbaababba")));
        assertTrue(m3.parse(new StringReader("baabbaaabb")));
        assertFalse(m3.parse(new StringReader("baabbaaaba")));
        assertFalse(m3.parse(new StringReader("aaabbaaabb")));
    }
}
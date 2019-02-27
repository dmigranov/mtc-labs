import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class NondeterministicStateMachineTest {

    @org.junit.jupiter.api.Test
    void parse() throws IOException, StateMachineException {
        Reader stateMachineReader;

        NondeterministicStateMachine m1;
        stateMachineReader = new FileReader("machine1.txt");     //все состоящие из ba и оканчивающиеся на ba
        m1 = new NondeterministicStateMachine(stateMachineReader);
        assertTrue(m1.parse(new StringReader("aaabbaba")));
        assertTrue(m1.parse(new StringReader("ba")));
        //assertFalse(m1.parse(new StringReader("cba")));
        assertFalse(m1.parse(new StringReader("baca")));
        assertFalse(m1.parse(new StringReader("aaaaa")));
        assertFalse(m1.parse(new StringReader("aabaa")));
        assertFalse(m1.parse(new StringReader("abaaa")));
        assertFalse(m1.parse(new StringReader("ab")));

        NondeterministicStateMachine m2;
        stateMachineReader = new FileReader("machine2.txt");    //все состоящие из ba и начинающиеся с ab
        m2 = new NondeterministicStateMachine(stateMachineReader);
        assertTrue(m2.parse(new StringReader("ab")));
        assertTrue(m2.parse(new StringReader("abba")));
        assertTrue(m2.parse(new StringReader("abababababab")));
        assertFalse(m2.parse(new StringReader("bab")));
        assertFalse(m2.parse(new StringReader("bbab")));
        assertFalse(m2.parse(new StringReader("aab")));

        NondeterministicStateMachine m3;
        stateMachineReader = new FileReader("machine3.txt");    //состоящие из ab и содержащие bb подряд
        m3 = new NondeterministicStateMachine(stateMachineReader);
        assertTrue(m3.parse(new StringReader("bb")));
        assertTrue(m3.parse(new StringReader("abba")));
        assertTrue(m3.parse(new StringReader("aaabbb")));
        assertTrue(m3.parse(new StringReader("bbbaaa")));
        assertFalse(m3.parse(new StringReader("abababa")));
        assertFalse(m3.parse(new StringReader("ba")));

        NondeterministicStateMachine m4;
        stateMachineReader = new FileReader("machine4.txt");    //состоящие из ab, нач. с b и заканч. b
        m4 = new NondeterministicStateMachine(stateMachineReader);
        assertTrue(m4.parse(new StringReader("b")));
        assertTrue(m4.parse(new StringReader("bab")));
        assertTrue(m4.parse(new StringReader("bb")));
        assertTrue(m4.parse(new StringReader("bbbbbbb")));
        assertTrue(m4.parse(new StringReader("baaaaab")));
        assertFalse(m4.parse(new StringReader("aaaaa")));
        assertFalse(m4.parse(new StringReader("baaaaaa")));
        assertFalse(m4.parse(new StringReader("abb")));

        NondeterministicStateMachine m5;
        stateMachineReader = new FileReader("machine5.txt");    //состоящие из ab, не сод. двух b ид. подряд
        m5 = new NondeterministicStateMachine(stateMachineReader);
        assertTrue(m5.parse(new StringReader("aaaaa")));
        assertTrue(m5.parse(new StringReader("abaaababaa")));
        assertTrue(m5.parse(new StringReader("bababababa")));
        assertFalse(m5.parse(new StringReader("bbaaab")));
        assertFalse(m5.parse(new StringReader("aaaabbbaaa")));
        assertFalse(m5.parse(new StringReader("aabbaa")));

        NondeterministicStateMachine m6;
        stateMachineReader = new FileReader("machine6.txt");    //состоящие из ab, cод. два а подряд или два b
        m6 = new NondeterministicStateMachine(stateMachineReader);
        assertTrue(m6.parse(new StringReader("aa")));
        assertTrue(m6.parse(new StringReader("abababbabab")));
        assertTrue(m6.parse(new StringReader("bb")));
        assertTrue(m6.parse(new StringReader("bbbbbbbbb")));
        assertTrue(m6.parse(new StringReader("aabb")));
        assertFalse(m6.parse(new StringReader("bababa")));
        assertFalse(m6.parse(new StringReader("abababa")));
        assertFalse(m6.parse(new StringReader("ab")));
    }
}
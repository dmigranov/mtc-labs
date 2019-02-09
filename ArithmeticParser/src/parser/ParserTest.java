package parser;

import lexer.Lexer;
import lexer.LexerException;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void calculate() throws ParserException
    {
        Parser parser;

        parser = new Parser(new StringReader("3+5"));
        assertEquals(8, parser.calculate());

        parser = new Parser(new StringReader("  3 +    5  *   4 -  2 "));
        assertEquals(21, parser.calculate());

        parser = new Parser(new StringReader("  3600 +    100  *   10 -  100 "));
        assertEquals(4500, parser.calculate());

        parser = new Parser(new StringReader("  2^3^2 "));
        assertEquals(512, parser.calculate());

        parser = new Parser(new StringReader("(((54-4)/5)^2)-50"));
        assertEquals(50, parser.calculate());

        parser = new Parser(new StringReader("(((54-4)/5)^2)-50"));
        assertEquals(50, parser.calculate());

        assertThrows(LexerException.class, () -> new Parser(new StringReader("g")).calculate());
        assertThrows(ParserException.class, () -> new Parser(new StringReader("(5+7))")).calculate());
        assertThrows(ParserException.class, () -> new Parser(new StringReader("((5+7)")).calculate());
        assertThrows(ParserException.class, () -> new Parser(new StringReader("5++7")).calculate());
        assertThrows(ParserException.class, () -> new Parser(new StringReader("5+7+")).calculate());
    }
}
package lexer;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @org.junit.jupiter.api.Test
    void getLexeme() throws LexerException {
        Lexer lexer;
        lexer = new Lexer(new StringReader("3+5"));
        Lexeme l;
        l = lexer.getLexeme();
        assertEquals(new Lexeme("3"), l); //expected, actual
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.PLUS), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme("5"), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.EOF), l);
    }
}
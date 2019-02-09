package lexer;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @org.junit.jupiter.api.Test
    void getLexeme() throws LexerException {
        Lexer lexer;
        Lexeme l;

        lexer = new Lexer(new StringReader("3+5"));
        l = lexer.getLexeme();
        assertEquals(new Lexeme("3"), l); //expected, actual
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.PLUS), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme("5"), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.EOF), l);

        lexer = new Lexer(new StringReader(" )  2343  + ( ++   335    *    "));
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.RBRACE), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme("2343"), l); //expected, actual
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.PLUS), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.LBRACE), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.PLUS), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.PLUS), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme("335"), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.ASTERISK), l);
        l = lexer.getLexeme();
        assertEquals(new Lexeme(LexemeType.EOF), l);

        assertThrows(LexerException.class, () -> new Lexer(new StringReader("g")).getLexeme());
    }
}
import lexer.Lexeme;
import lexer.Lexer;
import lexer.LexerException;

import java.io.Reader;

public class Parser {
    Lexer lexer;
    Lexeme current;
    public Parser(Reader reader) throws LexerException
    {
        lexer = new Lexer(reader);
    }

    public void calculate()
    {

    }

    private int parseExpression()
    {
        int temp = parseTerm();


        return 0;
    }

    private int parseTerm()
    {
        return 0;
    }

    private int parseFactor()
    {
        return 0;
    }

    private int parsePower()
    {
        return 0;
    }

    private int parseAtom()
    {
        return 0;
    }
}

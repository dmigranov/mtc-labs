package lexer;

import parser.ParserException;

public class LexerException extends ParserException {
    public LexerException()
    {
        super();
    }

    public LexerException(String message)
    {
        super(message);
    }
}

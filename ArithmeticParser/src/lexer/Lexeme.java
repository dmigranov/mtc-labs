package lexer;

public class Lexeme {
    private LexemeType type;
    private String text;

    public Lexeme(LexemeType type)
    {
        this.type = type;
    }

    public Lexeme(String text)
    {
        this.type = LexemeType.NUMBER;
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Lexeme))
            return false;
        Lexeme other = (Lexeme)obj;
        if(other.type == type && ((text == null && other.text == null) || text.equals(other.text)))
            return true;
        return false;
    }

    public LexemeType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

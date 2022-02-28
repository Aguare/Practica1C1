package Back;

/**
 *
 * @author Henry
 */
public class Token {

    private int line;
    private int column;
    private String lex;
    private TypeToken type;

    public Token(int line, int column, String lex, TypeToken type) {
        this.line = line;
        this.column = column;
        this.lex = lex;
        this.type = type;
    }

    public TypeToken getType() {
        return type;
    }

    public void setType(TypeToken type) {
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getLex() {
        return lex;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }
    
    
}

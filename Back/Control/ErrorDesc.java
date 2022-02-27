package Back.Control;

/**
 *
 * @author aguare
 */
public class ErrorDesc {

    private String content;
    private int line;
    private int column;
    private String msjInfo;
    private String typeError;

    public ErrorDesc(String content, int line, int column, String msjInfo, String typeError) {
        this.content = content;
        this.line = line;
        this.column = column;
        this.msjInfo = msjInfo;
        this.typeError = typeError;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getMsjInfo() {
        return msjInfo;
    }

    public void setMsjInfo(String msjInfo) {
        this.msjInfo = msjInfo;
    }

    public String getTypeError() {
        return typeError;
    }

    public void setTypeError(String typeError) {
        this.typeError = typeError;
    }
}

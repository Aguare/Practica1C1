package Back.Control;

/**
 *
 * @author aguare
 */
public class Operation {

    private String type_operation;
    private int line;
    private int column;
    private String operation;

    public Operation(String type_operation, int line, int column, String operation) {
        this.type_operation = type_operation;
        this.line = line;
        this.column = column;
        this.operation = operation;
    }

    public String getType_operation() {
        return type_operation;
    }

    public void setType_operation(String type_operation) {
        this.type_operation = type_operation;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "" + type_operation + " " + line + " " + column + " " + operation;
    }

}

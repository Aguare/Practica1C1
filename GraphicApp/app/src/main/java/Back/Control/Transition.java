package Back.Control;

import Back.sym;
import java.util.ArrayList;
import java.util.Stack;
import java_cup.runtime.Symbol;

/**
 *
 * @author aguare
 */
public class Transition {

    private final int[] execute_graphics = {sym.RUN, sym.O_PARENT, sym.STRING, sym.C_PARENT, sym.SEMICOLON};
    private final int[] title_estructure = {sym.ID_GRAPHIC, sym.COLONS, sym.STRING, sym.SEMICOLON};
    private final int[] axisx_estructure = {sym.AXIS_X, sym.COLONS, sym.O_SBRACKET, sym.STRING, sym.COMMA,
        sym.STRING, sym.C_SBRACKET, sym.SEMICOLON};
    private final int[] axisy_estructure = {sym.AXIS_Y, sym.COLONS, sym.O_SBRACKET, sym.DECIMAL, sym.COMMA,
        sym.DECIMAL, sym.C_SBRACKET, sym.SEMICOLON};
    private final int[] join_estructure = {sym.JOIN, sym.COLONS, sym.O_SBRACKET, sym.O_BRACE, sym.INTEGER,
        sym.COMMA, sym.INTEGER, sym.C_BRACE, sym.COMMA, sym.O_BRACE, sym.INTEGER, sym.COMMA, sym.INTEGER,
        sym.C_BRACE, sym.C_SBRACKET, sym.SEMICOLON};
    private final int[] type_estructure = {sym.TYPE, sym.COLONS, sym.TYPE_ATTRIBUTE, sym.SEMICOLON};
    private final int[] tags_estructure = {sym.TAGS, sym.COLONS, sym.O_SBRACKET, sym.STRING, sym.COMMA,
        sym.STRING, sym.C_SBRACKET, sym.SEMICOLON};
    private final int[] values_estructure = {sym.VALUES, sym.COLONS, sym.O_SBRACKET, sym.INTEGER, sym.COMMA,
        sym.INTEGER, sym.C_SBRACKET, sym.SEMICOLON};
    private final int[] total_estructure = {sym.TOTAL, sym.COLONS, sym.INTEGER, sym.SEMICOLON};
    private final int[] extra_estructure = {sym.EXTRA, sym.COLONS, sym.STRING, sym.SEMICOLON};

    public Transition() {
    }

    public String analizeError(Stack symbols) {
        for (Object s : symbols) {
            if (s instanceof Symbol) {
                if (((Symbol) s).value != null) {
                    switch (((Symbol) s).sym) {
                        case sym.RUN:
                            return analizeTrans(execute_graphics, symbols);
                        case sym.ID_GRAPHIC:
                            return analizeTrans(title_estructure, symbols);
                        case sym.AXIS_X:
                            return analizeTrans(axisx_estructure, symbols);
                        case sym.AXIS_Y:
                            return analizeTrans(axisy_estructure, symbols);
                        case sym.JOIN:
                            return analizeTrans(join_estructure, symbols);
                        case sym.TYPE:
                            return analizeTrans(type_estructure, symbols);
                        case sym.TAGS:
                            return analizeTrans(tags_estructure, symbols);
                        case sym.VALUES:
                            return analizeTrans(values_estructure, symbols);
                        case sym.TOTAL:
                            return analizeTrans(total_estructure, symbols);
                        case sym.EXTRA:
                            return analizeTrans(extra_estructure, symbols);
                    }
                }
            }
        }
        return analizeTrans(null, symbols);
    }

    private String analizeTrans(int[] transition, Stack received) {
        ArrayList<Integer> result = getListFree(received);
        /*System.out.println("--------------------------------------");
        for (Object o : received) {
            if (o instanceof Symbol) {
                if (((Symbol) o).value != null) {
                    System.out.println("L:" + ((Symbol) o).left + "\t C:" + ((Symbol) o).right + "\t V:" + ((Symbol) o).value + "\t T:" + sym.terminalNames[((Symbol) o).sym]);
                }
            }
        }
        System.out.println("--------------------------------------");*/
        if (transition != null) {
            for (int j = 0; j < result.size(); j++) {
                if (result.get(j) == transition[0]) {
                    int n = result.size() - (j);
                    if (n < transition.length) {
                        return "Se esperaba -> " + sym.terminalNames[transition[n]];
                    }
                }
            }
        } else {
            if (result.size() - 1 >= 0) {
                switch (result.get(result.size() - 1)) {
                    case sym.START_GRAPHIC:
                        return "Se esperaba un tipo de gráfico";
                    case sym.TYPE_BARS:
                        return "Se esperaba llave de apertura";
                    case sym.TYPE_PIE:
                        return "Se esperaba llave de apertura";
                    case sym.O_BRACE:
                        return "Se esperaba llave de cierre";
                    case sym.EOF:
                        return "Instrucciones incompletas o se esperaba -> }";
                }
            }
        }
        return "No se esperaba el símbolo";
    }

    private ArrayList<Integer> getListFree(Stack received) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Object o : received) {
            if (o instanceof Symbol) {
                if (((Symbol) o).value != null) {
                    result.add(((Symbol) o).sym);
                }
            }
        }
        return result;
    }

}

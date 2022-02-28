package com.aguare.appgraphic.Back.Control;

import com.aguare.appgraphic.Back.Graphics.GBars;
import com.aguare.appgraphic.Back.Graphics.GPie;
import com.aguare.appgraphic.Back.sym;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author aguare
 */
public class CreateGraphics implements Serializable {

    //dec -> declaration
    // 0 BAR | 1 PIE
    public int type_graphic = 0;
    //Declaration required
    public ArrayList<String> title_dec = new ArrayList<>();

    //Declaration for BARS
    public ArrayList<String> axisx_dec = new ArrayList<>();
    public ArrayList<Double> axisy_dec = new ArrayList<>();

    //Declaration for PIE
    public ArrayList<String> type_dec = new ArrayList<>();
    public ArrayList<String> tags_dec = new ArrayList<>();
    public ArrayList<Double> value_dec = new ArrayList<>();
    public ArrayList<Double> total_dec = new ArrayList<>();

    //Declaration optional for PIE
    public ArrayList<String> extra_dec = new ArrayList<>();

    //Declaration optional for all
    public ArrayList<Integer> join_dec = new ArrayList<>();

    //Graphic created
    private ArrayList<GBars> allGBar = new ArrayList<>();
    private ArrayList<GPie> allGPie = new ArrayList<>();

    //Graphics to run
    private ArrayList<String> runGraphics = new ArrayList<>();

    //Arithmetic operations
    private ArrayList<Operation> operations = new ArrayList<>();

    public boolean saveRunGraphic(String name) {
        if (graphicAlreadyExists(name)) {
            runGraphics.add(name);
            return true;
        }
        return false;
    }

    private boolean graphicAlreadyExists(String title) {
        for (GBars b : allGBar) {
            if (b.getTitle().equals(title)) {
                return true;
            }
        }
        for (GPie p : allGPie) {
            if (p.getTitle().equals(title)) {
                return true;
            }
        }
        for (Operation o : operations) {
            System.out.println(o.toString());
        }
        return false;
    }

    public void showGraphicsSaved() {
        for (GBars gBars : allGBar) {
            System.out.println(gBars.toString());
        }
        for (GPie s : allGPie) {
            System.out.println(s.toString());
        }
    }

    public Object recordOperation(Double num, Double num2, int line, int column, int operation) {
        switch (operation) {
            case sym.MULTIPLY:
                operations.add(new Operation("MULTIPLICACIÓN", line, column, "" + num + "*" + num2));
                return null;
            case sym.DIVIDE:
                operations.add(new Operation("DIVISIÓN", line, column, "" + num + "/" + num2));
                return null;
            case sym.SUMA:
                operations.add(new Operation("SUMA", line, column, "" + num + "+" + num2));
                return null;
            case sym.RESTA:
                operations.add(new Operation("RESTA", line, column, "" + num + "-" + num2));
                return null;
            default:
                return new ErrorDesc("Error en la operación", line, column, "La operación no se registró", "");
        }
    }

    /*
        Clear list declaration
     */
    private void emptyDeclaration() {
        title_dec.clear();
        axisx_dec.clear();
        axisy_dec.clear();
        type_dec.clear();
        extra_dec.clear();
        total_dec.clear();
        value_dec.clear();
        tags_dec.clear();
        join_dec.clear();
    }

    public Object verifyCountDeclaration(int line, int column) {
        try {
            if (graphicAlreadyExists(title_dec.get(0))) {
                return new ErrorDesc("Ya existe", line, column, "El nombre de la gráfica ya está en uso", "Sintactico");
            } else {
                if (type_graphic == 0) {
                    return createGBar();
                } else {
                    return createGPie();
                }
            }
        } catch (Exception e) {
            return new ErrorDesc("Incompleto", line, column, "Falta información de las gráficas", "Sintáctico");
        }
    }

    private Object createGBar() throws Exception {
        Object error = verifyDeclaration(title_dec.size(), "titulo", 1);
        if (error == null) {
            error = verifyMinimum(axisx_dec.size(), "ejex", 2);
            if (error == null) {
                error = verifyMinimum(axisy_dec.size(), "ejey", axisx_dec.size());
                if (error == null) {
                    if (join_dec.size() <= axisx_dec.size() * 2) {
                        String t = title_dec.get(0);
                        Object x = axisx_dec.clone();
                        Object y = axisy_dec.clone();
                        Object j = join_dec.clone();
                        allGBar.add(new GBars((ArrayList<String>) x, (ArrayList<Double>) y, t, (ArrayList<Integer>) j));
                    } else {
                        emptyDeclaration();
                        return new ErrorDesc("Multiple Declaración de unir", 0, 0, "Sólo puede declararse una vez el atributo", "Sintáctico");
                    }
                    emptyDeclaration();
                    return true;
                }
            }
        }
        emptyDeclaration();
        return error;
    }

    private Object createGPie() throws Exception {
        Object error = verifyDeclaration(title_dec.size(), "titulo", 1);
        if (error == null) {
            error = verifyDeclaration(type_dec.size(), "tipo", 1);
            if (error == null) {
                error = verifyMinimum(tags_dec.size(), "etiquetas", 2);
                if (error == null) {
                    error = verifyMinimum(value_dec.size(), "valores", tags_dec.size());
                    if (error == null) {
                        if (total_dec.size() <= 1) {
                            if (extra_dec.size() <= 1) {
                                if (join_dec.size() <= tags_dec.size() * 2) {
                                    String t = type_dec.get(0);
                                    Object tags = tags_dec.clone();
                                    Object v = value_dec.clone();
                                    Double to = 360.0;
                                    if (total_dec.size() == 1) {
                                        to = total_dec.get(0);
                                    }
                                    String ex = extra_dec.get(0);
                                    String ti = title_dec.get(0);
                                    Object j = join_dec.clone();
                                    allGPie.add(new GPie(t, (ArrayList<String>) tags, (ArrayList<Double>) v,
                                            to, ex, ti, (ArrayList<Integer>) j));
                                    emptyDeclaration();
                                    return true;
                                } else {
                                    emptyDeclaration();
                                    return new ErrorDesc("Multiple Declaración de unir", 0, 0, "Sólo puede declararse una vez el atributo", "Sintáctico");
                                }
                            } else {
                                emptyDeclaration();
                                return new ErrorDesc("Multiple Declaración de extra", 0, 0, "Sólo puede declararse una vez el atributo", "Sintáctico");
                            }
                        }
                    }
                }
            }
        }
        return error;
    }

    private Object verifyMinimum(int size, String name, int minimum) {
        if (size >= minimum) {
            return null;
        } else {
            return new ErrorDesc("Falta o no hay Declaración de " + name, 0, 0, "El atributo es obligatorio", "Sintáctico");
        }
    }

    private Object verifyDeclaration(int size, String name, int required) {
        if (size == required) {
            return null;
        }
        switch (size) {
            case 0:
                return new ErrorDesc("Ninguna Declaración de " + name, 0, 0, "El atributo es obligatorio", "Sintáctico");
            default:
                return new ErrorDesc("Multiple Declaración de " + name, 0, 0, "Sólo puede declararse una vez el atributo", "Sintáctico");
        }
    }

    public ArrayList<GBars> getAllGBar() {
        return allGBar;
    }

    public ArrayList<String> getRunGraphics() {
        return runGraphics;
    }

    public void setAllGBar(ArrayList<GBars> allGBar) {
        this.allGBar = allGBar;
    }

    public ArrayList<GPie> getAllGPie() {
        return allGPie;
    }

    public ArrayList<Operation> getOperations(){
        return operations;
    }

    public void setAllGPie(ArrayList<GPie> allGPie) {
        this.allGPie = allGPie;
    }
}

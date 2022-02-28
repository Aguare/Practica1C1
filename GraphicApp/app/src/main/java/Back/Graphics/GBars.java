package Back.Graphics;

import java.util.ArrayList;

/**
 *
 * @author aguare
 */
public class GBars extends GGeneral {

    private ArrayList<String> axis_x;
    private ArrayList<Double> axis_y;

    public GBars(ArrayList<String> axis_x, ArrayList<Double> axis_y, String title, ArrayList<Integer> join) {
        super(title, join);
        this.axis_x = axis_x;
        this.axis_y = axis_y;
    }

    public ArrayList<String> getAxis_x() {
        return axis_x;
    }

    public void setAxis_x(ArrayList<String> axis_x) {
        this.axis_x = axis_x;
    }

    public ArrayList<Double> getAxis_y() {
        return axis_y;
    }

    public void setAxis_y(ArrayList<Double> axis_y) {
        this.axis_y = axis_y;
    }

    @Override
    public String toString() {
        return "BARRAS -> Título: " + this.getTitle() + " Ejex: " + axis_x.get(0) + "," + axis_x.get(1) + " Ejey: " + axis_y.get(0) + "," + axis_y.get(1);
    }
}

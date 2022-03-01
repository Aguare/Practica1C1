package com.aguare.appgraphic.Back.Graphics;


import android.graphics.Point;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author aguare
 */
public class GBars extends GGeneral implements Serializable {

    private ArrayList<String> axis_x;
    private ArrayList<Double> axis_y;

    public GBars(ArrayList<String> axis_x, ArrayList<Double> axis_y, String title, ArrayList<Integer> join) {
        super(title, join);
        this.axis_x = axis_x;
        this.axis_y = axis_y;
        sortJoin();
    }

    public void sortJoin() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Integer> join = getJoin();
        if (getJoin().size() >= 2) {
            try {
                for (int i = 0; i < join.size() - 1; i += 2) {
                    int inX = join.get(i);
                    int inY = join.get(i + 1);
                    x.add(axis_x.get(inX));
                    y.add(axis_y.get(inY));
                    points.add(new Point(inX, inY));
                }
                for (int i = 0; i < axis_x.size(); i++) {
                    if (!existsIndex(i, points, 1)) {
                        x.add(axis_x.get(i));
                    }
                }
                for (int i = 0; i < axis_y.size(); i++) {
                    if (!existsIndex(i, points, 0)) {
                        y.add(axis_y.get(i));
                    }
                }
                axis_x.clear();
                axis_y.clear();
                axis_x = x;
                axis_y = y;
            } catch (Exception ex) {
            }
        }
    }

    private boolean existsIndex(int index, ArrayList<Point> points, int op) {
        for (Point p : points) {
            if (op == 1) {
                if (p.x == index) {
                    return true;
                }
            } else {
                if (p.y == index) {
                    return true;
                }
            }
        }
        return false;
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

    public GBars clone() {
        return new GBars((ArrayList<String>) axis_x.clone(), (ArrayList<Double>) axis_y.clone(), getTitle(), (ArrayList<Integer>) getJoin().clone());
    }

    @Override
    public String toString() {
        return "BARRAS -> Título: " + this.getTitle() + " Ejex: " + axis_x.get(0) + "," + axis_x.get(1) + " Ejey: " + axis_y.get(0) + "," + axis_y.get(1);
    }
}

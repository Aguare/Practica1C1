package com.aguare.appgraphic.Back.Graphics;

import android.graphics.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author aguare
 */
public class GPie extends GGeneral implements Serializable {

    private String type;
    private ArrayList<String> tags;
    private ArrayList<Double> values;
    private Double total;
    private String extra;

    public GPie(String type, ArrayList<String> tags, ArrayList<Double> values, Double total, String extra, String title, ArrayList<Integer> join) {
        super(title, join);
        this.type = type;
        this.tags = tags;
        this.values = values;
        this.total = total;
        this.extra = extra;
        Collections.reverse(tags);
        Collections.reverse(values);
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
                    x.add(tags.get(inX));
                    y.add(values.get(inY));
                    points.add(new Point(inX, inY));
                }
                for (int i = 0; i < tags.size(); i++) {
                    if (!existsIndex(i, points, 1)) {
                        x.add(tags.get(i));
                    }
                }
                for (int i = 0; i < values.size(); i++) {
                    if (!existsIndex(i, points, 0)) {
                        y.add(values.get(i));
                    }
                }
                tags.clear();
                values.clear();
                tags = x;
                values = y;
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

    public Double totalValues() {
        Double sum = 0.0;
        for (Double v : values) {
            sum += v;
        }
        return sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public GPie clone() {
        return new GPie(type, (ArrayList<String>) tags.clone(), (ArrayList<Double>) values.clone(), total, extra, getTitle(), (ArrayList<Integer>) getJoin().clone());
    }

    @Override
    public String toString() {
        return "PIE -> Título: " + this.getTitle() + " Tipo: " + type + " Etiquetas: " + tags.get(0) + "," + tags.get(1);
    }

}

package Back.Graphics;

import java.util.ArrayList;

/**
 *
 * @author aguare
 */
public class GPie extends GGeneral {

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

    @Override
    public String toString() {
        return "PIE -> Título: " + this.getTitle() + " Tipo: " + type + " Etiquetas: " + tags.get(0) + "," + tags.get(1);
    }

}

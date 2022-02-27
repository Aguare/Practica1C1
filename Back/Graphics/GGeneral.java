package Back.Graphics;

import java.util.ArrayList;

/**
 *
 * @author aguare
 */
public class GGeneral {

    private String title;
    private ArrayList<Integer> join;

    public GGeneral(String title, ArrayList<Integer> join) {
        this.title = title;
        this.join = join;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Integer> getJoin() {
        return join;
    }

    public void setJoin(ArrayList<Integer> join) {
        this.join = join;
    }

}

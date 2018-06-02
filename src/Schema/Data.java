package Schema;

import java.util.ArrayList;

public class Data {


    /**
     * Attributes
     */
    public static int obj_count = 0;

    private int id;
    private int nb_c;
    private ArrayList<String> criteres;

    /**
     * Constructor
     */
    public Data(String ...strings){
        this.id = obj_count++;
        this.nb_c = strings.length;

        this.criteres = new ArrayList<>();
        for (int i = 0; i < this.nb_c; i++) {
            criteres.add(strings[i]);
        }
    }

    /**
     * Methodes
     */
    // Getter & Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNb_c() {
        return nb_c;
    }
    public void setNb_c(int nb_c) {
        this.nb_c = nb_c;
    }
    public ArrayList<String> getCriteres() {
        return criteres;
    }
    public void setCriteres(ArrayList<String> criteres) {
        this.criteres = criteres;
    }
    public String getC(int c_index){
        return this.criteres.get(c_index);
    }

    // Obj Description
    @Override
    public String toString() {
        String desc = "id:" + this.id;
        for (int i = 0; i < this.nb_c; i++) {
            desc+= " | " + this.criteres.get(i);
        }
        return desc;
    }

}

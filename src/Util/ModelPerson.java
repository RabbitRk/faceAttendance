package Util;

public class ModelPerson {

    private int id;
    private String first_name;
    private int present;
    private String dateon;

    public ModelPerson() {       
    }
      
    public ModelPerson(int id, String first_name) {
        this.id = id;
        this.first_name = first_name;
    }
    
    public ModelPerson(int id, int present, String dateon ) {
        this.id = id;
        this.present = present;
        this.dateon = dateon;
    }
    
    ////////////////////////////////

    public int getPresent() {
        return present;
    }

    public String getDateon() {
        return dateon;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public void setDateon(String dateon) {
        this.dateon = dateon;
    }
 
    ///////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}

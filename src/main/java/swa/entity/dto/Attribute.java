package swa.entity.dto;

public class Attribute {
    String name;
    String catagory;

    public Attribute() {
    }

    public Attribute(String name) {
        this.name = name;
        this.catagory = null;
    }

    public Attribute(String name, String catagory) {
        this.name = name;
        this.catagory = catagory;
    }
}

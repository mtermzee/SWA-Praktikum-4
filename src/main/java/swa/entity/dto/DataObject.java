package swa.entity.dto;

public abstract class DataObject {
    int id;
    String name;
    String type;
    Attribute attributes;
    Relationship relationships;
    Link links;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Attribute getAttributes() {
        return attributes;
    }

    public Relationship getRelationships() {
        return relationships;
    }

    public Link getLinks() {
        return links;
    }

    public DataObject() {
    }

    @Override
    public String toString() {
        return "DataObject [id=" + id + ", type=" + type + ", attributes=" + attributes + ", relationships="
                + relationships + ", links=" + links + "]";
    }

}

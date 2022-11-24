package swa.entity.dto;

public abstract class DataObject {
    int id;
    String type;
    Attribute attributes;
    Relationship relationships;
    Link links;
}

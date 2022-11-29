package swa.entity.dto;

import swa.entity.Person;

public class PersonDTO extends DataObject {
    private final String URL = "http://localhost:8080/Persons/";
    private final String RelURL = "http://localhost:8080/Teams/";

    public PersonDTO() {
    }

    public PersonDTO(Person other) {
        this.id = other.getId();
        this.type = other.getType();
        this.attributes = new Attribute(other.getName());
        this.links = new Link();
        this.links.setSelf(URL + other.getId());
    }

    public PersonDTO(Person other, boolean isForRelationship) {
        if (isForRelationship) {
            this.id = other.getId();
            this.name = other.getName();
            this.type = other.getType();
            this.links = new Link();
            this.links.setSelf(RelURL + other.getId() + "/relationships/" + other.getType());
            this.links.setRelated(URL + other.getId());
        } else
            new PersonDTO(other);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

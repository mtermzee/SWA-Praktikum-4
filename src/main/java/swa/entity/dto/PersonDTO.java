package swa.entity.dto;

import swa.entity.Person;

public class PersonDTO extends DataObject {

    public PersonDTO() {
    }

    public PersonDTO(Person other) {
        this.id = other.getId();
        this.type = other.getType();
        this.attributes = new Attribute(other.getName());
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

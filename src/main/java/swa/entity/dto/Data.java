package swa.entity.dto;

import java.util.ArrayList;
import java.util.List;

import swa.entity.Person;

public class Data {
    List<DataObject> data;
    List<DataObject> included;

    public Data() {
    }

    public Data(DataObject data) {
        this.data = new ArrayList<>();
        this.data.add(data);
    }

    public Data(List<DataObject> data) {
        this.data = data;
    }

    public List<DataObject> getData() {
        return data;
    }

    public List<DataObject> getIncluded() {
        return included;
    }

    public void setIncluded(List<DataObject> included) {
        this.included = included;
    }

    public void addIncludeElement(Person person) {
        if (this.included == null)
            this.included = new ArrayList<>();

        if (person != null) {
            PersonDTO pDto = new PersonDTO(person);
            included.add(pDto);
            System.out.println("added: " + pDto);
        }
    }

    public void addIncludeElements(List<Person> persons) {
        if (persons != null && !persons.isEmpty()) {
            for (Person person : persons) {
                addIncludeElement(person);
                System.out.println("added: " + person);
            }
        }
    }

}

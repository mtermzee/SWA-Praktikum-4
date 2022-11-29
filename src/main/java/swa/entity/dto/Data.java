package swa.entity.dto;

import java.util.ArrayList;
import java.util.List;

import swa.entity.Person;

public class Data {
    List<DataObject> data;
    List<DataObject> included;
    Link links;

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
        }
    }

    public Link getLinks() {
        return links;
    }

    public void setPageLinks(int pageNumber, int pageSize, int listSize, boolean isForTeams)
    {
        String URL;

        if (!isForTeams)
            URL = "http://localhost:8080/Persons";
        else
            URL = "http://localhost:8080/Teams";

        links = new Link();

        int pagesAmount = listSize / pageSize;

        if (listSize % pageSize > 0)
            pagesAmount++;

        links.setFirst(URL + "?page%5Bnumber%5D=" + 1 + "&page%5Bsize%5D=" + pageSize);
        
        if (pageNumber > 1)
            links.setPrev(URL + "?page%5Bnumber%5D=" + (pageNumber - 1) + "&page%5Bsize%5D=" + pageSize);
        if (pageNumber < pagesAmount)
            links.setNext(URL + "?page%5Bnumber%5D=" + (pageNumber + 1) + "&page%5Bsize%5D=" + pageSize);

        links.setLast(URL + "?page%5Bnumber%5D=" + pagesAmount + "&page%5Bsize%5D=" + pageSize);
    }

    public void setLinks(Link links) {
        this.links = links;
    }

    public void addIncludeElements(List<Person> persons) {
        if (persons != null && !persons.isEmpty()) {
            for (Person person : persons) {
                addIncludeElement(person);
            }
        }
    }

}

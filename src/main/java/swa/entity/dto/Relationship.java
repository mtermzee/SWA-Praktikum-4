package swa.entity.dto;

import java.util.ArrayList;
import java.util.List;

import swa.entity.Person;

public class Relationship {
    DataObject manager;
    List<DataObject> players;

    public Relationship() {
        this.players = new ArrayList<>();
    }

    public Relationship(Person manager, List<Person> players, int teamId) {
        this.players = new ArrayList<>();

        if (manager != null) {
            PersonDTO personDTO = new PersonDTO(manager, true);
            personDTO.setLinksForRelationship(teamId);
            this.manager = personDTO;
        }
        if (players != null) {
            for (Person person : players) {
                PersonDTO personDTO = new PersonDTO(person, true);
                personDTO.setLinksForRelationship(teamId);
                this.players.add(personDTO);
            }
        }
    }

    public DataObject getManager() {
        return manager;
    }

    public List<DataObject> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Relationship [manager=" + manager + ", players=" + players + "]";
    }

}

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

    public Relationship(Person manager, List<Person> players) {
        this.players = new ArrayList<>();

        if (manager != null) {
            this.manager = new PersonDTO(manager, true);
        }
        if (players != null) {
            for (Person person : players) {
                this.players.add(new PersonDTO(person, true));
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

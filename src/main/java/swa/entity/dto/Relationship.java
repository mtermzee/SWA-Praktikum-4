package swa.entity.dto;

import java.util.List;

import swa.entity.Person;

public class Relationship {
    Person manager;
    List<Person> players;

    public Relationship() {
    }

    public Person getManager() {
        return manager;
    }

    public List<Person> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Relationship [manager=" + manager + ", players=" + players + "]";
    }

}

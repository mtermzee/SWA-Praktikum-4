package swa.entity;

import java.util.ArrayList;
import java.util.List;

public class Team {
    int id;
    String name;
    String catagory;

    Person manager = new Person();
    List<Person> players = new ArrayList<>();

    public Team() {
    }

    public Team(int id, String name, String catagory) {
        this.id = id;
        this.name = name;
        this.catagory = catagory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public List<Person> getPlayers() {
        return players;
    }

    public void setPlayers(List<Person> players) {
        this.players = players;
    }

    public void addPlayer(Person player) {
        players.add(player);
    }

}

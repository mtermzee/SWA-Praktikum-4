package swa.entity;

import java.util.ArrayList;
import java.util.List;

public class Team {
    int id;
    String name;
    String catagory;

    Person manager;
    List<Person> players;

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

    public boolean addPlayer(Person player) {
        if (players == null) {
            players = new ArrayList<>();
        }
        for (Person person : players) {
            if (person.getId() == player.getId()) {
                return false;
            }
        }
        players.add(player);
        return true;
    }

    public boolean removePlayer(int id) {
        if (players == null) {
            return false;
        }
        for (Person person : players) {
            if (person.getId() == id) {
                players.remove(person);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + name + ", catagory=" + catagory + ", manager=" + manager + ", players="
                + players + "]";
    }

}

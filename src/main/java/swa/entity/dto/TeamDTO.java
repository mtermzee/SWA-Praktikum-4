package swa.entity.dto;

import swa.entity.Team;

public class TeamDTO extends DataObject {
    private final String URL = "http://localhost:8080/Teams/";

    public TeamDTO() {
    }

    public TeamDTO(Team other) {
        this.id = other.getId();
        this.type = "Team";
        this.attributes = new Attribute(other.getName(), other.getCatagory());
        if (other.getManager() != null || other.getPlayers() != null) {
            this.relationships = new Relationship(other.getManager(), other.getPlayers());
        }
        this.links = new Link();
        this.links.setSelf(URL + other.getId());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

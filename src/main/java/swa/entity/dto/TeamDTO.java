package swa.entity.dto;

import swa.entity.Team;

public class TeamDTO extends DataObject {

    public TeamDTO() {
    }

    public TeamDTO(Team other) {
        this.id = other.getId();
        this.type = "Team";
        this.attributes = new Attribute(other.getName(), other.getCatagory());
        if (other.getManager() != null || other.getPlayers() != null) {
            this.relationships = new Relationship(other.getManager(), other.getPlayers());
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

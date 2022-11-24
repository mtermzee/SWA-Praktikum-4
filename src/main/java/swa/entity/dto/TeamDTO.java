package swa.entity.dto;

import swa.entity.Team;
import swa.entity.Type;

public class TeamDTO extends DataObject {

    public TeamDTO() {
    }

    public TeamDTO(Team other) {
        this.id = other.getId();
        this.type = Type.TEAM.name();
        this.attributes = new Attribute(other.getName(), other.getCatagory());
    }
}

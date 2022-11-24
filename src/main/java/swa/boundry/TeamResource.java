package swa.boundry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import swa.control.team.TeamService;

@Path("/Teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamService teamService;

    @PostConstruct
    public void init() {
        teamService.createTeam("Real Madrid", "Football");
        teamService.createTeam("Barcelona", "Football");
        teamService.createTeam("Bayern Munich", "Football");
    }
}

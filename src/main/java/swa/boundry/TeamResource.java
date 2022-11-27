package swa.boundry;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import swa.control.team.TeamService;
import swa.entity.Team;

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

    @GET
    public Response getTeams() {
        List<Team> teams = this.teamService.getAllTeams();
        if (teams.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(teams).build();
    }

    @GET
    @Path("{id}")
    public Response getTeamById(@PathParam("id") int id) {
        Team team = this.teamService.getTeam(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(team).build();
    }

    @POST
    @Path("{team}")
    public Response createTeam(@PathParam("team") String name, @QueryParam("category") String category) {
        this.teamService.createTeam(name, category);
        return Response.ok().build();
    }

    @POST
    @Path("{teamID}/{personID}")
    public Response addTeamMember(@PathParam("teamID") int teamID, @PathParam("personID") int personID) {
        boolean result = this.teamService.addMemberToTeam(teamID, personID);
        if (result)
            return Response.ok().entity("Person has been added to Team successfully").type("text/plain").build();

        return Response.status(Response.Status.NOT_FOUND).entity("Person or Team not found").type("text/plain").build();
    }

    @PUT
    @Path("{id}")
    public Response updateTeam(@PathParam("id") int id, @QueryParam("name") String name,
            @QueryParam("category") String category) {
        this.teamService.updateTeam(id, name, category);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeam(@PathParam("id") int id) {
        this.teamService.deleteTeam(id);
        return Response.ok().build();
    }
}

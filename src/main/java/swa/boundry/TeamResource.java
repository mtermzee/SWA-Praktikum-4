package swa.boundry;

import java.util.ArrayList;
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
import swa.entity.Person;
import swa.entity.Team;
import swa.entity.dto.Data;
import swa.entity.dto.DataObject;
import swa.entity.dto.PersonDTO;
import swa.entity.dto.TeamDTO;

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
    public Response getTeams(@QueryParam("filter[name]") String name,
            @QueryParam("category[category]") String category, @QueryParam("included[included]") String included,
            @QueryParam("page[number]") Integer number, @QueryParam("page[size]") Integer size) {
        List<Team> teams = this.teamService.getAllTeams(name, category);
        if (teams.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found!").type("text/plain").build();
        }

        Data data;

        if (number != null && size != null)
        {
            int teamsAmount = teams.size();
            teams = getTeamsForPage(teams, number, size);
            data = showUnderData(convertToDTO(teams));
            if (!teams.isEmpty())
                data.setPageLinks(number, size, teamsAmount, true);
        }
        else
        {
            data = showUnderData(convertToDTO(teams));
        }

        if (included != null) {
            addIncluded(data, included, teams);
        }

        return Response.ok(data).build();

    }

    @GET
    @Path("{id}")
    public Response getTeamById(@PathParam("id") int id) {
        Team team = this.teamService.getTeam(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found!").type("text/plain").build();
        }
        Data data = showUnderData(convertToDTO(team));
        return Response.ok(data).build();
    }

    @GET
    @Path("{teamID}/relationships/{type}")
    public Response getRelationships(@PathParam("teamID") int teamID, @PathParam("type") String type) {
        List<Person> persons = this.teamService.getMemberFromTeam(teamID, type);
        if (persons == null || persons.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found!").type("text/plain").build();
        }

        Data data = showUnderDataWithPersonDTO(convertToPersonDTO(persons), teamID);
        return Response.ok(data).build();
    }

    @POST
    @Path("{team}")
    public Response createTeam(@PathParam("team") String name, @QueryParam("category") String category) {
        boolean result = this.teamService.createTeam(name, category);
        if (result)
            return Response.ok().entity("Team has been added to Team successfully").type("text/plain").build();

        return Response.status(Response.Status.NOT_FOUND).entity("Team has been not added").type("text/plain").build();
    }

    @POST
    @Path("{teamID}/relationships/{personID}")
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
        boolean result = this.teamService.updateTeam(id, name, category);
        if (result)
            return Response.ok().entity("Team has been updated successfully").type("text/plain").build();

        return Response.status(Response.Status.NOT_FOUND).entity("Team has been not updated").type("text/plain")
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeam(@PathParam("id") int id) {
        boolean result = this.teamService.deleteTeam(id);
        if (result)
            return Response.ok().entity("Team has been removed successfully").type("text/plain").build();

        return Response.status(Response.Status.NOT_FOUND).entity("Team has been not removed").type("text/plain")
                .build();
    }

    @DELETE
    @Path("{teamID}/relationships/{playerID}")
    public Response deleteTeamMember(@PathParam("teamID") int teamID, @PathParam("playerID") int playerID) {
        boolean result = this.teamService.removeMemberFromTeam(teamID, playerID);
        if (result)
            return Response.ok().entity("Person has been removed from Team successfully").type("text/plain").build();

        return Response.status(Response.Status.NOT_FOUND).entity("Person or Team not found").type("text/plain").build();
    }

    public TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO(team);
        return teamDTO;
    }

    public List<TeamDTO> convertToDTO(List<Team> teams) {
        List<TeamDTO> teamDTOs = new ArrayList<>();
        for (Team team : teams) {
            teamDTOs.add(convertToDTO(team));
        }
        return teamDTOs;
    }

    public List<PersonDTO> convertToPersonDTO(List<Person> persons) {
        List<PersonDTO> personDtos = new ArrayList<>();
        for (Person person : persons) {
            personDtos.add(new PersonDTO(person));
        }
        return personDtos;
    }

    public Data showUnderData(List<TeamDTO> teamDTOs) {
        List<DataObject> dataObjects = new ArrayList<>();
        for (TeamDTO teamDTO : teamDTOs) {
            dataObjects.add(teamDTO);
        }
        Data data = new Data(dataObjects);
        return data;
    }

    public Data showUnderDataWithPersonDTO(List<PersonDTO> personDTOs, int teamId) {
        List<DataObject> dataObjects = new ArrayList<>();
        for (PersonDTO person : personDTOs) {
            person.setLinksForRelationship(teamId);
            dataObjects.add(person);
        }
        Data data = new Data(dataObjects);
        return data;
    }

    public Data showUnderData(TeamDTO teamDTO) {
        Data data = new Data(teamDTO);
        return data;
    }

    public Data addIncluded(Data data, String member, List<Team> teams) {
        for (Team team : teams) {
            if (member.contains("manager")) {
                data.addIncludeElement(team.getManager());
            }
            if (member.contains("player")) {
                data.addIncludeElements(team.getPlayers());
            }
        }
        return data;
    }

    public List<Team> getTeamsForPage(List<Team> originalList, int pageNumber, int pageSize)
    {
        List<Team> resultList = new ArrayList<>();

        for(int i = (pageNumber - 1) * pageSize; i < pageNumber * pageSize; i++)
        {
            if (i < originalList.size()) 
                resultList.add(originalList.get(i));
        }

        return resultList;
    }
}

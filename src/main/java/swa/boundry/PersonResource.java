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

import swa.control.person.PersonService;
import swa.entity.Person;
import swa.entity.Type;

@Path("/Persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService personService;

    @PostConstruct
    public void init() {
        personService.createTeamMember("Ronaldo", Type.PLAYER.name());
        personService.createTeamMember("Messi", Type.PLAYER.name());
        personService.createTeamMember("Neymar", Type.PLAYER.name());
    }

    @GET
    public Response getPersons() {
        List<Person> persons = this.personService.getAllTeamMembers();
        if (persons.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(persons).build();
    }

    @GET
    @Path("{id}")
    public Response getPersonById(@PathParam("id") int id) {
        Person person = this.personService.getTeamMember(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }

    @POST
    @Path("{person}")
    public Response createPerson(@PathParam("person") String name, @QueryParam("type") String type) {
        this.personService.createTeamMember(name, type);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response updatePerson(@PathParam("id") int id, @QueryParam("name") String name,
            @QueryParam("type") String type) {
        Person person = this.personService.getTeamMember(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        this.personService.updateTeamMember(person.getId(), name, type);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int id) {
        Person person = this.personService.getTeamMember(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        this.personService.deleteTeamMember(person.getId());
        return Response.ok().build();
    }
}

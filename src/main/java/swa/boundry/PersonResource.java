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

import swa.control.person.PersonService;
import swa.entity.Person;
import swa.entity.dto.Data;
import swa.entity.dto.DataObject;
import swa.entity.dto.PersonDTO;

@Path("/Persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService personService;

    @PostConstruct
    public void init() {
        personService.createTeamMember("Ronaldo", "Player");
        personService.createTeamMember("Messi", "Player");
        personService.createTeamMember("Neymar", "Player");
    }

    @GET
    public Response getPersons(@QueryParam("filter[name]") String name,
            @QueryParam("category[type]") String type) {
        List<Person> persons = this.personService.getAllTeamMembers(name, type);
        if (persons.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found!").type("text/plain").build();
        }

        Data data = showUnderData(convertToDTO(persons));
        return Response.ok(data).build();
    }

    @GET
    @Path("{id}")
    public Response getPersonById(@PathParam("id") int id) {
        Person person = this.personService.getTeamMember(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Data data = showUnderData(convertToDTO(person));
        return Response.ok(data).build();
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

    public PersonDTO convertToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO(person);
        return personDTO;
    }

    public List<PersonDTO> convertToDTO(List<Person> persons) {
        List<PersonDTO> personDTOs = new ArrayList<>();
        for (Person person : persons) {
            personDTOs.add(convertToDTO(person));
        }
        return personDTOs;
    }

    public Data showUnderData(List<PersonDTO> personDTOs) {
        List<DataObject> dataObjects = new ArrayList<>();
        for (PersonDTO personDTO : personDTOs) {
            dataObjects.add(personDTO);
        }
        Data data = new Data(dataObjects);
        return data;
    }

    public Data showUnderData(PersonDTO personDTO) {
        Data data = new Data(personDTO);
        return data;
    }
}

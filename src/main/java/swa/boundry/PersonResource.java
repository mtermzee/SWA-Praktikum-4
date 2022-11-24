package swa.boundry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import swa.control.person.PersonService;
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

}

package swa.control.person;

import java.util.List;

import javax.inject.Inject;

import swa.entity.Person;

public class PersonService {
    @Inject
    PersonManagement personManagement;

    public boolean createTeamMember(String name, String Type) {
        return personManagement.createTeamMember(name, Type);
    }

    public boolean deleteTeamMember(int id) {
        return personManagement.deleteTeamMember(id);
    }

    public boolean updateTeamMember(int id, String name, String Type) {
        return personManagement.updateTeamMember(id, name, Type);
    }

    public Person getTeamMember(int id) {
        /*
         * PersonDTO pDto = new PersonDTO(personManagement.getTeamMember(id));
         * return pDto;
         */

        return personManagement.getTeamMember(id);
    }

    public List<Person> getAllTeamMembers() {
        /*
         * List<Person> persons = personManagement.getAllTeamMembers();
         * List<PersonDTO> personDTOs = new ArrayList<>();
         * for (Person p : persons) {
         * personDTOs.add(new PersonDTO(p));
         * }
         * return personDTOs;
         */

        return personManagement.getAllTeamMembers();
    }
}

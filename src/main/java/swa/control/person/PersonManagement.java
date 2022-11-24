package swa.control.person;

import java.util.List;

import swa.entity.Person;

public interface PersonManagement {
    // TeamMember
    public boolean createTeamMember(String name, String Type);

    public boolean deleteTeamMember(int id);

    public boolean updateTeamMember(int id, String name, String Type);

    public Person getTeamMember(int id);

    public List<Person> getAllTeamMembers();
}

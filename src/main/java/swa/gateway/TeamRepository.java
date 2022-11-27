package swa.gateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import swa.control.person.PersonManagement;
import swa.control.team.TeamManagement;
import swa.entity.Person;
import swa.entity.Team;

@ApplicationScoped
public class TeamRepository implements TeamManagement, PersonManagement {
    Map<Integer, Team> teams = new HashMap<>();
    Map<Integer, Person> persons = new HashMap<>();
    int availableTeamID = 0;
    int availablePersonID = 0;

    @Override
    public boolean createTeam(String name, String catagory) {
        if (name == null || catagory == null) {
            return false;
        }
        Team team = new Team(availableTeamID++, name, catagory);
        teams.put(team.getId(), team);
        return true;
    }

    @Override
    public boolean deleteTeam(int id) {
        if (teams.containsKey(id)) {
            teams.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeam(int id, String name, String catagory) {
        if (teams.containsKey(id)) {
            Team team = teams.get(id);
            team.setName(name);
            team.setCatagory(catagory);
            return true;
        }
        return false;
    }

    @Override
    public Team getTeam(int id) {
        if (teams.containsKey(id)) {
            return teams.get(id);
        }
        return null;
    }

    @Override
    public List<Team> getAllTeams(String name, String catagory) {
        List<Team> filteredTeams = List.copyOf(teams.values());
        if (name != null && !name.equals("")) {
            filteredTeams = filterByName(filteredTeams, name);
        }
        if (catagory != null && !catagory.equals("")) {
            filteredTeams = filterByCategory(filteredTeams, catagory);
        }
        return filteredTeams;
    }

    @Override
    public boolean addMemberToTeam(int teamId, int memberId) {
        if (teams.containsKey(teamId) && persons.containsKey(memberId)) {
            Team team = teams.get(teamId);
            Person person = persons.get(memberId);

            if (person.getType().toLowerCase().equals("manager")) {
                team.setManager(person);
                return true;
            }

            return team.addPlayer(person);
        }
        return false;
    }

    @Override
    public boolean removeMemberFromTeam(int teamId, int memberId) {
        if (teams.containsKey(teamId) && persons.containsKey(memberId)) {
            Team team = teams.get(teamId);
            return team.removePlayer(memberId);
        }
        return false;

    }

    @Override
    public List<Team> filterByName(List<Team> teams, String name) {
        List<Team> filteredTeams = new ArrayList<>();
        for (Team team : teams) {
            if (team.getName().toLowerCase().equals(name.toLowerCase())) {
                filteredTeams.add(team);
            }
        }
        return filteredTeams;
    }

    @Override
    public List<Team> filterByCategory(List<Team> teams, String category) {
        List<Team> filteredTeams = new ArrayList<>();
        for (Team team : teams) {
            if (team.getCatagory().toLowerCase().equals(category.toLowerCase())) {
                filteredTeams.add(team);
            }
        }
        return filteredTeams;
    }

    @Override
    public boolean createTeamMember(String name, String Type) {
        System.out.println("createTeamMember");
        if (name == null || Type == null) {
            return false;
        }
        System.out.println("nach if");
        Person person = new Person(availablePersonID++, name, Type);
        persons.put(person.getId(), person);
        return true;
    }

    @Override
    public boolean deleteTeamMember(int id) {
        if (persons.containsKey(id)) {
            persons.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeamMember(int id, String name, String type) {
        if (persons.containsKey(id)) {
            Person person = persons.get(id);
            person.setName(name);
            person.setType(type);
            return true;
        }
        return false;
    }

    @Override
    public Person getTeamMember(int id) {
        if (persons.containsKey(id)) {
            return persons.get(id);
        }
        return null;
    }

    @Override
    public List<Person> getAllTeamMembers(String name, String Type) {
        List<Person> filteredPersons = List.copyOf(persons.values());
        if (name != null && !name.equals("")) {
            filteredPersons = filterByPerson(filteredPersons, name);
        }
        if (Type != null && !Type.equals("")) {
            filteredPersons = filterByType(filteredPersons, Type);
        }
        return filteredPersons;
    }

    @Override
    public List<Person> filterByPerson(List<Person> persons, String name) {
        List<Person> filteredPersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.getName().toLowerCase().equals(name.toLowerCase())) {
                filteredPersons.add(person);
            }
        }
        return filteredPersons;
    }

    @Override
    public List<Person> filterByType(List<Person> persons, String type) {
        List<Person> filteredPersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.getType().toLowerCase().equals(type.toLowerCase())) {
                filteredPersons.add(person);
            }
        }
        return filteredPersons;
    }

}

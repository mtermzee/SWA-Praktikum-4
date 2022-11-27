package swa.control.team;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import swa.entity.Team;

@ApplicationScoped
public class TeamService {
    @Inject
    TeamManagement teamManagement;

    public boolean createTeam(String name, String catagory) {
        return teamManagement.createTeam(name, catagory);
    }

    public boolean deleteTeam(int id) {
        return teamManagement.deleteTeam(id);
    }

    public boolean updateTeam(int id, String name, String catagory) {
        return teamManagement.updateTeam(id, name, catagory);
    }

    public Team getTeam(int id) {
        return teamManagement.getTeam(id);
    }

    public List<Team> getAllTeams(String name, String catagory) {
        return teamManagement.getAllTeams(name, catagory);
    }

    public boolean addMemberToTeam(int teamId, int memberId) {
        return teamManagement.addMemberToTeam(teamId, memberId);
    }

    public boolean removeMemberFromTeam(int teamId, int memberId) {
        // TODO Auto-generated method stub
        return false;
    }
}

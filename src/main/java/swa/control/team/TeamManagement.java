package swa.control.team;

import java.util.List;

import swa.entity.Team;

public interface TeamManagement {
    // Team
    public boolean createTeam(String name, String catagory);

    public boolean deleteTeam(int id);

    public boolean updateTeam(int id, String name, String catagory);

    public Team getTeam(int id);

    public List<Team> getAllTeams();

    public boolean addMemberToTeam(int teamId, int memberId);
}

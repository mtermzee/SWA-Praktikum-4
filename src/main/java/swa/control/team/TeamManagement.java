package swa.control.team;

import java.util.List;

import swa.entity.Team;

public interface TeamManagement {
    // Team
    public boolean createTeam(String name, String catagory);

    public boolean deleteTeam(int id);

    public boolean updateTeam(int id, String name, String catagory);

    public Team getTeam(int id);

    public List<Team> getAllTeams(String name, String catagory);

    public boolean addMemberToTeam(int teamId, int memberId);

    public boolean removeMemberFromTeam(int teamId, int memberId);

    public List<Team> filterByName(List<Team> teams, String name);

    public List<Team> filterByCategory(List<Team> teams, String category);
}

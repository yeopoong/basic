package kyun.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kyun.jpa.Team;
import kyun.jpa.TeamDao;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public void addTeam(Team team) {
        teamDao.addTeam(team);
    }

    @Override
    public void updateTeam(Team team) {
        teamDao.updateTeam(team);
    }

    @Override
    public Team getTeam(int id) {
        return teamDao.getTeam(id);
    }

    @Override
    public void deleteTeam(int id) {
        teamDao.deleteTeam(id);
    }

    @Override
    public List<Team> getTeams() {
        return teamDao.getTeams();
    }
}

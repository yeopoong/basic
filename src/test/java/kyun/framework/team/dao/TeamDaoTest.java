package kyun.framework.team.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kyun.framework.config.DbConfig;
import kyun.framework.team.dao.TeamDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DbConfig.class)
public class TeamDaoTest {

    @Resource
    TeamDao teamDao;

    @Test
    @Transactional
    public void getTeams() {
        teamDao.getTeams();
    }
}

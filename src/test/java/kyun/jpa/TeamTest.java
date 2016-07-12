package kyun.jpa;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { DbConfig.class })
public class TeamTest {

    @Resource
    TeamDao teamDao;

    @Test
    @Transactional
    public void getTeams() {
        teamDao.getTeams();
    }
}
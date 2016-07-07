package kyun.news.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kyun.news.vo.NewsVo;

@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleMapperTest {
    
    @Autowired
    NewsMapper mapper;
    
    @Test
    public void selectArticleList() {
        NewsVo vo = new NewsVo();
        List<NewsVo> list = mapper.selectArticleList(vo);
        assertNotNull(list);
    }
}

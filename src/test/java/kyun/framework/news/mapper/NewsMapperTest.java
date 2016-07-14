package kyun.framework.news.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kyun.framework.news.mapper.NewsMapper;
import kyun.framework.news.vo.NewsVo;

@ContextConfiguration(locations = "classpath:META-INF/spring/app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class NewsMapperTest {
    
    @Autowired
    NewsMapper mapper;
    
    @Test
    public void selectArticleList() {
        NewsVo vo = new NewsVo();
        List<NewsVo> list = mapper.selectArticleList(vo);
        assertNotNull(list);
    }
}

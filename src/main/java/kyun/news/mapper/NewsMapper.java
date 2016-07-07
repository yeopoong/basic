package kyun.news.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kyun.news.vo.NewsVo;

@Repository
public interface NewsMapper {

    public List<NewsVo> selectArticleList(NewsVo vo);
}

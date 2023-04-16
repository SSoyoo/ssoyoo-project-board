package com.ssoyoo.projectboard.repository;


import com.ssoyoo.projectboard.config.JpaConfig;
import com.ssoyoo.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("jpa연결테스트")
@DataJpaTest
@Import(JpaConfig.class)
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(

            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    @DisplayName("select 테스트")
    void given_when_then_selectTest(){
        List<Article> articles = articleRepository.findAll();
        assertThat(articles)
                .isNotNull()
                .hasSize(123);

    }
    @Test
    @DisplayName("insert 테스트")
    void givenTestData_whenIserting_thenWorksFine(){
        //given
        long previousCount = articleRepository.count();
        Article article = Article.of("new article","new content","#test");
        //when
        Article savedArticle = articleRepository.save(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }
    @Test
    @DisplayName("update 테스트")

    void givenTestData_whenUpdating_thenWorksFine() {

        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springBoot_업데이트 테스트";
        article.setHashtag(updatedHashtag);
        //when
        Article savedArticle = articleRepository.saveAndFlush(article);
        articleRepository.flush();
        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);


    }
    @Test
    @DisplayName("delete 테스트")
    void givenTestData_whenDeleting_thenWorksFine(){
        //Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();
        //when
        articleRepository.deleteById(1L);
        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentsSize);


    }

}



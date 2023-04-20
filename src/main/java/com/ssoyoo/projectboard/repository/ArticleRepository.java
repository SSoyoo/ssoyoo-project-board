package com.ssoyoo.projectboard.repository;

import com.ssoyoo.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {

}

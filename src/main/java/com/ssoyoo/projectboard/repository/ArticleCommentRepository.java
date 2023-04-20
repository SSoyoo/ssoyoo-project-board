package com.ssoyoo.projectboard.repository;

import com.ssoyoo.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {


}

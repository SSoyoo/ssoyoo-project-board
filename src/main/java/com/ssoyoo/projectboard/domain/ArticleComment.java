package com.ssoyoo.projectboard.domain;

import java.time.LocalDateTime;

public class ArticleComment {


    private Long id; 
    private Article articleId; //게시글id
    private String content; //본문
    private LocalDateTime createdAt; //생성일시
    private String createdBy; //생성자
    private LocalDateTime modifiedAt; //수정시간
    private String modifiedBy; //수정자

}
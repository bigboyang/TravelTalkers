package com.example.miniproject.Domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.Transient;
import java.util.Date;

@Getter
@Setter
@ToString
public class BoardVO {

    public BoardVO(){
        System.out.println("BoardBO 생성");
    }

    // 게시판 pk번호
    private int bno;
    // 게시판 조회수
    private int cnt;
    //사진
    private String pic;
    // 좋아요 갯수
    private int likes;
    // 제목
    private String title;
    // 작성자
    private String memberId;
    // 내용
    private String content;
    // 날짜
    private Date regDate;
    // 수정 날짜
    private Date updateDate;
    // 검색어 관련
    private String searchCondition,searchKeyword;
    // 검색결과갯수(페이징 처리관련), 삭제예정
    private int selectedNum;
    // 정렬후 rnum
    private int rnum;




}

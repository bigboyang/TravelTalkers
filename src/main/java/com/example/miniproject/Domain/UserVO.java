package com.example.miniproject.Domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

    // 회원 ID
    private String memberId;

    //회원 비밀번호
    private String memberPw;

    //회원 이름
    private String memberName;

    //회원 이메일
    private String memberMail;

    //회원 우편번호
    private String memberAddr1;

    //회원 주소
    private String memberAddr2;

    //회원 상세주소
    private String memberAddr3;

    // 관리자 구분
    private int adminCk;

    //등록 일자
    private String regDate;

    //프로필 파일
    private String memberFile;
}

package com.example.miniproject.mapper;

import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Domain.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    // 회원가입;
    public void memberJoin(UserVO user);

    // 아이디 중복 검사 Check
    public int idCheck(String memberId);

    // 로그인
    public UserVO UserLogin(UserVO user);

    public int UserUpdate(UserVO user);

    public void UserDelete(UserVO user);

    public List<UserVO> GetList(Criteria cri);

    public UserVO GetUser(UserVO user);

    public void adminUserdelete(String memberId);

    public int CountUser(Criteria cri);

    public int CountUserId(String memberId);

    public int CountBoard();

    public int CountComment();

    // 마이페이지board가져오기
    List<BoardVO> getMyBoard(Criteria cri);
    int selectBoardListCnt(Criteria cri);
}
package com.example.miniproject.Service;

import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Domain.UserVO;

import java.util.List;


public interface UserService {

    public void memberJoin(UserVO vo);

    public int idCheck(String memberId);

    public UserVO memberLogin(UserVO user);

    public void memberUpdate(UserVO user);

    public void memberDelete(UserVO user);

    public List<UserVO> getUserList(Criteria cri);

    public UserVO getUser(UserVO user);

    public void UserDelete(String memberId);

    public int CountUser(Criteria cri);

    public int CountUserId(String memberId);

    public int CountBoard();

    public int CountComment();

    // 마이페이지 관련
    List<BoardVO> getMyBoard(Criteria cri);
    //
    int selectBoardListCnt(Criteria cri);

}
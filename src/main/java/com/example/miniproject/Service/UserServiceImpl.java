package com.example.miniproject.Service;

import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Domain.UserVO;
import com.example.miniproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public void memberJoin(UserVO user) {

        userMapper.memberJoin(user);
    }

    @Override
    public int idCheck(String memberId) {
        return userMapper.idCheck(memberId);
    }

    @Override
    public UserVO memberLogin(UserVO user) {

        return userMapper.UserLogin(user);

    }

    @Override
    public void memberUpdate(UserVO user) {

        userMapper.UserUpdate(user);
    }

    @Override
    public void memberDelete(UserVO user) {

        userMapper.UserDelete(user);
    }

    @Override
    public List<UserVO> getUserList(Criteria cri) {

        return userMapper.GetList(cri);
    }

    @Override
    public UserVO getUser(UserVO user) {

        return userMapper.GetUser(user);

    }

    @Override
    public void UserDelete(String memberId) {

        userMapper.adminUserdelete(memberId);
    }

    @Override
    public int CountUser(Criteria cri) {

        return userMapper.CountUser(cri);
    }

    @Override
    public int CountUserId(String memberId) {
        return userMapper.CountUserId(memberId);
    }

    @Override
    public int CountBoard() {
        return userMapper.CountBoard();
    }

    @Override
    public int CountComment() {
        return userMapper.CountComment();
    }

    @Override
    public List<BoardVO> getMyBoard(Criteria cri) {
        return userMapper.getMyBoard(cri);
    }
    @Override
    public int selectBoardListCnt(Criteria cri) {
        return userMapper.selectBoardListCnt(cri);
    }
}
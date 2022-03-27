package com.example.miniproject.Service;

import com.example.miniproject.Domain.UserVO;
import com.example.miniproject.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTEST {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    public void userJoinTEST() {
        UserVO user = new UserVO();
        user.setMemberId("admin123");
        user.setMemberPw("adminpw");
        user.setMemberName("adminName");
        user.setMemberMail("adminMail");
        user.setMemberAddr1("add");
        user.setMemberAddr2("add2");
        user.setMemberAddr3("add3");


        userService.memberJoin(user);
    }

    @Test
    public void IdCheck(){
        String id = "admin";
        String id2 = "not exist";

        userService.idCheck(id);
        userService.idCheck(id2);
    }

    @Test
    public void UpdateTEST(){

        UserVO user = new UserVO();

        user.setMemberName("변경");
        user.setMemberMail("변경 메일2");
        user.setMemberId("asd123");

        userService.memberUpdate(user);
    }

    @Test
    public void getuserTEST(){

        UserVO user = new UserVO();
        user.setMemberId("asd1234");

        userService.getUser(user);
    }

    @Test
    public void deleteTest(){

        String id = "test1";

        userService.UserDelete(id);
    }
}

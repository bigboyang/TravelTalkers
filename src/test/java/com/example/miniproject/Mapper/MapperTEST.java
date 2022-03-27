package com.example.miniproject.Mapper;

import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Domain.UserVO;
import com.example.miniproject.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class MapperTEST {

    private static final Logger log = LoggerFactory.getLogger(MapperTEST.class);

    @Autowired
    private UserMapper mapper;

//    @Test
    public void InsertTEST(){
        UserVO user = new UserVO();
        user.setMemberId("admin");
        user.setMemberPw("adminpw");
        user.setMemberName("adminName");
        user.setMemberMail("adminMail");
        user.setMemberAddr1("add");
        user.setMemberAddr2("add2");
        user.setMemberAddr3("add3");

        mapper.memberJoin(user);
    }

    @Test
    public void memberIdcheck(){
        String id = "admin";
        String id2 = "not exist";

        mapper.idCheck(id);
        mapper.idCheck(id2);
    }

    @Test
    public void UserLoginTEST(){

        UserVO user = new UserVO();

//        user.setMemberId("admin23");
//        user.setMemberPw("admin");

        // 틀린 경우
        user.setMemberId("admin23123");
        user.setMemberPw("admin");
        mapper.UserLogin(user);

        System.out.println(mapper.UserLogin(user));

    }

    @Test
    public void UpdateUserTEST(){

        UserVO user = new UserVO();

        user.setMemberName("변경");
        user.setMemberMail("변경 메일");
        user.setMemberId("asd123");

        mapper.UserUpdate(user);

    }

    @Test
    public void DeleteTEST(){

        UserVO user = new UserVO();

        user.setMemberId("testadmin");

        mapper.UserDelete(user);

    }

//    @Test
//    public void GETUserListTEST(){
//
//        List list = mapper.GetList();
//
//        list.forEach(user -> log.info(""+user));
//    }

    @Test
    public void CountTEST(){
        Criteria cri = new Criteria();

        log.info(""+mapper.CountUser(cri));
    }
}

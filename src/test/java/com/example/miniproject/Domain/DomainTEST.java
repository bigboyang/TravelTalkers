package com.example.miniproject.Domain;

import org.junit.jupiter.api.Test;

public class DomainTEST {

    @Test
    public void lombokTEST(){
        UserVO user = new UserVO();
        user.setMemberId("admin11");
        user.setMemberPw("admin11");
        user.setMemberName("admin11");
        user.setMemberMail("admin11");
        user.setMemberAddr1("admin11");
        user.setMemberAddr2("admin11");
        user.setMemberAddr3("admin11");
        user.setAdminCk(1);
        user.setRegDate("21/01/21");
        user.setMoney(10000);
        user.setPoint(10000);

        System.out.println(user.toString());
    }
}

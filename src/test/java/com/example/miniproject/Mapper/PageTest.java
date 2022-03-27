package com.example.miniproject.Mapper;


import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.CommentVO;
import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.mapper.BoardMapper;
import com.example.miniproject.mapper.UserMapper;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class PageTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    public void getListPaging(){
        CommentVO c=new CommentVO();
        c.setBno(102);
        c.setMemberId("admin23");
        c.setContext("reqeustParam 등록테스트입니다");
        mapper.insertComment(c);

    }

}

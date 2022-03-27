package com.example.miniproject.controller;

import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.CommentVO;
import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Domain.PageMakerVO;
import com.example.miniproject.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class AdminBoardController {

    @Autowired
    private BoardService boardService;

    @ModelAttribute("conditionMap")
    public Map<String,String> searchConditionMap(){
        Map<String, String> conditionMap=new HashMap<>();
        conditionMap.put("제목","TITLE");
        conditionMap.put("내용","CONTENT");
        return conditionMap;
    }
    @RequestMapping(value = "/admin/boardList")
    public String adminBoardList(Model model, Criteria c){
        int total = boardService.selectBoardListCnt(c);
        PageMakerVO pMake=new PageMakerVO(c,total);
        model.addAttribute("pageMaker",pMake);
        model.addAttribute("boardList",boardService.getPage(c));
        return "admin/boardList";
    }

    @RequestMapping(value="/admin/getBoard")
    public String adminGetBoard(Model model, BoardVO vo){
        model.addAttribute("commentList",boardService.getCommentList(vo));
        model.addAttribute("board",boardService.getBoard(vo));
        return "admin/getBoard";
    }

    @RequestMapping(value = "/admin/boardDelete")
    public String deleteBoard(BoardVO vo){
        boardService.deleteBoard(vo);
        return "redirect:/admin/boardList";
    }
    // 댓글 삭제 메소드
    @RequestMapping(value="/admin/deleteComment")
    public String deleteComment(CommentVO comment){
        this.boardService.deleteComment(comment);
        return "redirect:/admin/getBoard?bno="+comment.getBno();
    }



}

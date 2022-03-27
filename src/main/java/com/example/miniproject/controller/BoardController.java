package com.example.miniproject.controller;


import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.CommentVO;
import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Domain.PageMakerVO;
import com.example.miniproject.Service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardController() {
        System.out.println("boardController 생성");
    }


    //    @RequestMapping(value = "getBoardList")
//    public String getBoardList(@RequestParam(value="searchCondition",defaultValue = "TITLE",required = false) String condition,
//                               @RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword,
//                               BoardVO vo, Model model){
//        vo.setSearchCondition(condition);
//        vo.setSearchKeyword(keyword);
//        model.addAttribute("boardList",boardService.getBoardList(vo));
//        return "getList";
//    }
    @ModelAttribute("conditionMap")
    public Map<String,String> searchConditionMap(){
        Map<String, String> conditionMap=new HashMap<>();
        conditionMap.put("제목","TITLE");
        conditionMap.put("내용","CONTENT");
        return conditionMap;
    }
    @RequestMapping(value = "/getBoardList")
    public String getBoardList( Criteria c, Model model){
        int total = boardService.selectBoardListCnt(c);
        PageMakerVO pMake=new PageMakerVO(c,total);

        model.addAttribute("pageMaker",pMake);
        model.addAttribute("boardList",boardService.getPage(c));

        return "board/getList";
    }

    @RequestMapping(value="/getBoard")
    public String getBoard(BoardVO vo, Model model){
        // 조회수 올리는 메소드 추가
        boardService.read(vo);
        model.addAttribute("commentList",boardService.getCommentList(vo));
        model.addAttribute("board",boardService.getBoard(vo));
        return "board/board";
    }
    @RequestMapping(value = "/insertBoard")
    public String insertBoard(
            HttpServletRequest forSession, MultipartFile file, BoardVO vo) throws IOException {

        if(file != null){
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imgs";
            String basePath = projectPath + "/";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(basePath + fileName);
            file.transferTo(saveFile);
            vo.setPic(fileName);
        }

        HttpSession session = forSession.getSession();
        String id = (String) session.getAttribute("id");
        if(id == null){
            vo.setMemberId("세션없음");
        }else{
            vo.setMemberId(id);
        }

        boardService.insertBoard(vo);

        return "redirect:/board/getBoardList";
    }
    @RequestMapping(value = "/updateBoard")
    public String updateBoard(
            MultipartFile file,BoardVO vo) throws IOException {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imgs";
        String basePath = projectPath + "/";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(basePath + fileName);
        file.transferTo(saveFile);
        vo.setPic(fileName);

        boardService.updateBoard(vo);
        return "redirect:/board/getBoardList";
    }
    @RequestMapping(value = "/deleteBoard")
    public String deleteBoard(BoardVO vo){
        boardService.deleteBoard(vo);
        return "redirect:/board/getBoardList";
    }

    // 댓글관련 메소드

    // 댓글 등록 메소드
    @RequestMapping(value="insertComment")
    public String insertBoard(CommentVO comment,
                              HttpServletRequest forSession){
        HttpSession session = forSession.getSession();
        String id = (String) session.getAttribute("id");
        if(id == null){
            comment.setMemberId("세션없음");
        }else{
            comment.setMemberId(id);
        }
        boardService.insertComment(comment);
        return "redirect:/board/getBoard?bno="+comment.getBno();
    }
    // 댓글 삭제 메소드
    @RequestMapping(value="deleteComment")
    public String deleteComment(CommentVO comment){
        this.boardService.deleteComment(comment);
        return "redirect:/board/getBoard?bno="+comment.getBno();
    }


    // View관련 메소드(기능x view관련)

    // 등록폼 이동
    @RequestMapping(value="/goInsert")
    public String goInsert(){
        return "/board/boardInsert";
    }

    // 수정폼 이동, bno를 받아서 이동
    @RequestMapping(value="/goUpdate")
    public String goUpdate(BoardVO vo, Model model){
        model.addAttribute("board",boardService.getBoard(vo));
        return "/board/boardUpdate";
    }


}

package com.example.miniproject.Service;

import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.CommentVO;
import com.example.miniproject.Domain.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {

    void insertBoard(BoardVO board);
    void updateBoard(BoardVO board);
    void deleteBoard(BoardVO board);
    BoardVO getBoard(BoardVO board);
    List<BoardVO> getBoardList(BoardVO board);
    List<BoardVO> getPage(Criteria cri);
    public int selectBoardListCnt(Criteria cri); // getList페이징 삭제 예정
    void read(BoardVO board);
    void insertComment(CommentVO comment);
    List<CommentVO> getCommentList(BoardVO board);
    void deleteComment(CommentVO comment);
}

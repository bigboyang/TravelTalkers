package com.example.miniproject.Service;

import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.CommentVO;
import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("boardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardVO> getBoardList(BoardVO board) {
        return boardMapper.getBoardList(board);
    }

    @Override
    public BoardVO getBoard(BoardVO board) {

        return this.boardMapper.getBoard(board);
    }

    @Override
    public void insertBoard(BoardVO board) {
        this.boardMapper.insertBoard(board);
    }

    @Override
    public void deleteBoard(BoardVO board) {
        this.boardMapper.deleteBoard(board);
    }

    @Override
    public void updateBoard(BoardVO board) {
        this.boardMapper.updateBoard(board);
    }

    @Override
    public List<BoardVO> getPage(Criteria cri) {
        return this.boardMapper.getPage(cri);
    }
    @Override
    public int selectBoardListCnt(Criteria cri) {
        return this.boardMapper.selectBoardListCnt(cri);
    }

    @Override
    public void read(BoardVO board) {
        this.boardMapper.read(board);
    }

    @Override
    public void insertComment(CommentVO comment){
       this.boardMapper.insertComment(comment);
    }

    @Override
    public List<CommentVO> getCommentList(BoardVO board) {
        return this.boardMapper.getCommentList(board);
    }

    @Override
    public void deleteComment(CommentVO comment) {
        this.boardMapper.deleteComment(comment);
    }
}

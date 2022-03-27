package com.example.miniproject.mapper;

import com.example.miniproject.Domain.BoardVO;
import com.example.miniproject.Domain.CommentVO;
import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Service.BoardService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 글 등록
     void insertBoard(BoardVO board);
    // 글 수정 bno를 이용 title.content 수정가능
     void updateBoard(BoardVO board);
    // 글 삭제
    void deleteBoard(BoardVO board);
    // 상세보기
    BoardVO getBoard(BoardVO board);
    // 전체보기
    List<BoardVO> getBoardList(BoardVO board);
    // 페이징용도
    List<BoardVO> getPage(Criteria cri);
    // 게시글 갯수
    public int selectBoardListCnt(Criteria cri);
    // 조회수 증가
    void read(BoardVO board);
    // 댓글 등록
    void insertComment(CommentVO comment);
    // 댓글들
    List<CommentVO> getCommentList(BoardVO board);
    // 댓글 삭제
    void deleteComment(CommentVO comment);

}

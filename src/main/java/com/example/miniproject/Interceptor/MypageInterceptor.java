package com.example.miniproject.Interceptor;

import com.example.miniproject.Domain.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
@Component
public class MypageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        log.info("session 값 없으면 바로 내쫓아내버리기~");

        UserVO user = (UserVO) session.getAttribute("member");

        if (user == null ){

            response.sendRedirect("/board/getBoardList");

            return false;
        }


        return true;
    }
}

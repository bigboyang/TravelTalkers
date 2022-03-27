package com.example.miniproject.controller;


import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Domain.PageMakerVO;
import com.example.miniproject.Domain.UserVO;
import com.example.miniproject.Service.BoardService;
import com.example.miniproject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    // 홈 화면
    @GetMapping("/")
    public String hello(){

        return "hello";
    }


    // 회원가입
    @GetMapping("/user/signup")
    public String UserSignup(){

        return "user/signup";
    }

    @PostMapping("/user/signup")
    public String UserSignupPOST(UserVO user){

        String noHashPw = "";
        String HashPw = "";

        noHashPw = user.getMemberPw();
        HashPw = passwordEncoder.encode(noHashPw);
        user.setMemberPw(HashPw);

        userService.memberJoin(user);

        return "redirect:/";
    }

    @PostMapping("/user/userIdCheck")
    @ResponseBody
    public String userIdCheck(String memberId){

        int result = userService.idCheck(memberId);

        if (result != 0){
            return "fail";

        }else {
            return "success";
        }

    }

    @GetMapping("/user/mailCheck")
    @ResponseBody
    public String mailCheck(String email){

        log.info("이메일 데이터 전송 확인");
        log.info("인증번호 :" + email );

        Random random = new Random();
        int Num = random.nextInt(888888)+111111;
        log.info("인증번호 :" + Num);

        String setFrom = "jungsmtp@gmail.com";
        String to = email;
        String title = "Mini_Project 인증 이메일 입니다.";
        String content = "홈페이지를 방문해주셔서 감사합니다."
                + "<br><br>" + "인증번호는 " + Num + " 입니다 " +
                "<br>" + "해당 인증번호를 입력해주세요";

        try{
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            mailHelper.setFrom(setFrom);
            mailHelper.setTo(to);
            mailHelper.setSubject(title);
            mailHelper.setText(content,true);

            mailSender.send(mail);


        } catch (Exception e){
            e.printStackTrace();
        }


        String num = Integer.toString(Num);

        log.info("인증번호"+ num);

        return num;
    }

    // 로그인
    @GetMapping("/user/login")
    public String UserLogin(){

        return "user/login";
    }

    @PostMapping("/user/login.do")
    public String UserLoginForm(HttpServletRequest request, UserVO user, RedirectAttributes rttr){
        // HttpSession 인터페이스는 둘 이상의 page request에서
        // 사용자를 식별하거나,
        // 웹 사이트를 방문하고 해당 사용자에 대한 정보를
        // 저장하는 방법을 제공한다.

        HttpSession session = request.getSession();
        String noHashPw = "";
        String HashPw = "";

        UserVO vo = userService.memberLogin(user);

        if (vo == null){
            // 실패
            int result = 0;
            rttr.addFlashAttribute("result" ,result);
            return "redirect:/user/login";
        } else {

            noHashPw = user.getMemberPw();
            HashPw = vo.getMemberPw();

            if(true == passwordEncoder.matches(noHashPw,HashPw)){
                // 성공
                vo.setMemberPw("");
                session.setAttribute("member",vo);
                session.setAttribute("id",vo.getMemberId());

                return "redirect:/board/getBoardList";

            } else {
                // 실패
                int result = 0;
                rttr.addFlashAttribute("result" ,result);
                return "redirect:/user/login";
            }
        }

    }

    @GetMapping("/user/logout")
    public String logoutGET(HttpServletRequest request){

        HttpSession session = request.getSession();

        // 로그아웃시 사용할 세션없으므로 전부삭제
        // 있다면 removeAttribute() 으로 특정 삭제
        session.invalidate();

        return "redirect:/";

    }

    // 마이페이지

    @GetMapping("/user/mypage")
    public String mypageGET(HttpServletRequest forSession,
                            Model model,
                            Criteria cri
                            ){

        HttpSession session = forSession.getSession();
        String id = (String) session.getAttribute("id");
        cri.setMemberId(id);

        int total = userService.selectBoardListCnt(cri);
        PageMakerVO pMake=new PageMakerVO(cri,total);

        model.addAttribute("pageMaker",pMake);
        model.addAttribute("boardList",userService.getMyBoard(cri));

        return "user/mypage";
    }

    // 회원 수정

    @GetMapping("/user/mypage/modify")
    public String modifyGET(){

        return "/user/modify";
    }

    @PostMapping("/user/mypage/modify")
    public String mypagePOST(UserVO user, HttpServletRequest request, MultipartFile file) throws IOException {
        HttpSession session = request.getSession();

        String projectPath = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img";

        String basePath = projectPath + "/";

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(basePath + fileName);
        file.transferTo(saveFile);

        user.setMemberFile(fileName);

        // session MemberId값 넣기
        String value = (String) session.getAttribute("id");
        user.setMemberId(value);

        userService.memberUpdate(user);

        session.setAttribute("member",user);
        session.setAttribute("id",user.getMemberId());


        return "redirect:/board/getBoardList";
    }

    @GetMapping("/user/mypage/delete")
    public String deleteGET(){

        return "/user/delete";
    }

    @PostMapping("/user/mypage/delete")
    public String deletePOST(UserVO user, HttpServletRequest request){
        HttpSession session = request.getSession();

        String value = (String) session.getAttribute("id");

        user.setMemberId(value);

        userService.memberDelete(user);

        session.invalidate();

        return "redirect:/";

    }
}

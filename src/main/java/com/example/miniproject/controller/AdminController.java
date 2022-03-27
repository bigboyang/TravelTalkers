package com.example.miniproject.controller;

import com.example.miniproject.Domain.Criteria;
import com.example.miniproject.Domain.PageMakerVO;
import com.example.miniproject.Domain.UserVO;
import com.example.miniproject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/main")
    public String main(Model model, Criteria cri){

        int CommentCnt = userService.CountComment();
        int BoardCnt = userService.CountBoard();
        int usercnt = userService.CountUser(cri);
        model.addAttribute("usercnt",usercnt);
        model.addAttribute("BoardCnt",BoardCnt);
        model.addAttribute("CommentCnt",CommentCnt);

        return "/admin/main";
    }

    @GetMapping("/admin/userList")
    public String userList(Model model, Criteria cri){

        int CntUser = userService.CountUser(cri);
        PageMakerVO pageMake = new PageMakerVO(cri, CntUser);

        model.addAttribute("list",userService.getUserList(cri));
        model.addAttribute("pageMaker",pageMake);

        return "/admin/userList";
    }

    @GetMapping("admin/getuser")
    public String getUserPageGET(UserVO user, Model model, Criteria cri){
        int CntUser = userService.CountUser(cri);
        PageMakerVO pageMake = new PageMakerVO(cri, CntUser);

        model.addAttribute("countboard",userService.CountUserId(user.getMemberId()));
        model.addAttribute("userget", userService.getUser(user));
        model.addAttribute("pageMaker", pageMake);

        return "/admin/getuser";
    }

    @PostMapping("admin/delete")
    public String DeleteAdminUser(String memberId, RedirectAttributes rttr){

        userService.UserDelete(memberId);

        rttr.addFlashAttribute("result","delete success");

        return "redirect:/admin/userList";

    }

}

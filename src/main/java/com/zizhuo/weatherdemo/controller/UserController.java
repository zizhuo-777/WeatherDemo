package com.zizhuo.weatherdemo.controller;


import com.zizhuo.weatherdemo.bean.User;
import com.zizhuo.weatherdemo.dao.UserDao;
import com.zizhuo.weatherdemo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "UserService")
    UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }


    @RequestMapping("/checkLogin")
    public String checkLogin(String username, String password, Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            model.addAttribute("user", user);
            return "main.html";
        } else {
            return "login.html";
        }
    }

    @RequestMapping("/list")
    public String userList(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user-list.html";
    }

    @RequestMapping("/delete")
    public String delById(int id, Model model) {
        int i = userService.deleteById(id);
        if (i == 0) {
            model.addAttribute("err", "操作失败");
        }
        return "redirect:/user/list";

    }

    @RequestMapping("/edit")
    public String edit() {

        return "user-edit.html";
    }

    @RequestMapping("/editEnter")
    public String editEnter(User user) {

        int i=0;
        /*if (StringUtils.isNoneBlank()) {
            i = userService.updateById(user);
        }else {

        }*/
        i = userService.insertUser(user);
        if (i > 0) {
            return "redirect:user/list";
        } else {
            return "user/user-edit.html";
        }
    }

    @RequestMapping("/update")
    public String update(int id,Model model){
        User user = userService.findById(id);

        model.addAttribute("user",user);
        return "user-update.html";
    }

    @RequestMapping("/updateEnter")
    public String updateEnter(User user) {

        int i = userService.updateById(user);
        if (i > 0) {
            return "redirect:user/list";
        } else {
            return "user/user-edit.html";
        }
    }
}

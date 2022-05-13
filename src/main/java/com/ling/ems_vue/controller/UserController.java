package com.ling.ems_vue.controller;

import com.ling.ems_vue.entity.User;
import com.ling.ems_vue.service.UserService;
import com.ling.ems_vue.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 用来处理用户登录
     */
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user){
        log.info("当前登录用户的信息: [{}]",user.toString());
        Map<String,Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            map.put("status",true);
            map.put("msg","登陆成功");
            map.put("data",userDB);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",false);
            map.put("msg",e.getMessage());

        }
        return map;
    }

    /**
     * 用来处理用户注册
     */
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody User user,String code,HttpServletRequest request){
        log.info("用户信息: [{}]",user.toString());
        log.info("用户输入的验证码信息: [{}]",code);
        Map<String,Object> map = new HashMap<>();
        try {
            String Key = (String) request.getServletContext().getAttribute("code");
            if (Key.equalsIgnoreCase(code)){
                //1 调用业务方法
                userService.register(user);
                map.put("status",true);
                map.put("msg","注册成功!");
            }else {
                throw new RuntimeException("验证码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("msg",e.getMessage());
        }
        return map;

    }

    /**
     * 获取登陆验证码
     */
    @GetMapping("/getImage")
    public String getImage(HttpServletRequest servlet) throws IOException {
        //1 使用工具类生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //2 将验证码放入servletContext作用域，为了要最高作用域
        servlet.getServletContext().setAttribute("code",code);
        //3 将图片转为base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(120, 30,outputStream,code);
        return "data:image/png;base64," + Base64Utils.encodeToString(outputStream.toByteArray());
    }
}

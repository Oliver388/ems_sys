package com.ling.ems_vue.controller;

import com.ling.ems_vue.utils.VerifyCodeUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

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

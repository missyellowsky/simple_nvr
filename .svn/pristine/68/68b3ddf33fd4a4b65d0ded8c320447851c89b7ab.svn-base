package com.recorder.recorder.controller;

import com.recorder.recorder.beans.Result;
import com.recorder.recorder.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "auth/check", method = RequestMethod.POST)
    public Result checkByPrivateKey(@RequestParam(name = "pass", required = true) String pass, HttpServletResponse response){
        Boolean result = authService.checkByPrivateKey(pass);
        System.out.println(result);
        if(!result){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
        }
        return Result.success();
    }

    @RequestMapping(value = "auth/addAuthMember", method = RequestMethod.POST)
    public Result addAuthMember(@RequestBody String member){
        Result result = authService.addAuthMember(member);
        return result;
    }

    @RequestMapping(value = "auth/removeAuthMember", method = RequestMethod.POST)
    public Result removeAuthMember(@RequestBody String member){
        Result result = authService.removeAuthMenber(member);
        return result;
    }
}

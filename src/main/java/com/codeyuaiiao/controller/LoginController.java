package com.codeyuaiiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.entity.TbAdmin;
import com.codeyuaiiao.entity.TbEnterprise;
import com.codeyuaiiao.entity.TbUser;
import com.codeyuaiiao.service.TbAdminService;
import com.codeyuaiiao.service.TbEnterpriseService;
import com.codeyuaiiao.service.TbUserService;
import com.codeyuaiiao.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private TbAdminService tbAdminService;

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbEnterpriseService tbEnterpriseService;

    @Autowired
    private ResultGenerator generator;


    //管理员登录
    @PostMapping(value = "/admin")
    public RestResult adminLogin( HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        QueryWrapper<TbAdmin> wrapper = new QueryWrapper<>();
        wrapper.allEq(map,false);
        if(tbAdminService.getMap(wrapper).size()!=3) return generator.getFailResult("登录失败~~");
        System.out.println(tbAdminService.getMap(wrapper).size());
        String token = JwtUtil.getToken(map);
        return generator.getTokenResult(token);
    }

//    //用户登录
    @PostMapping(value = "/user")
    public RestResult userLogin( HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.allEq(map,false);
        Map<String, Object> userMap = tbUserService.getMap(wrapper);
        Object backname = userMap.get("username");
        if(userMap.size()!=12) return generator.getFailResult("登录失败~~");
        System.out.println(tbUserService.getMap(wrapper).size());
        String token = JwtUtil.getToken(map);
        return generator.getSuccessResult(backname,token);
    }
//    //企业登录
    @PostMapping(value = "/entr")
    public RestResult entrLogin( HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        QueryWrapper<TbEnterprise> wrapper = new QueryWrapper<>();
        wrapper.allEq(map,false);
        Map<String, Object> entrMap = tbEnterpriseService.getMap(wrapper);
        Object backname = entrMap.get("username");
        if(entrMap.size()!=9) return generator.getFailResult("登录失败~~");
        System.out.println(tbEnterpriseService.getMap(wrapper).size());
        String token = JwtUtil.getToken(map);
        return generator.getSuccessResult(backname,token);
    }


    @PostMapping(value = "/token")
    public Map<String, Object> token() {
        Map<String, Object> map = new HashMap<>();
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }
}
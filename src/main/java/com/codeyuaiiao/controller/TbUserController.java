package com.codeyuaiiao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.entity.TbUser;
import com.codeyuaiiao.service.TbUserService;
import com.codeyuaiiao.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.codeyuaiiao.utils.DateUtil.DATE_FORMAT_YYYY_MM_DD;


/**
 * <p>
 * 用户前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/user")
public class TbUserController {
    @Autowired
    private ResultGenerator generator;
    @Autowired
    private TbUserService tbUserService;

    //查询用户账号的所有信息
    @PostMapping("/allInfo")
    public RestResult userAllInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        System.out.println(username);
        if (username != "") {
            return this.likeOneInfo(request);
        }
        List<TbUser> list = tbUserService.list();
        if (list != null) {
            return generator.getSuccessResult(list);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //模糊查询用户一条信息,通过用户名
    @PostMapping("/likeOneInfo")
    public RestResult likeOneInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username", username);
        List<TbUser> list = tbUserService.list(wrapper);
        if (list != null) {
            return generator.getSuccessResult(list);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //    用户注册
    @PostMapping("/register")
    public RestResult register(HttpServletRequest request) {
            TbUser tbUser = new TbUser();
            tbUser.setUsername(request.getParameter("username"));
            tbUser.setPassword(request.getParameter("password"));
            tbUser.setName(request.getParameter("name"));
            tbUser.setAge(request.getParameter("age"));
            tbUser.setGender(request.getParameter("gender"));
            tbUser.setBirthday(request.getParameter("birthday"));
            tbUser.setSchool(request.getParameter("school"));
            tbUser.setPhone(request.getParameter("phone"));
            tbUser.setEmail(request.getParameter("email"));
            tbUser.setSpecialty(request.getParameter("specialty"));
            tbUser.setEducation(request.getParameter("education"));
            String username = tbUser.getUsername();
            QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);
            List<TbUser> list = tbUserService.list(wrapper);
            if (!list.isEmpty()) {
                return generator.getFailResult("用户名重复,请重新输入");
            }
            boolean save = tbUserService.save(tbUser);
            if (save) {
                return generator.getSuccessResult("注册成功");
            } else {
                return generator.getFailResult("注册失败!!");
            }
    }

    //得到一条用户数据
    @PostMapping("/getOneInfo")
    public RestResult getUserOneInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        TbUser one = tbUserService.getOne(wrapper);
        if (one != null) {
            return generator.getSuccessResult(one);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //更新一条用户数据
    @PutMapping("/editOneInfo")
    public RestResult editUserOneInfo(TbUser tbUser){
        boolean update = tbUserService.updateById(tbUser);
        System.out.println(update);
        if (update) {
            return generator.getSuccessResult();
        } else {
            return generator.getFailResult("更新失败");
        }
    }

    //删除一条数据
    @PostMapping("/deleteInfo")
    public RestResult deleteInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean remove = tbUserService.remove(wrapper);
        if(remove){
            return generator.getSuccessResult("删除成功");
        }else{
            return generator.getFailResult("删除失败");
        }
    }

}

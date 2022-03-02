package com.codeyuaiiao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.entity.TbAdmin;
import com.codeyuaiiao.entity.TbEnterprise;
import com.codeyuaiiao.entity.TbUser;
import com.codeyuaiiao.service.TbEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  企业前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/enterprise")
public class TbEnterpriseController {
    @Autowired
    private ResultGenerator generator;
    @Autowired
    private TbEnterpriseService tbEnterpriseService;

    //查询企业的所有信息
    @PostMapping("/allInfo")
    public RestResult enterpriseAllInfo(HttpServletRequest request){
        String username = request.getParameter("username");
        if(username!=""){
           return this.likeOneInfo(request);
        }
        List<TbEnterprise> list = tbEnterpriseService.list();
        if(list!=null){
            return generator.getSuccessResult(list);
        }else{
            return generator.getFailResult("没有数据");
        }
    }

    //模糊查询得到一条信息
    @PostMapping("/likeOneInfo")
    public RestResult likeOneInfo(HttpServletRequest request){
        String username = request.getParameter("username");
        System.out.println(username);
        QueryWrapper<TbEnterprise> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username",username);
        List<TbEnterprise> list = tbEnterpriseService.list(wrapper);
        if(list!=null){
            return generator.getSuccessResult(list);
        }else{
            return generator.getFailResult("没有数据");
        }
    }

    // 企业注册
    @PostMapping("/register")
    public RestResult register(HttpServletRequest request) {
        TbEnterprise entr = new TbEnterprise();
        entr.setUsername(request.getParameter("username"));
        entr.setPassword(request.getParameter("password"));
        entr.setName(request.getParameter("name"));
        entr.setEmail(request.getParameter("email"));
        entr.setPhone(request.getParameter("phone"));
        entr.setManage(request.getParameter("manage"));
        entr.setAddress(request.getParameter("address"));
        entr.setIntroduce(request.getParameter("introduce"));
        String username = entr.getUsername();
        QueryWrapper<TbEnterprise> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<TbEnterprise> list = tbEnterpriseService.list(wrapper);
        if (!list.isEmpty()) {
            return generator.getFailResult("用户名重复,请重新输入");
        }
        boolean save = tbEnterpriseService.save(entr);
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
        QueryWrapper<TbEnterprise> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        TbEnterprise one = tbEnterpriseService.getOne(wrapper);
        if (one != null) {
            return generator.getSuccessResult(one);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //更新一条用户数据
    @PutMapping("/editOneInfo")
    public RestResult editUserOneInfo(TbEnterprise tbEnterprise){
        boolean update = tbEnterpriseService.updateById(tbEnterprise);
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
        QueryWrapper<TbEnterprise> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean remove = tbEnterpriseService.remove(wrapper);
        if(remove){
            return generator.getSuccessResult("删除成功");
        }else{
            return generator.getFailResult("删除失败");
        }
    }
}

package com.codeyuaiiao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.entity.TbAdmin;
import com.codeyuaiiao.entity.TbUser;
import com.codeyuaiiao.service.TbAdminService;
import com.codeyuaiiao.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.codeyuaiiao.utils.DateUtil.DATE_FORMAT_YYYY_MM_DD;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/admin")
public class TbAdminController {
    @Autowired
    private ResultGenerator generator;
    @Autowired
    private TbAdminService tbAdminService;

    //查询管理员账号的所有信息
    @PostMapping("/allInfo")
    public RestResult adminAllInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        if (username != "") {
            return this.likeOneInfo(request);
        }
        List<TbAdmin> list = tbAdminService.list();
        if (list != null) {
            return generator.getSuccessResult(list);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //模糊查询得到一条数据
    @PostMapping("/likeOneInfo")
    public RestResult likeOneInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        QueryWrapper<TbAdmin> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username", username);
        List<TbAdmin> list = tbAdminService.list(wrapper);
        if (list != null) {
            return generator.getSuccessResult(list);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    // 管理者注册
    @PostMapping("/register")
    public RestResult register(HttpServletRequest request) {
            TbAdmin admin = new TbAdmin();
            admin.setUsername(request.getParameter("username"));
            admin.setPassword(request.getParameter("password"));
            String username = admin.getUsername();
            QueryWrapper<TbAdmin> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);
            List<TbAdmin> list = tbAdminService.list(wrapper);
            if (!list.isEmpty()) {
                return generator.getFailResult("用户名重复,请重新输入");
            }
            boolean save = tbAdminService.save(admin);
            if (save) {
                return generator.getSuccessResult("注册成功");
            } else {
                return generator.getFailResult("注册失败!!");
            }
    }

    //准确比较得到一条用户数据
    @PostMapping("/getOneInfo")
    public RestResult getUserOneInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        QueryWrapper<TbAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        TbAdmin one = tbAdminService.getOne(wrapper);
        if (one != null) {
            return generator.getSuccessResult(one);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //更新一条用户数据
    @PutMapping("/editOneInfo")
    public RestResult editUserOneInfo(TbAdmin tbAdmin){
        boolean update = tbAdminService.updateById(tbAdmin);
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
        QueryWrapper<TbAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean remove = tbAdminService.remove(wrapper);
        if(remove){
            return generator.getSuccessResult("删除成功");
        }else{
            return generator.getFailResult("删除失败");
        }
    }
}

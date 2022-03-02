package com.codeyuaiiao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.entity.TbEnterprise;
import com.codeyuaiiao.entity.TbResume;
import com.codeyuaiiao.entity.TbUser;
import com.codeyuaiiao.service.TbResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  简历前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/resume")
public class TbResumeController {
    @Autowired
    private ResultGenerator generator;
    @Autowired
    private TbResumeService tbResumeService;

    //查询简历的所有信息
    @PostMapping("/allInfo")
    public RestResult resumeAllInfo(HttpServletRequest request){
        String username = request.getParameter("username");
        if (username != "" ) {
            return this.likeOneInfo(request);
        }
        List<TbResume> list = tbResumeService.list();
        if(list!=null){
            return generator.getSuccessResult(list);
        }else{
            return generator.getFailResult("没有数据");
        }
    }

    @PostMapping("/register")
    public RestResult register(HttpServletRequest request) {
        TbResume resume = new TbResume();
        resume.setUsername(request.getParameter("username"));
        resume.setIndustry(request.getParameter("industry"));
        resume.setWorkExperience(request.getParameter("workExperience"));
        resume.setAddress(request.getParameter("address"));
        resume.setSalary(request.getParameter("salary"));
        resume.setIntentionJob(request.getParameter("intentionJob"));
        resume.setJobStatus(request.getParameter("JobStatus"));
        resume.setPersonalIntroduction(request.getParameter("personalIntroduction"));
        resume.setValidTime(request.getParameter("validTime"));
        String username = resume.getUsername();
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<TbResume> list = tbResumeService.list(wrapper);
        if (!list.isEmpty()) {
            return generator.getFailResult("用户名重复,请重新输入");
        }
        boolean save = tbResumeService.save(resume);
        if (save) {
            return generator.getSuccessResult("注册成功");
        } else {
            return generator.getFailResult("注册失败!!");
        }
    }

    //模糊查询得到一条信息
    @PostMapping("/likeOneInfo")
    public RestResult likeOneInfo(HttpServletRequest request){
        String username = request.getParameter("username");
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username",username);
        List<TbResume> list = tbResumeService.list(wrapper);
        if(list != null){
            System.out.println("Yes");
            return generator.getSuccessResult(list);
        }else{
            System.out.println("No");
            return generator.getFailResult("没有数据");
        }
    }

    //得到一条用户数据
    @PostMapping("/getOneInfo")
    public RestResult getUserOneInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        TbResume one = tbResumeService.getOne(wrapper);
        if (one != null) {
            return generator.getSuccessResult(one);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //更新一条用户数据
    @PutMapping("/editOneInfo")
    public RestResult editUserOneInfo(TbResume resume){
        boolean update = tbResumeService.updateById(resume);
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
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean remove = tbResumeService.remove(wrapper);
        if(remove){
            return generator.getSuccessResult("删除成功");
        }else{
            return generator.getFailResult("删除失败");
        }
    }
}

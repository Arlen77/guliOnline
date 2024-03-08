package com.atguigu.serviceedu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.vo.TeacherQuery;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2020-08-02
 */
@Api(description = "讲师管理")
@AllArgsConstructor
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    //查询讲师表所有的数据
    @GetMapping("findAll")
    public R findAllTeachers(){
        //调用service的方法查询数据
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //删除讲师表数据
    @DeleteMapping("{id}")
    public R removeById(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        Page<EduTeacher> page = new Page<>(current, limit);
        teacherService.page(page,null);
        return R.ok().data("result",page);
    }

    //条件查询分页
//    @GetMapping("pageTeacherCondition/{current}/{limit}")
//    public R pageTeacherCondition(@PathVariable long current,
//                                  @PathVariable long limit,
//                                  TeacherQuery teacherQuery){
//        Page<EduTeacher> page = new Page<>(current,limit);
//        //创建构造条件
//        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
//        String name = teacherQuery.getName();
//        Integer level = teacherQuery.getLevel();
//        String begin = teacherQuery.getBegin();
//        String end = teacherQuery.getEnd();
//        if (!StringUtils.isEmpty(name)) {
//            wrapper.like("name", name);
//        }
//        if (!org.springframework.util.StringUtils.isEmpty(level)) {
//            wrapper.eq("level", level);
//        }
//        if (!StringUtils.isEmpty(begin)) {
//            wrapper.ge("gmt_create", begin);
//        }
//        if (!StringUtils.isEmpty(end)) {
//            wrapper.le("gmt_create", end);
//        }
//
//        teacherService.page(page,wrapper);
//
//        return R.ok().data("result",page);
//    }

    //条件查询分页
   @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        IPage<EduTeacher> resultPage = teacherService.pageTeacherCondition(current, limit, teacherQuery);
        return R.ok().data("result", resultPage);
   }

    /**
     * 添加讲师
     */
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        return save?R.ok() : R.error();
    }

    /**
     * 根据ID查询讲师
     */
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }
    /**
     * 修改讲师
     */
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        return flag?R.ok() : R.error();
    }
}
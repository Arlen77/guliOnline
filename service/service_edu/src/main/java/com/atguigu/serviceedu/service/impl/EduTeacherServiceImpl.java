package com.atguigu.serviceedu.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.vo.TeacherQuery;
import com.atguigu.serviceedu.mapper.EduTeacherMapper;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-02
 */
@Service
@AllArgsConstructor
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        return null;
    }

    //条件查询分页
    @Override
    public IPage<EduTeacher> pageTeacherCondition(long current, long limit, TeacherQuery teacherQuery) {
        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(teacherQuery.getName()),
                        "name", teacherQuery.getName())
                .eq(teacherQuery.getLevel() != null,
                        "level", teacherQuery.getLevel())
                .ge(!StringUtils.isEmpty(teacherQuery.getBegin()),
                        "gmt_create", teacherQuery.getBegin())
                .le(!StringUtils.isEmpty(teacherQuery.getEnd()),
                        "gmt_create", teacherQuery.getEnd());
        return this.page(page, wrapper);
    }

}

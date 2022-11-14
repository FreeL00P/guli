package com.atguigu.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.excel.SubjectData;
import com.atguigu.serviceedu.entity.subject.OneSubject;
import com.atguigu.serviceedu.entity.subject.TwoSubject;
import com.atguigu.serviceedu.listener.SubjectExcelListener;
import com.atguigu.serviceedu.mapper.EduSubjectMapper;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-01
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            //文件输入流
            InputStream inputStream = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        //查询一级分类
        QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<EduSubject> oneEduSubject = baseMapper.selectList(oneWrapper);

        //查询二级分类
        QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
        oneWrapper.ne("parent_id","0");
        List<EduSubject> twoEduSubject = baseMapper.selectList(twoWrapper);

        /**
         * 整合oneSubject和twoSubject
        */
        //最终返回结果
        ArrayList<OneSubject> finalSubject = new ArrayList<>();
        //封装一级分类
        //把eduSubject的数据拷贝到oneSubject
        /**
         * 参数解析
         *  finalSubject 最终返回结果
         *  oneEduSubject 一级分类列表 来源上方查询
         *      eduSubject 来源于一级分类列表 循环取出
         *      oneSubject eduSubject拷贝到oneSubject
         *  twoEduSubject 二级分类列表 来源上方查询
         *      twoSubject 逐一取出的二级分类列表元素
         *      addTwoSubject 经过判断后将对于的 twoSubject数据拷贝到此
         *      twoFinalSubjects 存放拷贝后的addTwoSubject
         *
         */
        for (EduSubject eduSubject : oneEduSubject) {
            OneSubject oneSubject = new OneSubject();
            //对象拷贝
            BeanUtils.copyProperties(eduSubject, oneSubject);


            //封装每一级分类下的二级分类
            ArrayList<TwoSubject> twoFinalSubjects = new ArrayList<>();
            for (EduSubject twoSubject : twoEduSubject) {
                //判断二级分类pid和一级分类id
                if (eduSubject.getId().equals(twoSubject.getParentId())){
                    //把twoSubject 复制到twoFinalSubjects
                    TwoSubject addTwoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,addTwoSubject);
                    twoFinalSubjects.add(addTwoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjects);
            finalSubject.add(oneSubject);
        }

        return finalSubject;
    }
}

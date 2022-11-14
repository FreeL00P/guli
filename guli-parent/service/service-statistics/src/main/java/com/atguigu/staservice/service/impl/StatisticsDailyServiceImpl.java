package com.atguigu.staservice.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.RandomUtil;
import com.atguigu.staservice.client.UcenterClient;
import com.atguigu.staservice.entity.StatisticsDaily;
import com.atguigu.staservice.mapper.StatisticsDailyMapper;
import com.atguigu.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-13
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public void createStatisticsByDay(String day) {

        //删除已存在的统计对象
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dayQueryWrapper);


        R r = ucenterClient.registerCount(day);
        Integer countRegister = (Integer) r.getData().get("countRegister");
        //把获取到的数据添加到对象 在保存到数据库
        StatisticsDaily stats=new StatisticsDaily();
        stats.setRegisterNum(countRegister);//注册人数
        stats.setDateCalculated(day);//统计日期

        stats.setVideoViewNum(RandomUtils.nextInt(100,200));
        stats.setLoginNum(RandomUtils.nextInt(100,200));
        stats.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(stats);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> staDailyList = baseMapper.selectList(wrapper);
        //遍历集合 取出日期数据 数量数据 存放到型的集合
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList =new ArrayList<>();
        for (StatisticsDaily sta : staDailyList) {
            date_calculatedList.add(sta.getDateCalculated());
            switch (type){
                case "login_num":
                    numDataList.add(sta.getLoginNum());
                case "course_num":
                    numDataList.add(sta.getCourseNum());
                case "register_num":
                    numDataList.add(sta.getRegisterNum());
                case "video_view_num":
                    numDataList.add(sta.getVideoViewNum());
            }
        }
        Map<String, Object> map=new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }
}

package com.nzkj.screen.Controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nzkj.screen.Entity.Bill;
import com.nzkj.screen.Entity.DTO.GunMonitorDto;
import com.nzkj.screen.Entity.DTO.GunStateEnum;
import com.nzkj.screen.Entity.DTO.StationDto;

import com.nzkj.screen.Utils.StringUtils;
import com.nzkj.screen.mapper.pile.bill.IBillMapper;
import com.xiaoleilu.hutool.util.CollectionUtil;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: Liu yang
 * @Date: 2020/7/7 10:10
 * Describe:
 */
@RestController
public class ScreenController {

    @Autowired
    private RedisTemplate redisTemplate;

    //商家id为固定值
    @Value("${sellerId}")
    private long sellerId;

    //userId为固定值
    @Value("${userId}")
    private long userId;

    //缓存到redis中的key过期时间  单位为秒  最好不要设置为永久
    //设置为永久后永远能从redis拿到数据 如有错误很难发现
    @Value("${expireTime}")
    private int expireTime;

    @Autowired
    private IBillMapper billMapper;



    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){

        Bill b = billMapper.get(1l);
        System.out.println(b);


    }



}

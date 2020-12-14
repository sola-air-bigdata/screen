package com.nzkj.screen.Utils;


import com.google.gson.Gson;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * <p>项目名称：广交大屏项目</p>
 * <p>文件名称：redis配置</p>
 * <p>描述：[类型描述]</p>
 * <p>创建时间：2019年9月11日13:19:22</p>
 * <p>公司信息：广州市凝智科技有限公司研发部</p>
 *
 * @version v1.0
 * @author<a>huangxw</a>
 * @update [序号][日期YYYY-MM-DD][更改人名][变更描述]
 */
@Component("cacheUtil")
public class RedisCacheUtil {

     @Resource
     private RedisTemplate<Serializable, Object> redisTemplate;//redis操作模板

        public void put(String key, String value) {
            if (key==null || "".equals(key)) {
                return;
            }
            redisTemplate.opsForValue().set(key,value);
            redisTemplate.expire(key, 3, TimeUnit.HOURS);

        }


        public void put(String key, Object value) {
            if (key==null || "".equals(key)) {
                return;
            }
            redisTemplate.opsForValue().set(key, new Gson().toJson(value));
            redisTemplate.expire(key, 3, TimeUnit.HOURS);
        }


        public <T> T get(String key, Class<T> className) {
            Object obj = redisTemplate.opsForValue().get(key);
            if(obj == null){
                return null;
            }
            return new Gson().fromJson(""+obj, className);
        }


        public String get(String key) {
            Object obj = redisTemplate.opsForValue().get(key);
            if(obj == null){
                return null;
            }else{
                return String.valueOf(obj);
            }
        }


}

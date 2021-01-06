package com.nzkj.screen.memory;


import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>项目名称：广交充电桩项目</p>
 * <p>文件名称：redis配置</p>
 * <p>描述：[类型描述]</p>
 * <p>创建时间：2019年9月11日13:19:22</p>
 * <p>公司信息：广州市凝智科技有限公司研发部</p>
 *
 * @version v1.0
 * @author<a>huangxw</a>
 * @update [序号][日期YYYY-MM-DD][更改人名][变更描述]
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

     static {
         ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
     }
    @Bean
    @Qualifier("redisTemplates")
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer keySerializer = new StringRedisSerializer();
        RedisSerializer valueSerializer = new FastJsonRedisSerializer();
        //key采用字符串反序列化对象
        redisTemplate.setKeySerializer(keySerializer);
        //value也采用字符串反序列化对象
        //原因：管道操作，是对redis命令的批量操作，各个命令返回结果可能类型不同
        //可能是 Boolean类型 可能是String类型 可能是byte[]类型 因此统一将结果按照String处理
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        return redisTemplate;
    }

}
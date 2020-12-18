package com.nzkj.screen.memory;


import com.nzkj.screen.Utils.LogUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedisKeyBuilder {
	/*分隔符*/
    protected final static String SEPARATOR = ":";
    /*通配符*/
    protected final static String PLACE_HOLDER = "*";
    /**
     * 生成redis键
     * @param redisData 保存到redis中的类型
     * @param patterns 组成键的元素
     * @return
     * @author 007idle
     * @date 2017年12月30日
     */
    protected String buildKey(RedisDataEnum redisData ,Object... patterns) {
    	StringBuilder key = new StringBuilder(redisData.getPrefix()).append(SEPARATOR);
		for(int i = 0 , size = patterns.length ; i < size; i++) {
			key.append(patterns[i]);
			if(i != size - 1) {
				key.append(SEPARATOR);
			}
		}
		return key.toString();
	}
    /**
     * 拆分键
     * @param key 需要拆分的键
     * @return
     * @author 007idle
     * @date 2017年12月30日
     * @description
     */
    public List<String> splitKey(String key){
    	if(StrUtil.isEmpty(key)) {
    		return Collections.emptyList();
    	}
    	return StrUtil.split(key, ':');
    }
    /**
     * a扫描获取key
     * @param redisTemplate
     * @param parttern 匹配字符串
     * @param count 扫描获取返回数量
     * @return
     */
    public Set<String> scan(RedisTemplate<String, ? extends Object> redisTemplate, final String parttern, final int count){
    	Set<String> keys = redisTemplate.execute(new RedisCallback<Set<String>>() {
			@Override
			public Set<String> doInRedis(RedisConnection conn) throws DataAccessException {
				Set<String> set = new HashSet<>();
				Cursor<byte[]> cursor = conn.scan(ScanOptions.scanOptions().match(parttern).count(count).build());
				while (cursor.hasNext()) {
					set.add(new String(cursor.next()));
				}
				try {
					cursor.close();
				} catch (IOException e) {
					LogUtil.error("redis的scan出现问题", e);
				}
				return set;
			}
		});
    	return keys;
    }
    /**
     * a扫描获取单个key
     * @param redisTemplate
     * @param parttern 匹配字符串
     * @return 
     */
    public String scanOne(RedisTemplate<String, ? extends Object> redisTemplate, final String parttern) {
    	Set<String> keys = scan(redisTemplate, parttern, 1);
    	return CollectionUtil.isEmpty(keys) ? null : keys.iterator().next();
    }
    /**
     * a扫描获取set集合里面的key
     * @param redisTemplate
     * @param key 
     * @param parttern 匹配字符串
     * @param count 扫描获取返回数量
     * @return
     */
    public Set<String> sscan(RedisTemplate<String, ? extends Object> redisTemplate, final String key, final String parttern, final int count){
    	Set<String> keys = redisTemplate.execute(new RedisCallback<Set<String>>() {
			@Override
			public Set<String> doInRedis(RedisConnection conn) throws DataAccessException {
				Set<String> set = new HashSet<>();
				Cursor<byte[]> cursor = conn.sScan(key.getBytes(),ScanOptions.scanOptions().match(parttern).count(count).build());
				while (cursor.hasNext()) {
					set.add(new String(cursor.next()));
				}
				try {
					cursor.close();
				} catch (IOException e) {
					LogUtil.error("redis的scan出现问题", e);
				}
				return set;
			}
		});
    	return keys;
    }
    /**
     * a扫描获取set集合里面的一个key
     * @param redisTemplate
     * @param key
     * @param parttern
     * @return
     */
    public String sscanOne(RedisTemplate<String, ? extends Object> redisTemplate, String key, final String parttern) {
    	Set<String> keys = sscan(redisTemplate, parttern, key, 1);
    	return CollectionUtil.isEmpty(keys) ? null : keys.iterator().next();
    }
    
}

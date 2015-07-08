package com.tingyun.auto.utils;

import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManger {
	
	private static Logger logger = LoggerFactory.getLogger(RedisManger.class);
	private static ResourceBundle bundle = ResourceBundle.getBundle("ftp");
	private static Jedis redis;//非切片额客户端连接
    private static JedisPool jedisPool;//非切片连接池
    
    static{
    	try {
			JedisPoolConfig config = new JedisPoolConfig();
			
			config.setMaxIdle(Integer.parseInt(bundle.getString("redis.maxIdle")));
			
			config.setMaxWaitMillis(Long.parseLong(bundle.getString("redis.maxWait")));
			
			config.setTestOnBorrow(bundle.getString("redis.testOnBorrow").equals("true")?Boolean.TRUE:Boolean.FALSE);
			jedisPool = new JedisPool(config,bundle.getString("redis.host"),Integer.parseInt(bundle.getString("redis.port")));// 连接redis
			logger.info("Connect Redis Server Successful!");
		} catch (Exception e) {
			logger.error("Faild to Connect Redis Server!");
		}
    }
	
	
	/**
	* @author : chenjingli
	* @decription 初始化基本池
	* @return
	 */
    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
            	redis =  jedisPool.getResource();
            	return redis;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
    
	public void DisConn() {
		try {
			getJedis().disconnect();
		} catch (Exception e) {
			logger.error("Faild to disconnect Redis Server!");
		}
	}

	/**写入key - value方式的数据
	 * @param key
	 * @param value string
	 * @date	Nov 24, 2013
	 */ 
	public static void setValue(String key, String value) {
		try {
			logger.info("通过key={}存入redis的value是={}",key,value);
			getJedis().set(key, value);
		} catch (Exception e) {
			logger.error("Faild Set key!");
		}
	}
	
	/**写入key - List<string>方式数据，key可以相同
	 * @param key
	 * @param value List<String>
	 * @date	Nov 24, 2013
	 */ 
	public void setListValue(String key, List<String> value) {
		try {
			getJedis() .del(key);  
			for(int i=0;i<value.size();i++){
			    redis.rpush(key, value.get(i));//顺序添加，lpush堆的方式添加value
			}
		} catch (Exception e) {
			logger.error("Faild Set ListValue!");
		}
	}
	
	public boolean existsKey(String key) {
	try {
		return getJedis() .exists(key);
	} catch (Exception e) {
		logger.error("Faild Del key!");
		return false;
	}
} 
	
	public void delKey(String key) {
		try {
			getJedis() .del(key);
		} catch (Exception e) {
			logger.error("Faild Del key!");
		}
	}
	
	public static String getValue(String key,String phone) {
		try {
			if (!getJedis().exists(key+phone)) {
				logger.error("Faild Get Value! key======{}",key+phone);
				return null;
			} else {
				String value = redis.get(key+phone).split("#")[1];
				returnResource(redis);
				logger.info("redis通过key-----{},获取的vakue值是-----{}",key+phone,value);
				return value;// GET key 返回key所关联的字符串值
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<String> getListValues(String key) {
		try {
			List<String> values = getJedis().lrange(key, 0, -1);  
	        return values;
		} catch (Exception e) {
			logger.error("Faild Get ListValue!");
			return null;
		}
	}

	 /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

	public static void main(String[] args) {
		redis = RedisManger.getJedis();
	//	redis.set("test", "testtest");
		System.out.println(RedisManger.getValue("reg:phone:","13812344321"));
//		RedisManger t1 = new RedisManger();
//		List<String> dd = new ArrayList<String>();
//		for(int i=0;i<5;i++){
//			dd.add("hanzhen"+i);
//		}
//		
//		t1.setListValue("name", dd);
//		System.out.println(t1.getListValues("name"));
//		System.out.println(t1.getListValues("name").get(1));   
	}
}

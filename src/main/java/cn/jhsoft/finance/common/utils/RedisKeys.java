package cn.jhsoft.finance.common.utils;

/**
 * Redis所有Keys
 *
 * @author jhsoft
 * @email hn1987@126.com
 * @date 2017-07-18 19:51
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}

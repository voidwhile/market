package com.voidwhile.core.cache.ehcache;

/**
 * 
 * @Description: 表明指定name在缓存配置文件中不存在
 * 
 * @author xiaowei
 */
public class UnExistsedCacheNameException extends RuntimeException {
    public UnExistsedCacheNameException() {
        super();
    }

    public UnExistsedCacheNameException(String message) {
        super(message);
    }

    public UnExistsedCacheNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnExistsedCacheNameException(Throwable cause) {
        super(cause);
    }
}

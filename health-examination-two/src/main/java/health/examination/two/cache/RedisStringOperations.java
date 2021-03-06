package health.examination.two.cache;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.Charset;

/**
 * 字符串存储
 */
public class RedisStringOperations {
    private static final Charset CHARSET = Charset.forName("UTF8");
    private RedisTemplate<String, Object> redisTemplate;

    public RedisStringOperations(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String get(String key) {
        byte[] result = (byte[])this.redisTemplate.execute((RedisCallback<byte[]>) (connection) -> {
            return connection.get(key.getBytes());
        });
        return result == null ? null : new String(result, CHARSET);
    }

    public <T> void set(String key, String str) {
        byte[] value = str.getBytes(CHARSET);
        this.redisTemplate.execute((RedisCallback<Object>) (connection) -> {
            connection.set(key.getBytes(), value);
            return null;
        });
    }

    public void setWithExpire(String key, String str, long expireTime) {
        byte[] value = str.getBytes(CHARSET);
        this.redisTemplate.execute((RedisCallback<Object>) (connection) -> {
            connection.setEx(key.getBytes(), expireTime, value);
            return null;
        });
    }

    public String getAndSet(String key, String str) {
        byte[] value = str.getBytes(CHARSET);
        byte[] result = (byte[])this.redisTemplate.execute((RedisCallback<byte[]>) (connection) -> {
            return connection.getSet(key.getBytes(CHARSET), value);
        });
        return result == null ? null : new String(result, CHARSET);
    }
}


package error.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/redis")
@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/set")
    public String set(String key, String value) throws Exception {
        stringRedisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @RequestMapping("/get")
    public String get(String key) throws Exception {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @RequestMapping("/delete")
    public String delete(String key) throws Exception {
        Boolean l = stringRedisTemplate.delete(key);
        if (l) {
            return "success";
        } else {
            return "fail";
        }
    }

}

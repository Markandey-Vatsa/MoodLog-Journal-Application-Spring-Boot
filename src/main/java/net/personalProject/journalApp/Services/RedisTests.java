package net.personalProject.journalApp.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    void testSetMail(){
        redisTemplate.opsForValue().set("email","mymail@email.com");
        Object email = redisTemplate.opsForValue().get("email");

    }


}

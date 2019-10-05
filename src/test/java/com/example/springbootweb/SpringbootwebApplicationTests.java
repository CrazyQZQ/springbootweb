package com.example.springbootweb;

import com.example.springbootweb.bean.User;
import com.example.springbootweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootwebApplicationTests {
    @Autowired
    UserService us;
    @Test
    public void contextLoads() {
        List<User> allUsers = us.getAllUsers();
        allUsers.forEach(user -> {
            log.info(user.toString());
        });
    }

}

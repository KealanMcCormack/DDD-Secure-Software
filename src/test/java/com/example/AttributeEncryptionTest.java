package com.example;

import com.example.model.User;
import com.example.repository.UsersRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class AttributeEncryptionTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributeEncryptionTest.class);

    private static final String NAME = "Gerard";
    private static final String EMAIL = "gerard@example.com";
    private static final String PPS = "748329";

    private String id;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @Before("")
    public void setUp(){
        User user = new User();
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPPS(PPS);
        id = PPS;
    }

    @Test
    public void readDecrypted() {
        User user = usersRepository.findById(id).orElseThrow();

        Assertions.assertEquals(user.getName(), NAME);
        Assertions.assertEquals(user.getEmail(), EMAIL);
        Assertions.assertEquals(user.getPPS(), PPS);
    }

    @Test
    public void readEncrypted() {

        User user = usersRepository.getById(PPS);

        Assertions.assertNotEquals(user.getName(), NAME);
        LOGGER.info("Encrypted name value in DB is {}", user.getName());
        Assertions.assertNotEquals(user.getEmail(), EMAIL);
        LOGGER.info("Encrypted email value in DB is {}", user.getEmail());
    }

}

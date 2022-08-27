package com.wj.resttasks.repositories;

import com.wj.resttasks.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userTest;

    @BeforeEach
    void setUp(){
        User user = new User("testusername", "testpass");
        userTest.save(user);
    }

    @AfterEach
    void afterEach(){
        userTest.deleteAll();
    }

    @Test
    void shouldFindUserByUsername() {
        assertThat(userTest.findByUsername("testusername")).isInstanceOf(Optional.class);
    }

}
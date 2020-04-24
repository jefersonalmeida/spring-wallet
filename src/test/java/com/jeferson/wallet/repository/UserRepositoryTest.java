package com.jeferson.wallet.repository;

import com.jeferson.wallet.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class UserRepositoryTest {

    private static final String EMAIL = "email@teste.com";

    @Autowired
    UserRepository repository;

    @Before
    public void setUp() {
        User u = new User();
        u.setName("Usuário Teste");
        u.setEmail(EMAIL);
        u.setPassword("123456");
        repository.save(u);
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testSave() {
        User u = new User();
        u.setName("Usuário Teste");
        u.setEmail("teste@teste.com");
        u.setPassword("123456");

        User response = repository.save(u);

        assertNotNull(response);
    }

    @Test
    public void testFindByEmail() {
        Optional<User> response = repository.findByEmailEquals(EMAIL);
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }
}

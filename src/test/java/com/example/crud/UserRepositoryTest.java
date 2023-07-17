package com.example.crud;

import com.example.crud.user.User;
import com.example.crud.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Collection;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("piroj.pa@ku.th");
        user.setPassword("9999");
        user.setFirstName("Piroj");
        user.setLastName("Puu");

        User savedUser = repo.save(user);

        Assertions.assertNotNull(user);
        Assertions.assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();

        Assertions.assertTrue(((Collection<User>) users).size() > 0);

        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUser() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hnoonnahoh");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertTrue(updatedUser.getPassword().equals("hnoonnahoh"));
     }

     @Test
    public void testGet() {
         Integer userId = 2;
         Optional<User> optionalUser = repo.findById(userId);
         Assertions.assertTrue(optionalUser.isPresent());
         System.out.println(optionalUser.get());
     }

     @Test
    public void delete() {
        Integer userId = 4;
        repo.deleteById(userId);

         Optional<User> optionalUser = repo.findById(userId);
         Assertions.assertTrue(optionalUser.isEmpty());
     }
}

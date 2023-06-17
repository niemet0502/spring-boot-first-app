package com.groupeisi.m2gl.mariusdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserControllerTests {
    private UserController userController;
    private List<User> users;



    @BeforeEach
    public void setup() {
        users = new ArrayList<User>();
        users.add(new User(1, "John", "Doe", 25));
        users.add(new User(2, "Jane", "Smith", 30));
        users.add(new User(3, "Mike", "Johnson", 35));

        userController = new UserController(users);
    }

    @Test
    public void getAllUsers_ShouldReturnAllUsers() {
        List<User> result = userController.getAllUsers();
        assertEquals(users, result);
    }

    @Test
    public void getUserById_ExistingId_ShouldReturnUser() {
        User result = userController.getUserById(2);
        assertEquals(users.get(1), result);
    }

    @Test
    public void getUserById_NonExistingId_ShouldReturnNull() {
        User result = userController.getUserById(5);
        assertNull(result);
    }

    @Test
    public void createUser_ShouldAddUserToList() {
        User newUser = new User(4, "Emily", "Anderson", 28);
        User result = userController.createUser(newUser);

        assertEquals(newUser, result);
        assertEquals(4, users.size());
        assertTrue(users.contains(newUser));
    }


    @Test
    public void deleteUser_ExistingId_ShouldRemoveUser() {
        boolean result = userController.deleteUser(2);

        assertTrue(result);
        assertEquals(2, users.size());
        assertFalse(users.stream().anyMatch(user -> user.getId() == 2));
    }

    @Test
    public void deleteUser_NonExistingId_ShouldReturnFalse() {
        boolean result = userController.deleteUser(5);
        assertFalse(result);
    }
}

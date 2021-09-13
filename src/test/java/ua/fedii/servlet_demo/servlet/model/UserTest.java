package ua.fedii.servlet_demo.servlet.model;

import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

public class UserTest {
    private static User user;

    @BeforeAll
    public static void userInitialization() {
        user = new User("Al", "Pacino", 40, "mail@mail.ua");
    }

    @Test
    public void getFirstName() {
        String expectedFirstName = "Al";
        String actualFirstName = user.getFirstName();
        Assertions.assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    public void getSecondName() {
        String expectedSecondName = "Pacino";
        String actualSecondName = user.getSecondName();
        Assertions.assertEquals(expectedSecondName, actualSecondName);
    }

    @Test
    public void getAge() {
        int expectedAge = 40;
        int actualAge = user.getAge();
        Assertions.assertEquals(expectedAge, actualAge);
    }

    @Test
    public void getEmail() {
        String expectedEmail = "mail@mail.ua";
        String actualEmail = user.getEmail();
        Assertions.assertEquals(expectedEmail, actualEmail);
    }

    @AfterAll
    public static void clearUserState()
    {
        user = null;
    }
}
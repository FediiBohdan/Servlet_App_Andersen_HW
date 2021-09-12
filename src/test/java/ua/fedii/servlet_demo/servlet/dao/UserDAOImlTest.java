package ua.fedii.servlet_demo.servlet.dao;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.fedii.servlet_demo.servlet.email_exception.EmailException;
import ua.fedii.servlet_demo.servlet.model.User;

public class UserDAOImlTest {
    UserDAOIml userDAO = new UserDAOIml();

    @Test
    public void selectAllUsers() {
        int actualUserAmount = userDAO.selectAllUsers().size();
        int expectedUserAmount = 4;

        Assertions.assertEquals(expectedUserAmount, actualUserAmount);
    }

    @Test
    public void insertUser() {
        int initialUserListSize = userDAO.selectAllUsers().size();
        userDAO.insertUser(new User("Taras", "Stepanenko", 25, "taras_s@mail.ua"));
        int currentUserListSize = initialUserListSize + 1;

        Assertions.assertEquals(5, currentUserListSize);
    }

    @Test
    public void selectUser() {
        User actualUser = userDAO.selectUser(20);
        User expectedUser = new User(20, "Linda", "Gregor", 43, "lind@mail.net");

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Ignore
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
        User expectedUpdatedUser = new User(21, "Mike", "Owl", 76, "j_buyer@gmail.com");
        userDAO.updateUser(expectedUpdatedUser);
        User actualUpdatedUser = userDAO.selectUser(21);

        Assertions.assertEquals(expectedUpdatedUser, actualUpdatedUser);
    }

    @Test()
    public void checkIncorrectEmail() {
        String incorrectEmail = "incorrectEmail";

        Assertions.assertThrows(EmailException.class, () -> userDAO.checkEmail(incorrectEmail));
    }

    @Test()
    public void checkCorrectEmail() {
        String correctEmail = "correctEmail@ukr.net";

        Assertions.assertDoesNotThrow(() -> userDAO.checkEmail(correctEmail));
    }

}
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
        int expectedUserAmount = 5;

        Assertions.assertEquals(expectedUserAmount, actualUserAmount);
    }

    @Test
    public void insertUser() {
        int initialUserListSize = userDAO.selectAllUsers().size();
        userDAO.insertUser(new User("Taras", "Stepanenko", 25, "taras_s@mail.ua"));
        int currentUserListSize = initialUserListSize + 1;

        Assertions.assertEquals(6, currentUserListSize);
    }

    @Test
    public void selectUser() {
        User actualUser = userDAO.selectUser(3);
        User expectedUser = new User(3, "Linda", "Gregor", 43, "lind@mail.net");

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Ignore
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
        User expectedUpdatedUser = new User(4, "Mike", "Owl", 76, "owwwwl_mike@gmail.co");
        userDAO.updateUser(expectedUpdatedUser);
        User actualUpdatedUser = userDAO.selectUser(4);

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
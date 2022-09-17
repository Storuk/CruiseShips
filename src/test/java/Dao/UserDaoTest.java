package Dao;

import Entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao = new UserDao();
    @Test
    void validate() throws ClassNotFoundException {
        int id = 1;
        String username = "storuk";
        String firstname = "Vlad";
        String lastname = "Storoshchuk";
        String email = "vladstoroschuk@gmail.com";
        String password = "A434C52C393045E9AC064875AA112E07EEBB72A25E3D9126A4514D88FCCBB0B0";
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(password);
        assertTrue(user.equals(userDao.validate(user.getUsername(),user.getPassword())));
    }

    @Test
    void authenticateUser() throws ClassNotFoundException {
        String adminRole = "Admin_Role";
        String userRole = "User_Role";
        User user = new User();
        user.setUsername("storuk");
        user.setPassword("A434C52C393045E9AC064875AA112E07EEBB72A25E3D9126A4514D88FCCBB0B0");
        assertTrue(adminRole.equals(UserDao.authenticateUser(user)));

        user.setUsername("vlados");
        user.setPassword("33417ABD8AB740F098A1718DB8B5DF707BDFD1243E906CA70CEFDACE312BCF27");
        assertTrue(userRole.equals(UserDao.authenticateUser(user)));
    }

    @Test
    void checkName() throws ClassNotFoundException {
        User user = new User();

        user.setUsername("storuk");
        assertTrue(UserDao.checkName(user));

        user.setUsername("vlad");
        assertFalse(UserDao.checkName(user));
    }
}
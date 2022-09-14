package controller.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptTest {

    @Test
    void hashPasswordTestCorrect() {
        String password = "12345678";
        assertEquals("33417ABD8AB740F098A1718DB8B5DF707BDFD1243E906CA70CEFDACE312BCF27",PasswordEncrypt.hashPassword(password));
    }

    @Test
    void hashPasswordTestNotCorrect() {
        String password = "1234567";
        assertNotEquals("33417ABD8AB740F098A1718DB8B5DF707BDFD1243E906CA70CEFDACE312BCF27",PasswordEncrypt.hashPassword(password));
    }

    @Test
    void ConvertImageNameTestCorrect() {
        String photoName = "photo.jpg";
        assertEquals(".jpg",PasswordEncrypt.ConvertImage(photoName));
    }

    @Test
    void ConvertImageNameTestNotCorrecr() {
        String photoName = "photo.png";
        assertNotEquals(".jpg",PasswordEncrypt.ConvertImage(photoName));
    }
}
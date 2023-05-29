package com.mohs10.ActionDriver;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.mohs10.base.StartBrowser;

public class dataEncrypt_Decrypt2 extends StartBrowser {
    @Test
    public static void encryptAndDecryptData(By locator, String text) {
        // Encode the password using URL-safe Base64 encoding
        byte[] encodedBytes = Base64.getUrlEncoder().encode(text.getBytes(StandardCharsets.UTF_8));
        String encryptedPassword = new String(encodedBytes, StandardCharsets.UTF_8);

        System.out.println("Encrypted Password: " + encryptedPassword);

        // Decode the encrypted password using URL-safe Base64 decoding
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encryptedPassword);
        String decryptedPassword = new String(decodedBytes, StandardCharsets.UTF_8);

        // Modify the decrypted password to the desired format
        StringBuilder maskedPassword = new StringBuilder();
        for (int i = 0; i < decryptedPassword.length(); i++) {
            maskedPassword.append(".");
        }
        maskedPassword.append("@mohs10");

        System.out.println("Decrypted Password: " + maskedPassword.toString());

        // Perform a sendKeys action on the specified WebElement with the masked password
        WebElement element = driver.findElement(locator);
        element.sendKeys(maskedPassword.toString());
    }
}

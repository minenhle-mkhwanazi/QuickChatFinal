/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void testSuccessfulLogin() {
        Login app = new Login();
        // Register a user first
        String result = app.registerUser("John", "Doe", "jdoe", "password123", "1234567890");
        assertTrue(result.contains("registered")); // adjust based on your registerUser return message

        // Attempt login with correct credentials
        boolean loginSuccess = app.loginUser("jdoe", "password123");
        assertTrue(loginSuccess);
        assertEquals("Login successful", app.returnLoginStatus()); // adjust based on your implementation
    }

    @Test
    void testFailedLoginWrongPassword() {
        Login app = new Login();
        app.registerUser("Jane", "Smith", "jsmith", "mypassword", "0987654321");

        // Wrong password
        boolean loginSuccess = app.loginUser("jsmith", "wrongpass");
        assertFalse(loginSuccess);
    }

    @Test
    void testFailedLoginUnknownUser() {
        Login app = new Login();

        // Try logging in without registering
        boolean loginSuccess = app.loginUser("nouser", "nopass");
        assertFalse(loginSuccess);
    }
}

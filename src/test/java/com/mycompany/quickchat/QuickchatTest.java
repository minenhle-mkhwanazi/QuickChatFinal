/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;

import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class QuickChatTest {

    @Test
    void testExitImmediately() throws Exception {
        // Simulate user typing "3" (Exit) and pressing Enter
        String simulatedInput = "3\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);

        // Redirect System.in and System.out
        System.setIn(in);
        System.setOut(ps);

        // Run main
        QuickChat.main(new String[]{});

        // Capture output
        String output = out.toString();

        // Verify that "Goodbye!" message appears
        assertTrue(output.contains("Goodbye!"));
    }

    @Test
    void testRegisterUserAndExit() throws Exception {
        // Simulate choosing option 1 (Register User), entering details, then exiting
        String simulatedInput =
                "1\n" +               // choose Register User
                "John\n" +            // first name
                "Doe\n" +             // last name
                "jdoe\n" +            // username
                "password123\n" +     // password
                "1234567890\n" +      // cell number
                "3\n";                // exit

        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);

        System.setIn(in);
        System.setOut(ps);

        QuickChat.main(new String[]{});

        String output = out.toString();

        // Check that registration message and Goodbye appear
        assertTrue(output.contains("registered")); // adjust based on your Login.registerUser return
        assertTrue(output.contains("Goodbye!"));
    }
}

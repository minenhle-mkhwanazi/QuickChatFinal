/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testSingleMessageJsonObject() {
        Message msg = new Message("123", "Alice", "Hello World", 5);
        JSONObject json = msg.toJson();

        assertEquals("123", json.get("messageID"));
        assertEquals("Alice", json.get("recipient"));
        assertEquals("Hello World", json.get("messageText"));
        assertEquals(5, json.get("numSent"));
        assertEquals(msg.generateHash(), json.get("hash"));
        assertEquals(false, json.get("tooLong"));
        assertEquals(250, json.get("maxTextLength"));
        assertEquals(100, json.get("maxSent"));
    }

    @Test
    void testJsonArrayOfMessages() {
        Message msg1 = new Message("1", "Bob", "Hi Bob", 2);
        Message msg2 = new Message("2", "Carol", "Hi Carol", 3);

        JSONArray array = Message.toJsonArray(new Message[]{msg1, msg2});

        assertEquals(2, array.size());

        JSONObject first = (JSONObject) array.get(0);
        assertEquals("Bob", first.get("recipient"));

        JSONObject second = (JSONObject) array.get(1);
        assertEquals("Carol", second.get("recipient"));
    }

    @Test
    void testTooLongMessage() {
        String longText = "x".repeat(300);
        Message msg = new Message("999", "Bob", longText, 1);
        assertTrue(msg.isTooLong());
        assertEquals("Message too long!", msg.sendMessage());
    }

    @Test
    void testMaxSendsReached() {
        Message msg = new Message("888", "Carol", "Hi Carol", 100);
        assertEquals("Maximum sends reached!", msg.sendMessage());
    }
}

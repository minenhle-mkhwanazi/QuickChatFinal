
package com.mycompany.quickchat;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

class Message {
    private static final int MAX_TEXT_LENGTH = 250;
    private static final int MAX_SENT = 100;

    String messageID;
    String recipient;
    String messageText;
    int numSent;

    public Message(String id, String rec, String text, int num) {
        this.messageID = id;
        this.recipient = rec;
        this.messageText = text;
        this.numSent = num;
    }

    public String display() {
        return "Message ID: " + messageID +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText +
               "\nTotal Sent: " + numSent;
    }

    public String sendMessage() {
        if (isTooLong()) {
            return "Message too long!";
        }
        if (numSent >= MAX_SENT) {
            return "Maximum sends reached!";
        }
        numSent++;
        return "Message sent.";
    }

    public String generateHash() {
        return Integer.toHexString((messageID + messageText).hashCode());
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageText() {
        return messageText;
    }

    public boolean isTooLong() {
        return messageText.length() > MAX_TEXT_LENGTH;
    }

    // ✅ Convert to JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("messageID", messageID);
        json.put("recipient", recipient);
        json.put("messageText", messageText);
        json.put("numSent", numSent);
        json.put("hash", generateHash());
        json.put("tooLong", isTooLong());
        json.put("maxTextLength", MAX_TEXT_LENGTH);
        json.put("maxSent", MAX_SENT);
        return json;
    }

    // ✅ Convert multiple messages to JSONArray
    public static JSONArray toJsonArray(Message[] messages) {
        JSONArray array = new JSONArray();
        for (Message msg : messages) {
            array.add(msg.toJson());
        }
        return array;
    }
}



package com.mycompany.quickchat;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QuickChat {
    // NEW ARRAYS FOR PART 3 
    static String[] sentMessages = new String[20];
    static String[] disregardedMessages = new String[20];
    static String[] storedMessages = new String[20];
    static String[] messageHashes = new String[20];
    static String[] messageIDs = new String[20];

    static int sentCount = 0, disregardedCount = 0, storedCount = 0, hashCount = 0, idCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login app = new Login();

        int choice;

        // OUTER LOOP: Main menu
        do {
            System.out.println("\n===== QUICKCHAT MENU =====");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("4. Stored Messages"); // New option for part 3
            System.out.println("5. Export JSON Report"); // ✅ New option

            System.out.print("Choose option: ");
            choice = input.nextInt();
            input.nextLine();

            switch(choice) {
                case 1:
                    // Registration
                    System.out.print("Enter first name: ");
                    String fName = input.nextLine();

                    System.out.print("Enter last name: ");
                    String lName = input.nextLine();

                    System.out.print("Enter username: ");
                    String username = input.nextLine();

                    System.out.print("Enter password: ");
                    String password = input.nextLine();

                    System.out.print("Enter cell number: ");
                    String cell = input.nextLine();

                    System.out.println(app.registerUser(fName, lName, username, password, cell));
                    break;

                case 2:
                    // Login
                    System.out.print("Enter username: ");
                    String u = input.nextLine();

                    System.out.print("Enter password: ");
                    String p = input.nextLine();

                    if(app.loginUser(u, p)) {
                        System.out.println(app.returnLoginStatus());

                        // INNER LOOP: Message menu
                        int msgChoice;
                        do {
                            System.out.println("\n===== MESSAGE MENU =====");
                            System.out.println("1. Send Message");
                            System.out.println("2. Show Recent Messages");
                            System.out.println("3. Quit Messages");

                            msgChoice = input.nextInt();
                            input.nextLine();

                            switch(msgChoice) {
                                case 1:
                                    System.out.print("Enter message ID: "); 
                                    String id = input.nextLine();

                                    System.out.print("Enter recipient number: ");
                                    String recipient = input.nextLine();

                                    System.out.print("Enter message: ");
                                    String text = input.nextLine();

                                    Message msg = new Message(id, recipient, text, 1);

                                    // === STORE INTO ARRAYS ===
                                    String hash = msg.generateHash();
                                    messageHashes[hashCount++] = hash;
                                    messageIDs[idCount++] = msg.getMessageID();

                                    if(msg.isTooLong()) {
                                        disregardedMessages[disregardedCount++] = msg.getMessageText();
                                        System.out.println("Message disregarded (too long).");
                                    } else {
                                        sentMessages[sentCount++] = msg.getMessageText();
                                        storedMessages[storedCount++] = "Recipient: " + msg.getRecipient() + " | Message: " + msg.getMessageText();
                                        System.out.println(msg.sendMessage());
                                    }
                                    break;

                                case 2:
                                    System.out.println("Coming Soon.");
                                    break;

                                case 3:
                                    System.out.println("Exiting message menu...");
                                    break;

                                default:
                                    System.out.println("Invalid option.");
                            }
                        } while(msgChoice!= 3);

                    } else {
                        System.out.println("Username or password incorrect.");
                    }
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                case 4:
                    // STORED MESSAGES MENU
                    int storedChoice;
                    do {
                        System.out.println("\n===== STORED MESSAGES MENU =====");
                        System.out.println("1. Display sender & recipient");
                        System.out.println("2. Display longest message");
                        System.out.println("3. Search by message ID");
                        System.out.println("4. Search by recipient");
                        System.out.println("5. Delete by hash");
                        System.out.println("6. Display full report");
                        System.out.println("7. Exit Stored Menu");

                        storedChoice = input.nextInt();
                        input.nextLine();

                        switch(storedChoice) {
                            case 1:
                                for(int i=0; i<storedCount; i++) {
                                    System.out.println(storedMessages[i]);
                                }
                                break;

                            case 2:
                                String longest = "";
                                for(int i=0; i<storedCount; i++) {
                                    if(storedMessages[i].length() > longest.length()) {
                                        longest = storedMessages[i];
                                    }
                                }
                                System.out.println("Longest message: " + longest);
                                break;

                            case 3:
                                System.out.print("Enter Message ID: ");
                                String searchID = input.nextLine();
                                for(int i=0; i<idCount; i++) {
                                    if(messageIDs[i].equals(searchID)) {
                                        System.out.println("Message ID: " + messageIDs[i] + " | Recipient & Message: " + storedMessages[i]);
                                    }
                                }
                                break;

                            case 4:
                                System.out.print("Enter Recipient: ");
                                String rec = input.nextLine();
                                for(int i=0; i<storedCount; i++) {
                                    if(storedMessages[i].contains(rec)) {
                                        System.out.println(storedMessages[i]);
                                    }
                                }
                                break;

                            case 5:
                                System.out.print("Enter Hash to delete: ");
                                String hashDel = input.nextLine();
                                for(int i=0; i<hashCount; i++) {
                                    if(messageHashes[i].equals(hashDel)) {
                                        System.out.println("Message deleted: " + storedMessages[i]);
                                        storedMessages[i] = "";
                                    }
                                }
                                break;

                            case 6:
                                System.out.println("===== REPORT =====");
                                for(int i=0; i<storedCount; i++) {
                                    System.out.println("Hash: " + messageHashes[i] +
                                                       " | Recipient & Message: " + storedMessages[i] +
                                                       " | Message ID: " + messageIDs[i]);
                                }
                                break;

                            case 7:
                                System.out.println("Exiting Stored Menu...");
                                break;
                        }
                    } while(storedChoice!= 7);
                    break;

                case 5:
                    // ✅ Export JSON Report
                    JSONArray jsonArray = new JSONArray();
                    for(int i=0; i<storedCount; i++) {
                        JSONObject obj = new JSONObject();
                        obj.put("hash", messageHashes[i]);
                        obj.put("messageID", messageIDs[i]);
                        obj.put("storedMessage", storedMessages[i]);
                        jsonArray.add(obj);
                    }
                    System.out.println("===== JSON REPORT =====");
                    System.out.println(jsonArray.toJSONString());
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        } while(choice!= 3);

        input.close();
    }
}

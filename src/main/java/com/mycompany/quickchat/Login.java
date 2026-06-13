
package com.mycompany.quickchat;


class Login {
     private String storedUsername;
    private String storedPassword;
    private String storedCell;
    private String firstName;
    private String lastName;

    public Login() {} 

    // Register user
    public String registerUser(String fName, String lName, String username, String password, String cell) {
        this.firstName = fName;
        this.lastName = lName;
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCell = cell;

        // basic validation
        if(!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure it contains an underscore and is no more than 5 characters.";
        }
        if(!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; must be 8+ chars, contain a capital, number, and special character.";
        }
        if(!checkCellPhoneNumber(cell)) {
            return "Cell number incorrectly formatted; must start with +27 and be at least 11 characters.";
        }
        return "User registered successfully.";
    }

    // Validation helpers
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean longEnough = password.length() >= 8;
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        return longEnough && hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cell) { 
        return cell.startsWith("+27") && cell.length() >= 11;
    }

    // Login check
    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // Welcome message
    public String returnLoginStatus() {
        return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
    }
}



package com.example.assignment1;
public class User {
    private int userID;
    private String userName;
    private String userLname;
    private String userEmail;
    private String userDob;
    private String userGender;
    private String userPassword;
    public User() {}
    public User(int id, String username, String userlname, String useremail, String userdob, String gender, String password) {
        this.userID = id;
        this.userName = username;
        this.userLname = userlname;
        this.userEmail = useremail;
        this.userDob = userdob;
        this.userGender = gender;
        this.userPassword = password;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserLname() {
        return userLname;
    }
    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserDob() {
        return userDob;
    }
    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }
    public String getUserGender() {
        return userGender;
    }
    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
}
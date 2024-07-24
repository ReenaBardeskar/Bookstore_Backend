package com.example.bookStoreWebApp.model;
import jakarta.persistence.*;


@Entity
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "Account_Status_Id")
    private Integer accountStatusId;

    @Column(name = "Account_Type_Id")
    private Integer accountTypeId;


    @Column(name = "Mobile_Number")
    private String mobileNumber;


    @Column(name = "subscribe_to_promo", columnDefinition = "BIT")
    private boolean subscribeToPromo;

    @Column(name = "user_name")
    private String username;

    // Constructors, getters, and setters

    public Users() {
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(Integer accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public boolean isSubscribeToPromo() {
        return subscribeToPromo;
    }

    public void setSubscribeToPromo(boolean subscribeToPromo) {
        this.subscribeToPromo = subscribeToPromo;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
	

}

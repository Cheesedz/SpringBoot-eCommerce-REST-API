package com.Cheesedz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String dob;
    private Cart cart;
    public User() {

    }

    public User(String username, String name, String email, String phone, String gender, String dob, Cart cart) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.cart = cart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User[username=" + username + ","
                + "name=" + name + ","
                + "email=" + email + ","
                + "phone=" + phone + ","
                + "gender=" + gender + ","
                + "cart=" + cart + ","
                + "dob=" + dob + "]";
    }
}

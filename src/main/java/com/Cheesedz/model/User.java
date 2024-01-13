package com.Cheesedz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user",  uniqueConstraints = { @UniqueConstraint(columnNames = { "id"/*, "username"*/ })})
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private String dob;

    @Column(name = "cart")
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voucher> voucherList;

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

    public List<Voucher> getProductList() {
        return this.voucherList == null ? null : new ArrayList<>(this.voucherList);
    }

    public void setProductList(List<Voucher> voucherList) {
        if (voucherList == null) {
            this.voucherList = null;
        } else {
            this.voucherList = Collections.unmodifiableList(voucherList);
        }
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

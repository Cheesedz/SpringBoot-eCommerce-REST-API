package com.Cheesedz.payload;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String dob;
    private String password;
    private Long roleID;
}

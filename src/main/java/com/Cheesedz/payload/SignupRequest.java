package com.Cheesedz.payload;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
    @NotNull
    @Size(min = 4, max = 40)
    private String name;

    @NotNull
    @Size(min = 3, max = 15)
    private String username;

    @NotNull
    @Size(max = 40)
    @Email
    private String email;

    @NotNull
    @Size(max = 6)
    private String gender;

    @NotNull
    @Size(max = 10)
    private String dob;

    @NotNull
    @Size(min = 10, max = 11)
    private String phone;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    private Long roleID;
}

package com.Cheesedz.payload;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignUpRequest {
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
}

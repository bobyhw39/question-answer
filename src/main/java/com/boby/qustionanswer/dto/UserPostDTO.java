package com.boby.qustionanswer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDTO {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}

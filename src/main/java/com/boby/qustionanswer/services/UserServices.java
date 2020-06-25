package com.boby.qustionanswer.services;

import com.boby.qustionanswer.dto.UserGetDTO;
import com.boby.qustionanswer.dto.UserPostDTO;
import com.boby.qustionanswer.dto.UserPostLogin;
import com.boby.qustionanswer.exceptions.ErrorDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    UserGetDTO findByUsername(String username);
    void saveUser(UserPostDTO userPostDTO);
    ErrorDetails loginUser(UserPostLogin userPostLogin);
}

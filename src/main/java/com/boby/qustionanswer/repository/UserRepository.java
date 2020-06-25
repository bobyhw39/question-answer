package com.boby.qustionanswer.repository;

import com.boby.qustionanswer.dto.UserGetDTO;
import com.boby.qustionanswer.dto.UserPostDTO;
import com.boby.qustionanswer.dto.UserPostLogin;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    UserGetDTO findByUsername(String username);
    void saveUser(UserPostDTO userPostDTO);
    UserPostLogin loginUser(UserPostLogin userPostLogin);

}

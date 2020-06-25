package com.boby.qustionanswer.services.impl;

import com.boby.qustionanswer.dto.UserGetDTO;
import com.boby.qustionanswer.dto.UserPostDTO;
import com.boby.qustionanswer.dto.UserPostLogin;
import com.boby.qustionanswer.exceptions.BadRequestException;
import com.boby.qustionanswer.exceptions.ErrorDetails;
import com.boby.qustionanswer.exceptions.NotFoundException;
import com.boby.qustionanswer.repository.UserRepository;
import com.boby.qustionanswer.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserGetDTO findByUsername(String username) {
        try{
            return userRepository.findByUsername(username);
        }
        catch (EmptyResultDataAccessException x){
            throw new NotFoundException(username + "not found");
        }
    }

    @Override
    public void saveUser(UserPostDTO userPostDTO) {
        UserGetDTO user;
        if(userPostDTO.getPassword().equals(userPostDTO.getConfirmPassword())){
            try {
                user = userRepository.findByUsername(userPostDTO.getUsername());
                throw new BadRequestException("user " + userPostDTO.getUsername() + " already exist");
            }
            catch (EmptyResultDataAccessException x){
                userRepository.saveUser(userPostDTO);
            }
        }
        else throw new BadRequestException("Password does not match");
    }

    @Override
    public ErrorDetails loginUser(UserPostLogin userPostLogin) {
        try{
            UserPostLogin userPostLogin1 = userRepository.loginUser(userPostLogin);
            if(userPostLogin1.equals(userPostLogin)){
                return new ErrorDetails(new Date(System.currentTimeMillis()), " Login " + userPostLogin.getUsername() + " success", "200", "/user/login");
            }
            else throw new BadRequestException("Username and Password Wrong");
        }
        catch (EmptyResultDataAccessException x){
            throw new BadRequestException("Username and Password Wrong");
        }

    }
}

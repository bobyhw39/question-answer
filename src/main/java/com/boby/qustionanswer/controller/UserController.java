package com.boby.qustionanswer.controller;

import com.boby.qustionanswer.dto.UserGetDTO;
import com.boby.qustionanswer.dto.UserPostDTO;
import com.boby.qustionanswer.dto.UserPostLogin;
import com.boby.qustionanswer.exceptions.ErrorDetails;
import com.boby.qustionanswer.services.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@Api(value = "Question Answer applications")
@RequestMapping("user")
@Validated
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping("")
    @ApiOperation(value = "show user info", response = UserGetDTO.class)
    public UserGetDTO showUserInfo(@RequestParam String username){
        return userServices.findByUsername(username);
    }

    @PostMapping("")
    @ApiOperation(value = "Create New User", response = ErrorDetails.class)
    public ErrorDetails saveUser(@RequestBody UserPostDTO userPostDTO){
        userServices.saveUser(userPostDTO);
        return new ErrorDetails(new Date(System.currentTimeMillis()), "create user " + userPostDTO.getUsername() + " success", "200", "/user");
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login user", response = ErrorDetails.class)
    public ErrorDetails loginUser(@RequestBody UserPostLogin userPostLogin){
        return userServices.loginUser(userPostLogin);
    }

}

package com.boby.qustionanswer.repository.dao;

import com.boby.qustionanswer.dto.UserGetDTO;
import com.boby.qustionanswer.dto.UserPostDTO;
import com.boby.qustionanswer.dto.UserPostLogin;
import com.boby.qustionanswer.entity.User;
import com.boby.qustionanswer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserGetDTO findByUsername(String username) {
        return jdbcTemplate.queryForObject(
                "select * from tbl_user where username=?",
                new Object[]{username},
                (rs, rowNum) -> new UserGetDTO(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("username")
                )
        );
    }

    @Override
    public void saveUser(UserPostDTO userPostDTO) {
        jdbcTemplate.update(
                "insert into tbl_user (email,username,password) values (?,?,?) ",
                userPostDTO.getEmail(),userPostDTO.getUsername(),userPostDTO.getPassword()
        );
    }

    @Override
    public UserPostLogin loginUser(UserPostLogin userPostLogin) {
        return jdbcTemplate.queryForObject(
                "select * from tbl_user where username=? and password=?",
                new Object[]{userPostLogin.getUsername(),userPostLogin.getPassword()},
                (rs, rowNum) -> new UserPostLogin(
                        rs.getString("username"),
                        rs.getString("password")
                )
        );
    }
}

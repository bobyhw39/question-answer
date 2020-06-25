package com.boby.qustionanswer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.boby.qustionanswer"})
@EntityScan("com.boby.qustionanswer")
public class QustionanswerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QustionanswerApplication.class, args);
    }

}

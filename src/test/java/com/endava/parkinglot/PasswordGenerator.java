package com.endava.parkinglot;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {



    @Test
    void generatePassword(){
        BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
        String rawPass="endava";
        String encodedPass= encoder.encode(rawPass);
        System.out.println(encodedPass);
        System.out.println(String.format("asd %X", 3));

    }


}

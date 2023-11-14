package com.flotte.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flotte.security.JwtTokenUtil;

@Component
public class Authentification {

    @Autowired
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    public String checkValidateToken(String authorizationHeader)throws Exception
    {
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                String profile = jwtTokenUtil.validateToken(token);
                if(profile == null)throw new Exception("Please enter valid token or sign in to get new token");
                return profile;
            } else {
                throw new Exception("Invalid or missing Bearer token");
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }
    
}

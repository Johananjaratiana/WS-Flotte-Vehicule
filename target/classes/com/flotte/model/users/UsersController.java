package com.flotte.model.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;


import com.flotte.security.JwtTokenUtil;
import com.flotte.security.token.Token;
import com.flotte.ws.MyResponse;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @CrossOrigin
    @PostMapping("/login")
    public MyResponse login(@RequestBody Users loginRequest) throws Exception{
        MyResponse response = new MyResponse();
        try
        {
            Users user = Users.VerifyExistingUsers(loginRequest.getNom(), loginRequest.getMdp());
            if (user != null) 
            {
                String token = jwtTokenUtil.generateToken(user.getId());
                response.setData(token);
            }
            else 
            {
                response.addError("login","Invalid username or password");
            }
        }catch(Exception ex){
            response.addError("login", ex.getMessage());
        }
        return response;
    }

    @GetMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7); // Extract the token excluding "Bearer "
                String profile = jwtTokenUtil.validateToken(token);
                if(profile == null)profile = "false";
                return ResponseEntity.ok(profile);
            } else {
                return ResponseEntity.status(401).body("Invalid or missing Bearer token");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(401).body("Error " + ex.getMessage());
        }
    }

    @GetMapping("/deconnection/{userId}")
    public ResponseEntity<?> deconnection(@PathVariable Integer userId) {
        try {
            Token.invalidateToken(userId);
            return ResponseEntity.ok(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(401).body("Error " + ex.getMessage());
        }
    }
    
}

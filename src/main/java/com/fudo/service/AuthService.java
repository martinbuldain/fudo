package com.fudo.service;

import com.fudo.security.Credential;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Credential credential;

    public AuthService(Credential credential) {
        this.credential = credential;
    }

    public boolean authenticate(String username, String password) {
        return credential.getUser().equals(username) && credential.getPassword().equals(password);
    }
}

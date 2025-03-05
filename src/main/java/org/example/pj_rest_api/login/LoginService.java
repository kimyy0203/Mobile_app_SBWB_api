package org.example.pj_rest_api.login;

import org.example.pj_rest_api.Jpa.JpaUserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    public boolean login(String username, String password) {
        Optional<JpaUserEntity> user = loginRepository.findByUserId(username);
        return user.map(u -> u.getUserPassword().equals(password)).orElse(false);
    }
}

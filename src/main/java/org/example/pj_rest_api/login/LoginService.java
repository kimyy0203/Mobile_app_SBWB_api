package org.example.pj_rest_api.login;

import jakarta.transaction.Transactional;
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
    @Transactional
    public boolean register(String username, String password, String name, String num) {
        if(loginRepository.findByUserId(username).isPresent())
            return false;
        else {
            JpaUserEntity user = new JpaUserEntity();
            user.setUserId(username);
            user.setUserPassword(password);
            user.setUserName(name);
            user.setUserNum(num);
            loginRepository.save(user);
            return true;
        }
    }
    @Transactional
    public boolean updatePassword(String username, String password, String newPassword) {
        Optional<JpaUserEntity> optionalUser = loginRepository.findByUserId(username);

        if (optionalUser.isEmpty()) {
            return false; // 사용자 존재하지 않음
        }

        JpaUserEntity user = optionalUser.get();
        if (!user.getUserPassword().equals(password)) {
            return false; // 기존 비밀번호 불일치
        }

        user.setUserPassword(newPassword); // 비밀번호 변경
        loginRepository.save(user);
        return true;
    }
}

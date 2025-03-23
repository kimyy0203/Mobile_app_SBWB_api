package org.example.pj_rest_api.login;

import org.example.pj_rest_api.security.SHA256;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.example.pj_rest_api.Jpa.JpaUserEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
@Service
public class LoginService {
    private final LoginRepository loginRepository;
    SHA256 sha256 = new SHA256();

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Transactional( readOnly = true )
    public boolean login(String username, String password) {
        String encryptedPw = sha256.encrypt(password);
        Optional<JpaUserEntity> user = loginRepository.findByUserId(username);
        return user.map(u -> u.getUserPassword().equals(encryptedPw)).orElse(false);
    }

    @Transactional
    public void register(String username, String password, String name, String num) {
        String encryptedPw = sha256.encrypt(password);
        try {
            JpaUserEntity user = new JpaUserEntity();
            if(loginRepository.existsById(username))//username으로 중복 검사 -> 중복 발생 시 throw
                throw new DataIntegrityViolationException("이미 존재하는 ID 입니다.");
            user.setUserId(username);
            user.setUserPassword(encryptedPw);
            user.setUserName(name);
            user.setUserNum(num);
            loginRepository.saveAndFlush(user);
        }
        catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMessage().toLowerCase();
            if(errorMessage.contains("'user_num'")) {
                throw new RuntimeException("이미 등록된 전화번호 입니다.");
            }
            else
                throw e;
        }
    }

    @Transactional
    public boolean updatePassword(String username, String password, String newPassword) {
        Optional<JpaUserEntity> optionalUser = loginRepository.findByUserId(username);
        String encryptedPw = sha256.encrypt(password);

        if (optionalUser.isEmpty()) {
            return false; // 사용자 존재하지 않음
        }

        JpaUserEntity user = optionalUser.get();
        if (!user.getUserPassword().equals(encryptedPw)) {
            return false; // 기존 비밀번호 불일치
        }
        encryptedPw = sha256.encrypt(newPassword);
        user.setUserPassword(encryptedPw); // 비밀번호 변경
        loginRepository.save(user);
        return true;
    }

    @Transactional
    public boolean updatePos(String username, String pos) {
        Optional<JpaUserEntity> optionalUser = loginRepository.findByUserId(username);

        if (optionalUser.isEmpty()) {
            return false; // 사용자 존재하지 않음
        }

        JpaUserEntity user = optionalUser.get();
        user.setUserPos(pos); // 관할 구역 변경
        loginRepository.save(user);
        return true;
    }

    @Transactional
    public JpaUserEntity getInfo(String username) {
        Optional<JpaUserEntity> optionalUser = loginRepository.findByUserId(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("유저가 존재하지 않습니다.");
        }
        return optionalUser.get();
    }

}

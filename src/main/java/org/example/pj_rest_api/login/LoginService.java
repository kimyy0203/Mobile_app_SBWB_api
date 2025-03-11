package org.example.pj_rest_api.login;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.example.pj_rest_api.Jpa.JpaUserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    @Transactional( readOnly = true )
    public boolean login(String username, String password) {
        Optional<JpaUserEntity> user = loginRepository.findByUserId(username);
        return user.map(u -> u.getUserPassword().equals(password)).orElse(false);
    }
    @Transactional
    public void register(String username, String password, String name, String num) {
        try {
            JpaUserEntity user = new JpaUserEntity();
            if(loginRepository.existsById(username))//username으로 중복 검사 -> 중복 발생 시 throw
                throw new DataIntegrityViolationException("이미 존재하는 ID 입니다.");
            user.setUserId(username);
            user.setUserPassword(password);
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

}

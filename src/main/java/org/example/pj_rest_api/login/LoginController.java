package org.example.pj_rest_api.login;

import org.example.pj_rest_api.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        boolean isAuthenticated = loginService.login(request.getUsername(), request.getPassword());
        if(isAuthenticated){
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
            //테스트
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest request){
        boolean isRegister = loginService.register(request.getUsername(), request.getPassword(), request.getName(), request.getNum());
        if(isRegister){
            return ResponseEntity.ok("Register successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Register failed");
        }
    }

    @PostMapping("/updatePassword") // 비밀번호 변경
    public ResponseEntity<String> updatePassword(@RequestBody LoginRequest request){
        boolean isUpdatePassword = loginService.updatePassword(request.getUsername(), request.getPassword(), request.getNewPassword());
        if(isUpdatePassword){
            return ResponseEntity.ok("Password update successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password update failed");
        }
    }
}

package org.example.pj_rest_api.login;

import org.example.pj_rest_api.dto.LoginRequest;
import org.example.pj_rest_api.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;
    public LoginController(LoginService loginService, JwtTokenProvider jwtTokenProvider) {
        this.loginService = loginService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        boolean isAuthenticated = loginService.login(request.getUsername(), request.getPassword());
        if(isAuthenticated){
            String token = jwtTokenProvider.generateToken(request.getUsername());
            // return ResponseEntity.ok("Login successful");
            return ResponseEntity.ok("Bearer " + token); // JWT 반환
        } else {
            // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed"); // 테스트
            return ResponseEntity.status(401).body("Login failed");
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
        String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isUpdatePassword = loginService.updatePassword(authenticatedUser, request.getPassword(), request.getNewPassword());
        if(isUpdatePassword){
            return ResponseEntity.ok("Password update successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password update failed");
        }
    }

    @PostMapping("/updatePos") // 관할 구역 변경
    public ResponseEntity<String> updatePos(@RequestBody LoginRequest request){
        String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isUpdatePos = loginService.updatePos(authenticatedUser, request.getPos());
        if(isUpdatePos){
            return ResponseEntity.ok("Position update successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Position update failed");
        }
    }
}

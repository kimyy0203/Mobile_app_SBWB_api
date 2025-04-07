package org.example.pj_rest_api.Pin;

import org.springframework.http.HttpStatus;
import org.example.pj_rest_api.Jpa.JpaPinEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pin")
public class PinController {
    private PinService pinService;
    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JpaPinEntity>> getAllPins() {
        List<JpaPinEntity> pins = pinService.getAllPins();
        return ResponseEntity.ok(pins);
    }
}
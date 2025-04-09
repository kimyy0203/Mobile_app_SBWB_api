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
    public ResponseEntity<List<JpaPinEntity>> getPins(
            @RequestParam(required = false) String ctprvnNm,
            @RequestParam(required = false) String signguNm,
            @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitude
    ) {
        List<JpaPinEntity> pins = pinService.getPins(ctprvnNm, signguNm, latitude, longitude);
        return ResponseEntity.ok(pins);
    }
}
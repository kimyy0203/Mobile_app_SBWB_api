package org.example.pj_rest_api.Pin;

import org.example.pj_rest_api.dto.PinRequest;
import org.springframework.http.HttpStatus;
import org.example.pj_rest_api.Jpa.JpaPinEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
            @RequestParam(required = false) BigDecimal latitude,
            @RequestParam(required = false) BigDecimal longitude,
            @RequestParam(required = false) String cat
    ) {
        List<JpaPinEntity> pins = pinService.getPins(ctprvnNm, signguNm, latitude, longitude, cat);
        return ResponseEntity.ok(pins);
    }

    @PostMapping("/add")
    public ResponseEntity<List<JpaPinEntity>> addPin(@RequestBody PinRequest pin) {
        pinService.pinAction("add",pin.getLat(),pin.getLon(),pin.getCom(),pin.getCtp(),pin.getSig(),pin.getCat(),pin.getAddr());
        List<JpaPinEntity> pins = pinService.getPins(null,null,pin.getLat(),pin.getLon(),null);
        return ResponseEntity.ok(pins);
    }
    @PostMapping("/del")
    public ResponseEntity<String> removePin(@RequestBody PinRequest pin) {
        pinService.pinAction("del",pin.getLat(),pin.getLon(),null, null, null, null,null);
        return ResponseEntity.ok("Pin delete successful");
    }
    @PostMapping("/mod")
    public ResponseEntity<List<JpaPinEntity>> changePin(@RequestBody PinRequest pin) {
        pinService.pinAction("mod",pin.getLat(),pin.getLon(),pin.getCom(),pin.getCtp(),pin.getSig(),pin.getCat(),pin.getAddr());
        List<JpaPinEntity> pins = pinService.getPins(null,null,pin.getLat(),pin.getLon(),null);
        return ResponseEntity.ok(pins);
    }
    @PostMapping("/search")
    public ResponseEntity<List<JpaPinEntity>> searchPin(@RequestBody PinRequest pin) {
        List<JpaPinEntity> pins = pinService.pinSearch(pin.getType(),pin.getCom());
        return ResponseEntity.ok(pins);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
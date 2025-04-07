package org.example.pj_rest_api.Pin;

import org.example.pj_rest_api.Jpa.JpaPinEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PinService {

    private final PinRepository pinRepository;

    public PinService(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    public List<JpaPinEntity> getAllPins() {
        return pinRepository.findAll();
    }
}
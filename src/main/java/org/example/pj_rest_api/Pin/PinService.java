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

    public List<JpaPinEntity> getPins(String ctprvnnm, String signgunm, String latitude, String longitude) {
        return pinRepository.findByFilters(ctprvnnm, signgunm, latitude, longitude);
    }
}
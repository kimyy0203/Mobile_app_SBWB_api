package org.example.pj_rest_api.Pin;

import org.example.pj_rest_api.Jpa.JpaPinEntity;
import org.example.pj_rest_api.Jpa.JpaPinEntityId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PinService {

    private final PinRepository pinRepository;

    public PinService(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    public List<JpaPinEntity> getPins(String ctprvnnm, String signgunm, BigDecimal latitude, BigDecimal longitude, String cat) {
        return pinRepository.findByFilters(ctprvnnm, signgunm, latitude, longitude, cat);
    }

    @Transactional
    public void pinAction(String type, BigDecimal lat, BigDecimal lon, String com, String ctp, String sig, String cat, String addr) {
        JpaPinEntityId Id = new JpaPinEntityId();
        Id.setLat(lat);
        Id.setLon(lon);

        boolean isNull = pinRepository.existsById(Id);
        try {
            JpaPinEntity pin = new JpaPinEntity();
            switch (type){
                case "add":
                    if(isNull)//값이 있으면
                       throw new IllegalStateException("이미 등록된 좌표입니다.");
                    else {//새로 추가
                        pin.setId(Id);
                        pin.setComment(com);
                        pin.setCtprvnnm(ctp);
                        pin.setSigngunm(sig);
                        pin.setCat(cat);
                        pin.setAddr(addr);
                        pinRepository.saveAndFlush(pin);
                    }
                    break;
                case "mod":
                    if(!isNull)//값이 없으면
                        throw new IllegalStateException("해당하는 좌표값이 없습니다.");
                    else{
                        pin.setId(Id);
                        pin.setComment(com);
                        pin.setCtprvnnm(ctp);
                        pin.setSigngunm(sig);
                        pin.setCat(cat);
                        pinRepository.saveAndFlush(pin);
                    }
                    break;
                case "del":
                    if(!isNull)//값이 없으면
                        throw new IllegalStateException("해당하는 좌표값이 없습니다.");
                    else{
                        pinRepository.deleteById(Id);
                    }
            }
        }
        catch (IllegalStateException e) {
            throw e;
        }

    }
}
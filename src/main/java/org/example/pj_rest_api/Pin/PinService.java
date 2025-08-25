package org.example.pj_rest_api.Pin;

import org.aspectj.lang.annotation.RequiredTypes;
import org.example.pj_rest_api.Jpa.JpaPinEntity;
import org.example.pj_rest_api.Jpa.JpaPinEntityId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PinService {

    private final PinRepository pinRepository;
    private final EntityManager entityManager;

    public PinService(PinRepository pinRepository, EntityManager entityManager) {
        this.pinRepository = pinRepository;
        this.entityManager = entityManager;
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
                        entityManager.clear();
                    }
                    break;
                case "mod":
                    if(!isNull)//값이 없으면
                        throw new IllegalStateException("해당하는 좌표값이 없습니다.");
                    else{
                        pin = pinRepository.findById(Id).get();
                        pin.setId(Id);
                        pin.setComment(com);
                        pin.setCat(cat);
                        pinRepository.saveAndFlush(pin);
                        entityManager.clear();
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

    public List<JpaPinEntity> pinSearch(String type, String com) {
        switch (type) {
            case "com": return pinRepository.findByCommentContaining(com);
            case "addr": return pinRepository.findByAddrContaining(com);
            default: return pinRepository.findByAddrOrCommentContaining(com);
        }
    }
}
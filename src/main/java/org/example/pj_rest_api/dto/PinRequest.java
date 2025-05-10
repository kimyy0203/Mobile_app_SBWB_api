package org.example.pj_rest_api.dto;

import java.math.BigDecimal;

public class PinRequest {
    private BigDecimal lat;
    private BigDecimal lon;
    private String com;
    private String ctp;
    private String sig;
    private String cat;
    public PinRequest() {}
    public BigDecimal getLat() {
        return lat;
    }
    public BigDecimal getLon() {
        return lon;
    }
    public String getCom() {
        return com;
    }
    public void setcom(String com){
        this.com=com;
    }
    public String getCtp() {
        return ctp;
    }
    public void setctp(String ctp){
        this.ctp=ctp;
    }
    public String getSig() {
        return sig;
    }
    public void setsig(String sig){
        this.sig=sig;
    }
    public String getCat() {
        return cat;
    }
    public void setcat(String cat){
        this.cat=cat;
    }
}

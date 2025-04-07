package org.example.pj_rest_api.Jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class JpaPinEntityId implements java.io.Serializable {
    private static final long serialVersionUID = -1383348199884244984L;
    @Column(name = "lat", nullable = false, precision = 20, scale = 10)
    private BigDecimal lat;

    @Column(name = "lon", nullable = false, precision = 20, scale = 10)
    private BigDecimal lon;

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JpaPinEntityId entity = (JpaPinEntityId) o;
        return Objects.equals(this.lon, entity.lon) &&
                Objects.equals(this.lat, entity.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lon, lat);
    }

}
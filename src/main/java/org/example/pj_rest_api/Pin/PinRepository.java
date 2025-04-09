package org.example.pj_rest_api.Pin;

import org.example.pj_rest_api.Jpa.JpaPinEntity;
import org.example.pj_rest_api.Jpa.JpaPinEntityId;
import org.example.pj_rest_api.Jpa.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PinRepository extends JpaRepository<JpaPinEntity, Long> {
    @Query("SELECT p FROM JpaPinEntity p WHERE " +
            "(:signgunm IS NULL OR p.signgunm = :signgunm) AND " +
            "(:ctprvnnm IS NULL OR p.ctprvnnm = :ctprvnnm) AND " +
            "(:latitude IS NULL OR p.id.lat = :latitude) AND " +
            "(:longitude IS NULL OR p.id.lon = :longitude)")
    List<JpaPinEntity> findByFilters(
            @Param("ctprvnnm") String ctprvnnm,
            @Param("signgunm") String signgunm,
            @Param("latitude") String latitude,
            @Param("longitude") String longitude
    );
}

package org.example.pj_rest_api.Pin;

import org.example.pj_rest_api.Jpa.JpaPinEntity;
import org.example.pj_rest_api.Jpa.JpaPinEntityId;
import org.example.pj_rest_api.Jpa.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PinRepository extends JpaRepository<JpaPinEntity, Long> {
}

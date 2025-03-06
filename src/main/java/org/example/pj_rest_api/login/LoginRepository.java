package org.example.pj_rest_api.login;

import jakarta.persistence.LockModeType;
import  org.example.pj_rest_api.Jpa.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<JpaUserEntity, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<JpaUserEntity> findByUserId(String userId);
}

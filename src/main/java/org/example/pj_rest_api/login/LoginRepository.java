package org.example.pj_rest_api.login;

import  org.example.pj_rest_api.Jpa.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<JpaUserEntity, Integer> {
    Optional<JpaUserEntity> findByUserId(String userId);
}

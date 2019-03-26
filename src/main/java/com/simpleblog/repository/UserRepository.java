package com.simpleblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simpleblog.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByLastName(String username);
    Boolean existsByLastName(String username);
    Boolean existsByEmail(String email);
    Optional<UserEntity> findUserByEmail(String email);
}

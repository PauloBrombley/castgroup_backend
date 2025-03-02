package com.castgroup.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castgroup.backend.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	Optional<UserModel> findByName(String name);
	
}

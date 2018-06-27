package com.friends.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.friends.dto.UserDTO;

public interface UserRepository extends CrudRepository<UserDTO, Long> {

	UserDTO findFirstByEmail(String email);
	List<UserDTO> findAllByEmailIn(Set<String> emails);
}
package com.friends.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.friends.dao.UserRepository;
import com.friends.dto.UserDTO;
import com.friends.request.BlockerRequestEntity;

@Service
public class BlockerServiceImpl implements BlockerService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<Map<String, Object>> addBlockers(BlockerRequestEntity friendsEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (friendsEntity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		if (friendsEntity.getRequestor() == null || friendsEntity.getTarget() == null) {
			result.put("Error : ", "Requester or Target can not be empty");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		
		String email1 = friendsEntity.getRequestor();
		String email2 = friendsEntity.getTarget();

		if (email1.equals(email2)) {
			result.put("Info : ", "Cannot subscribe , if users are same");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		
		UserDTO user1Dto = this.userRepository.findFirstByEmail(email1);
		UserDTO user2Dto = this.userRepository.findFirstByEmail(email2);

		user1Dto.addBlockers(user2Dto);
		this.userRepository.save(user1Dto);

		result.put("Success", true);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}

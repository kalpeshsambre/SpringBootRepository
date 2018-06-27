package com.friends.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.friends.dao.UserRepository;
import com.friends.dto.UserDTO;
import com.friends.request.FriendsListRequestEntity;
import com.friends.request.FriendsRequestEntity;

@Service
public class FriendsServiceImpl implements FriendsService {

	@Autowired
	private UserRepository userRepository;

	private UserDTO saveIfNotExist(String email) {

		UserDTO existingUser = this.userRepository.findFirstByEmail(email);
		if (existingUser == null) {
			existingUser = new UserDTO();
			existingUser.setEmail(email);
			return this.userRepository.save(existingUser);
		} else {
			return existingUser;
		}

	}

	@Override
	public ResponseEntity<Map<String, Object>> addFriends(FriendsRequestEntity friendsEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (friendsEntity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		if (CollectionUtils.isEmpty(friendsEntity.getFriends())) {
			result.put("Error : ", "Friend list cannot be empty");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		if (friendsEntity.getFriends().size() != 2) {
			result.put("Info : ", "Please provide 2 emails to make them friends");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		String email1 = friendsEntity.getFriends().get(0);
		String email2 = friendsEntity.getFriends().get(1);

		if (email1.equals(email2)) {
			result.put("Info : ", "Cannot make friends, if users are same");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		UserDTO user1Dto = null;
		UserDTO user2Dto = null;
		user1Dto = this.saveIfNotExist(email1);
		user2Dto = this.saveIfNotExist(email2);

		if (user1Dto.getFriends().contains(user2Dto)) {
			result.put("Info : ", "Cannot add them as friends as they are already friends");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}

		if (user1Dto.getBlockers().contains(user2Dto)) {
			result.put("Info : ", "Cannot add them as friends are blocked ");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}

		user1Dto.addFriend(user2Dto);
		this.userRepository.save(user1Dto);
		result.put("Success", true);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getFriendsList(FriendsListRequestEntity entity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (entity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		UserDTO userDto = this.userRepository.findFirstByEmail(entity.getEmail());
		List<String> friendList = userDto.getFriends().stream().map(UserDTO::getEmail).collect(Collectors.toList());

		result.put("success", true);
		result.put("friends", friendList);
		result.put("count", friendList.size());

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Map<String, Object>> getCommonFriends(FriendsRequestEntity entity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (entity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		UserDTO user1Dto = null;
		UserDTO user2Dto = null;
		user1Dto = this.userRepository.findFirstByEmail(entity.getFriends().get(0));
		user2Dto = this.userRepository.findFirstByEmail(entity.getFriends().get(1));

		if (user1Dto.getEmail().equals(user2Dto.getEmail())) {
			result.put("Info : ", "Both users are same");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		Set<UserDTO> friends = null;
		friends = user1Dto.getFriends();
		friends.retainAll(user2Dto.getFriends());

		result.put("success", true);
		result.put("friends", friends.stream().map(UserDTO::getEmail).collect(Collectors.toList()));
		result.put("count", friends.size());

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}

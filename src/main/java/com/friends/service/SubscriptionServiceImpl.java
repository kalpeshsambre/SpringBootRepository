package com.friends.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.friends.dao.UserRepository;
import com.friends.dto.UserDTO;
import com.friends.request.ReceiveUpdateRequestEntity;
import com.friends.request.SubscribeRequestEntity;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<Map<String, Object>> addSubscribers(SubscribeRequestEntity friendsEntity) {

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

		user1Dto.addSubscribers(user2Dto);
		this.userRepository.save(user1Dto);

		result.put("Success", true);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> receiveUpdateFrom(ReceiveUpdateRequestEntity updateRequest) {
		Map<String, Object> result = new HashMap<String, Object>();

		if (updateRequest == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		UserDTO userDto = this.userRepository.findFirstByEmail(updateRequest.getSender());

		Set<UserDTO> receipents = new HashSet<>();

		receipents.addAll(userDto.getFriends());
		receipents.addAll(userDto.getSubscribers());

		Set<String> receipentsInText = new HashSet<String>();
		if (updateRequest.getText() != null && !updateRequest.getText().isEmpty()) {
			Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")
					.matcher(updateRequest.getText());
			while (m.find()) {
				receipentsInText.add(m.group());
			}

			List<UserDTO> usersInText = this.userRepository.findAllByEmailIn(receipentsInText);
			receipents.retainAll(usersInText);

			receipents.removeAll(userDto.getBlockers());
			receipents.remove(userDto);

			result.put("success", true);
			result.put("recipients", receipents.stream().map(UserDTO::getEmail).collect(Collectors.toList()));

		}
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}

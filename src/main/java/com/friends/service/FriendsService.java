package com.friends.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.friends.request.FriendsListRequestEntity;
import com.friends.request.FriendsRequestEntity;

public interface FriendsService {

	ResponseEntity<Map<String, Object>> addFriends(FriendsRequestEntity friendsEntity);

	ResponseEntity<Map<String, Object>> getFriendsList(FriendsListRequestEntity entity);

	ResponseEntity<Map<String, Object>> getCommonFriends(FriendsRequestEntity friendsEntity);
}

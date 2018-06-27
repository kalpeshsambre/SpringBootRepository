package com.friends.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.friends.request.ReceiveUpdateRequestEntity;
import com.friends.request.SubscribeRequestEntity;

public interface SubscriptionService {

	ResponseEntity<Map<String, Object>> addSubscribers(SubscribeRequestEntity friendsEntity);

	ResponseEntity<Map<String, Object>> receiveUpdateFrom(ReceiveUpdateRequestEntity friends);
}

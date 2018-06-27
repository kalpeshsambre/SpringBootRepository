package com.friends.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.friends.request.BlockerRequestEntity;

public interface BlockerService {

	ResponseEntity<Map<String, Object>> addBlockers(BlockerRequestEntity friendsEntity);
}

package com.friends.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.friends.request.BlockerRequestEntity;
import com.friends.request.FriendsListRequestEntity;
import com.friends.request.FriendsRequestEntity;
import com.friends.request.ReceiveUpdateRequestEntity;
import com.friends.request.SubscribeRequestEntity;
import com.friends.service.BlockerService;
import com.friends.service.FriendsService;
import com.friends.service.SubscriptionService;

@RestController
public class FriendsController {

	@Autowired
	private FriendsService friendsService;

	@Autowired
	private SubscriptionService subscriberService;

	@Autowired
	private BlockerService blockerService;

	@RequestMapping(value = "/friendRequest", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> friendRequest(@RequestBody FriendsRequestEntity friends) {
		return this.friendsService.addFriends(friends);
	}

	@RequestMapping(value = "/friendList", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> friendList(@RequestBody FriendsListRequestEntity friendListOf) {
		return this.friendsService.getFriendsList(friendListOf);
	}

	@RequestMapping(value = "/commonFriends", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> commonFriends(@RequestBody FriendsRequestEntity friendListOf) {
		return this.friendsService.getCommonFriends(friendListOf);
	}

	@RequestMapping(value = "/requestToSubscribe", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> requestToSubscribe(@RequestBody SubscribeRequestEntity friends) {
		return this.subscriberService.addSubscribers(friends);
	}

	@RequestMapping(value = "/requestToBlock", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> requestToBlock(@RequestBody BlockerRequestEntity friends) {
		return this.blockerService.addBlockers(friends);
	}
	
	@RequestMapping(value = "/receiveUpdateFrom", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> receiveUpdateFrom(@RequestBody ReceiveUpdateRequestEntity friends) {
		return this.subscriberService.receiveUpdateFrom(friends);
	}
}

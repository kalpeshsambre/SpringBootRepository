package com.friends.dto;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "user")
public class UserDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "email")
	private String email;

	@ManyToMany
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "userId") , inverseJoinColumns = @JoinColumn(name = "friendId") )
	private Set<UserDTO> friends;

	@ManyToMany
	@JoinTable(name = "subscribers", joinColumns = @JoinColumn(name = "subscriberId") , inverseJoinColumns = @JoinColumn(name = "targetUserId") )
	private Set<UserDTO> subscribers;

	@ManyToMany
	@JoinTable(name = "blockers", joinColumns = @JoinColumn(name = "blockerId") , inverseJoinColumns = @JoinColumn(name = "targetUserId") )
	private Set<UserDTO> blockers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserDTO> getFriends() {
		return friends;
	}

	public void setFriends(Set<UserDTO> friends) {
		this.friends = friends;
	}

	public Set<UserDTO> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Set<UserDTO> subscribers) {
		this.subscribers = subscribers;
	}

	public Set<UserDTO> getBlockers() {
		return blockers;
	}

	public void setBlockers(Set<UserDTO> blockers) {
		this.blockers = blockers;
	}

	public void addFriend(UserDTO user) {
		if (CollectionUtils.isEmpty(this.friends)) {
			this.friends = new HashSet<>();
		}
		this.friends.add(user);
	}

	public void addSubscribers(UserDTO user) {
		if (CollectionUtils.isEmpty(this.subscribers)) {
			this.subscribers = new HashSet<>();
		}
		this.subscribers.add(user);
	}

	public void addBlockers(UserDTO user) {
		if (CollectionUtils.isEmpty(this.blockers)) {
			this.blockers = new HashSet<>();
		}
		this.blockers.add(user);
	}

}

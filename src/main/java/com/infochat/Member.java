package com.infochat;

import java.io.Serializable;
import java.util.UUID;

public class Member implements Serializable {
	private String username;
	private String userID;
	private String publicKey;
	private boolean isAdmin;

	public Member(String username, boolean isAdmin) {
		this.username = username;
		this.userID = UUID.randomUUID().toString();
		this.isAdmin = isAdmin;
	}
}

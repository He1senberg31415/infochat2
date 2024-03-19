package com.infochat;

import java.io.Serializable;
import java.util.UUID;

public class Message implements Serializable {
	private String id;
	private int timestamp;
	private String content;
	private String sender;

	public Message(String sender, String content) {
		this.id = UUID.randomUUID().toString();
		this.timestamp = 1;
		this.sender = sender;
		this.content = content;
	}

	public String getID() {
		return this.id;
	}

	public String getContent() {
		return this.content;
	}

	public String getSender() {
		return this.sender;
	}
}

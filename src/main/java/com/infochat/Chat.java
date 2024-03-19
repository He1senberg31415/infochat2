package com.infochat;

import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;;

public class Chat implements Serializable {
	private ArrayList<Member> members;
	private String name;
	private String id;
	private ArrayList<Message> messages;

	public Chat(Member creator) {
		this.members = new ArrayList<Member>();
		this.id = UUID.randomUUID().toString();
	}

	public ArrayList<Member> getMembers() {
		return this.members;
	}

	public String getName() {
		return this.name;
	}

	public String getID() {
		return this.id;
	}

	public ArrayList<Message> getMessages() {
		return this.messages;
	}
}

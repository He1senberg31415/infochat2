package com.infochat;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Messenger implements Serializable {
	private Chat currentChat;
	private Firestore db;
	private Member localMember = new Member("", false);
	private HashMap<String, Chat> chats;
	private Crypto cryptoManager = new Crypto();
	private Settings settings;
	public boolean loggedIn = false;

	public Messenger() {
		this.chats.put("", new Chat(localMember));

		try {
			FileInputStream serviceAccount = new FileInputStream("credential.json"); // Replace with

			// Step 2: Initialize Firebase Admin SDK
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			FirebaseApp.initializeApp(options);

			// Step 3: Create a Firestore instance
			this.db = FirestoreClient.getFirestore();
		} catch (Exception e) {
			System.out.println("err.");
		}

	}

	public String[] getChatNames() {
		Set<String> keys = this.chats.keySet();
		System.out.println("Length " + this.chats.keySet().toArray().length);
		return keys.toArray(new String[0]);
	}

	public void setCrypto(Crypto crypto) {
		this.cryptoManager = crypto;
	}

	public Chat getCurrentChat() {
		return this.currentChat;
	}

	public void selectChat(String id) {
		this.currentChat = chats.get(id);
	}

	public void createChat() {
		// Chat chat = new Chat(this.localMember);
		// String chatID = chat.getID();
		// if (chatID != null) {
		// DocumentReference docRef = this.db.collection("chatID").document("root");
		// Message customObject = new Message("user", "hello");

		// docRef.set(customObject);
		// this.chats.put(chatID, chat);
		// } else {
		// System.out.println("error id");
		// }s
	}

	public void joinChat(String chatID) {
		// Chat chat;
		// try {
		// // chat = (Chat) this.db.getDocument("chats", chatID, Chat.class);
		// this.chats.put(chatID, chat);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// e.printStackTrace();
		// }
	}

	public void sendMessage(String content) {
		Message msg = new Message("localMember", content);
	}
}

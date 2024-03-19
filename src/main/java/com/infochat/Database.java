package com.infochat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nonnull;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Database implements Serializable {
	private FirebaseOptions appOptions;
	private FirebaseApp app;
	private FirestoreOptions options;
	public Firestore db;
	private boolean proxyActive;
	private String proxyAddress;
	private int proxyPort;
	private static boolean firebaseInitialized = false;

	public Database() {
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

	public void createDocument(@Nonnull String collection, @Nonnull String document, @Nonnull Object data) {
		DocumentReference docRef = this.db.collection(collection).document("test");

		// Step 5: Create an instance of your custom class
		Message customObject = new Message("user", "hello");

		// Step 6: Convert your custom class instance into a Map
		docRef.set(data);
	}

	public Object getDocument(@Nonnull String collection, @Nonnull String document, Class<?> clazz)
			throws InterruptedException, ExecutionException {
		return this.db.collection(collection).document(document).get().get().toObject(clazz);
	}
}

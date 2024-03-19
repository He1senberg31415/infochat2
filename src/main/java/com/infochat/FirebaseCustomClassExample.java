package com.infochat;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FirebaseCustomClassExample {

	public static void main(String[] args) throws IOException {
		// Step 1: Set up Firebase Admin SDK in your Java project
		FileInputStream serviceAccount = new FileInputStream("credential.json"); // Replace with

		// Step 2: Initialize Firebase Admin SDK
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
		FirebaseApp.initializeApp(options);

		// Step 3: Create a Firestore instance
		Firestore db = FirestoreClient.getFirestore();

		// Step 4: Create a new document reference
		DocumentReference docRef = db.collection("custom_objects").document("custom_object_id");

		// Step 5: Create an instance of your custom class
		Message customObject = new Message("user", "hello");

		// Step 6: Convert your custom class instance into a Map
		docRef.set(customObject);

		// Step 7: Add the document to Firestore

		System.out.println("Custom class stored successfully.");

		// Close the Firestore client
		try {
			db.close();
		} catch (Exception e) {
			System.out.println("err.");

		}
	}
}

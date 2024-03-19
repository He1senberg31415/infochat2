package com.infochat;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto implements Serializable {
	private KeyPair keyPair;
	private String passwordHash;

	public void createKeyPair(String path) {
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			this.keyPair = keyPairGenerator.generateKeyPair();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String generateHash(String data) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes());

			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			System.out.println("slökj " + hexString.toString());
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("tre");
			e.printStackTrace();
			return "";
		}
	}

	public void setPasswordHash(String password) {
		this.passwordHash = generateHash(password);
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public KeyPair getKeyPair() {
		return this.keyPair;
	}
}

package com.infochat;

import java.io.Serializable;

public class Settings implements Serializable {
	private boolean proxyActive;
	private String proxyAddress;
	private int proxyPort;
	private String keyFilePath;
	private String chatFilePath;
	private String username;

	public Settings() {
		this.proxyActive = false;
		this.proxyAddress = "";
		this.proxyPort = 0;
		this.keyFilePath = "./";
		this.chatFilePath = "./";
	}

	public boolean getProxyActive() {
		return this.proxyActive;
	}

	public String getProxyAddress() {
		return this.proxyAddress;
	}

	public int getProxyPort() {
		return this.proxyPort;
	}

	public String getKeyFilePath() {
		return this.keyFilePath;
	}

	public String getChatFilePath() {
		return this.chatFilePath;
	}
}

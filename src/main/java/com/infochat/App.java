package com.infochat;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
	private JList<String> chatList;
	private JTextArea chatArea;
	private JTextField inputField;
	private JButton sendButton;

	public App() {
		setTitle("Chat Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);

		chatList = new JList<>(new String[] { "Chat 1", "Chat 2", "Chat 3" });
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		JScrollPane chatAreaScrollPane = new JScrollPane(chatArea);

		inputField = new JTextField();
		sendButton = new JButton("Send");

		JPanel chatPanel = new JPanel();
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
		chatPanel.add(chatAreaScrollPane);
		chatPanel.add(inputField);
		chatPanel.add(sendButton);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chatList, chatPanel);
		splitPane.setDividerLocation(150);

		add(splitPane);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new App().setVisible(true);
		});
	}
}
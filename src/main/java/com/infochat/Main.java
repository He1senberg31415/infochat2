package com.infochat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends JFrame {
	private JFrame frame;
	private JPanel panelContainer;
	private JPanel panelLogin;
	private JPanel panelRegistration;
	private CardLayout cardLayout;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField regUsernameField;
	private JPasswordField regPasswordField;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel bottomPanel;
	private JTextArea messagesTextArea;
	private JTextField inputField;
	private JButton sendButton;

	private List<Message> messages;

	private Messenger messenger;
	private Crypto crypto;
	private Storage storage;
	JList<String> chatsList;

	public Main() {
		panelContainer = new JPanel();
		panelLogin = new JPanel();
		panelRegistration = new JPanel();
		cardLayout = new CardLayout();
		storage = new Storage();
		messenger = new Messenger();

		panelContainer.setLayout(cardLayout);

		panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelLogin.setLayout(null);

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(398, 312, 117, 29);
		panelLogin.add(loginButton);

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String password = new String(passwordField.getPassword());
				crypto = new Crypto();
				String content = "";

				try {
					content = readFileAsString("password.dat");
					System.out.println(content);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (crypto.generateHash(password).equals(content)) {
					cardLayout.show(panelContainer, "Chat");
					messenger = (Messenger) storage.load("config.dat");
					messenger.createChat();
					System.out.println(messenger.getChatNames().length);
					displayChats();
				} else {
					System.out.println(content);
					System.out.println("2 " + crypto.generateHash(password));
					JOptionPane.showMessageDialog(frame, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(366, 234, 94, 16);
		panelLogin.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(366, 262, 178, 38);
		panelLogin.add(passwordField);

		panelRegistration.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelRegistration.setLayout(null);

		JLabel regUsernameLabel = new JLabel("Username");
		regUsernameLabel.setBounds(366, 150, 94, 16);
		panelRegistration.add(regUsernameLabel);

		JButton registerButton = new JButton("Register");
		registerButton.setBounds(398, 312, 117, 29);
		panelRegistration.add(registerButton);

		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = regUsernameField.getText();
				String password = new String(regPasswordField.getPassword());

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					crypto = new Crypto();
					crypto.setPasswordHash(crypto.generateHash(password));
					crypto.createKeyPair(password);
					messenger = new Messenger();
					messenger.setCrypto(crypto);
					storage.save(messenger, "config.dat");

					try {
						writeStringToFile(crypto.generateHash(password), "password.dat");
						System.out.println("Content has been written to the file.");
					} catch (IOException e) {
						e.printStackTrace();
					}
					// set username
					cardLayout.show(panelContainer, "Chat");
				}
			}
		});

		regUsernameField = new JTextField();
		regUsernameField.setBounds(363, 178, 181, 44);
		panelRegistration.add(regUsernameField);
		regUsernameField.setColumns(10);

		JLabel regPasswordLabel = new JLabel("Password");
		regPasswordLabel.setBounds(366, 234, 94, 16);
		panelRegistration.add(regPasswordLabel);

		regPasswordField = new JPasswordField();
		regPasswordField.setBounds(366, 262, 178, 38);
		panelRegistration.add(regPasswordField);

		panelContainer.add(panelLogin, "Login");
		panelContainer.add(panelRegistration, "Registration");

		File file1 = new File("config.dat"); // Replace "path_to_your_file" with the actual path to your file
		File file2 = new File("password.dat"); // Replace "path_to_your_file" with the actual path to your file

		boolean filesExist = file1.exists() && file2.exists();

		cardLayout.show(panelContainer, filesExist ? "Login" : "Registration");

		messages = new ArrayList<>();

		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(150, 400));

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
		JButton joinButton = new JButton("Join");
		JButton createButton = new JButton("Create");

		// Add action listeners to the buttons
		joinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openJoinWindow();
			}
		});

		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("createbutton");
				System.out.println(messenger.getChatNames().length);
				System.out.println("changing");
				messenger.createChat();
				displayChats();
				System.out.println(messenger.getChatNames().length);

			}
		});

		buttonsPanel.add(joinButton);
		buttonsPanel.add(createButton);

		leftPanel.add(buttonsPanel, BorderLayout.NORTH);

		chatsList = new JList<>(messenger.getChatNames());

		chatsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedChatName = (String) chatsList.getSelectedValue();
					messenger.selectChat(selectedChatName);
					messages = messenger.getCurrentChat().getMessages();
					displayMessages();
					System.out.println(selectedChatName);
				}
			}
		});

		JScrollPane leftScrollPane = new JScrollPane(chatsList);
		leftPanel.add(leftScrollPane, BorderLayout.CENTER);

		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());

		messagesTextArea = new JTextArea();
		messagesTextArea.setEditable(false);
		JScrollPane messagesScrollPane = new JScrollPane(messagesTextArea);
		rightPanel.add(messagesScrollPane, BorderLayout.CENTER);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());

		inputField = new JTextField();
		bottomPanel.add(inputField, BorderLayout.CENTER);

		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		bottomPanel.add(sendButton, BorderLayout.EAST);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		panelContainer.add(mainPanel, "Chat");

		add(panelContainer);
		setBounds(100, 100, 900, 510);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void sendMessage() {
		String messageContent = inputField.getText();
		if (!messageContent.isEmpty()) {
			messages.add(new Message("You", messageContent));
			displayMessages();
			inputField.setText("");
		}
		storage.save(messenger, "config.dat");
	}

	private void displayMessages() {
		messagesTextArea.setText("");
		for (Message message : messages) {
			messagesTextArea.append(message.getSender() + ": " + message.getContent() + "\n");
		}
		storage.save(messenger, "config.dat");
	}

	public void displayChats() {
		String[] chatNames = messenger.getChatNames();
		chatsList.setListData(chatNames); // Update the data model of the JList
		storage.save(messenger, "config.dat");
	}

	public void addMessageFromOtherUser(String sender, String content) {
		messages.add(new Message(sender, content));
		displayMessages();
	}

	public static String readFileAsString(String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)));
	}

	public static void writeStringToFile(String content, String fileName) throws IOException {
		Files.write(Paths.get(fileName), content.getBytes());
	}

	private void openJoinWindow() {
		JFrame joinFrame = new JFrame("Join Window");
		joinFrame.setSize(800, 600);
		joinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel joinPane = new JPanel();
		joinPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		joinFrame.add(joinPane);

		joinFrame.setContentPane(joinPane);
		joinFrame.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Chat-ID");
		lblNewLabel_1.setBounds(366, 150, 61, 16);
		joinPane.add(lblNewLabel_1);

		JTextField joinField = new JTextField();
		joinField.setBounds(363, 178, 181, 44);
		joinField.setColumns(10);
		joinPane.add(joinField);

		JButton btnNewButton = new JButton("Beitreten");
		btnNewButton.setBounds(397, 234, 117, 29);
		joinPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (joinField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter Chat-ID.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					messenger.joinChat(joinField.getText());
					displayChats();
					joinFrame.dispose();
				}
			}
		});

		joinFrame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}
}

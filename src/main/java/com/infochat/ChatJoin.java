package com.infochat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class ChatJoin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatJoin frame = new ChatJoin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatJoin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Chat-ID");
		lblNewLabel_1.setBounds(366, 150, 61, 16);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Beitreten");
		btnNewButton.setBounds(397, 234, 117, 29);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(363, 178, 181, 44);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}

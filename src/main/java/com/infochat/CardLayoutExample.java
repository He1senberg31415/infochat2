package com.infochat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardLayoutExample {
	private JFrame frame;
	private JPanel panelCont;
	private JPanel panelFirst;
	private JPanel panelSecond;
	private JButton buttonOne;
	private JButton buttonTwo;
	private CardLayout cl;

	public CardLayoutExample() {
		frame = new JFrame("CardLayout Example");
		panelCont = new JPanel();
		panelFirst = new JPanel();
		panelSecond = new JPanel();
		buttonOne = new JButton("Switch to second panel/screen");
		buttonTwo = new JButton("Switch to first panel/screen");
		cl = new CardLayout();

		panelCont.setLayout(cl);

		panelFirst.add(buttonOne);
		panelSecond.add(buttonTwo);
		panelFirst.setBackground(Color.BLUE);
		panelSecond.setBackground(Color.RED);

		panelCont.add(panelFirst, "1");
		panelCont.add(panelSecond, "2");

		buttonOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "2");
			}
		});

		buttonTwo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "1");
			}
		});

		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CardLayoutExample();
			}
		});
	}
}
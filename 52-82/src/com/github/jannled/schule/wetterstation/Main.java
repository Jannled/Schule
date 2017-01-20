package com.github.jannled.schule.wetterstation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main implements ActionListener
{
	JFrame frame;
	JPanel root;
	JButton button = new JButton("Messen");
	Date startTime;
	
	public Main()
	{
		frame = new JFrame("Wetterstation");
		root = new JPanel(new BorderLayout());
		
		button.addActionListener(this);
		
		root.add(button, BorderLayout.SOUTH);
		frame.add(root);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		try {
			System.out.println("Setting look and feel to OS design.");
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found!");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.err.println("Failed to Instantiate!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("Illegal Acces!");
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println("Unsuported Look and feel!");
			e.printStackTrace();
		}
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == button)
		{
			add
		}
	}
	
	public void addDataentry(Dataentry dataentry)
	{
		daten.add(dataentry);
		
	}
}

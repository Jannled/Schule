package com.github.jannled.fib;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main implements ActionListener 
{
	private static final String numberErr = "Eingabe ist keine Zahl!";
	
	JFrame frame = new JFrame("Fibonaccizahlen");
	JPanel panel = new JPanel(new GridLayout(0, 1));
	JPanel confirm = new JPanel(new GridLayout(1, 0));
	JTextField input = new JTextField("");
	JTextField output = new JTextField("Bitte geben sie eine Zahl in das obige Feld ein!");
	JButton recursive = new JButton("Rekursiv");
	JButton iterative = new JButton("Iterativ");
	
	
	public Main()
	{
		frame.add(panel);
		
		panel.add(input);
		
		output.setEditable(false);
		panel.add(output);
		
		recursive.addActionListener(this);
		confirm.add(recursive);
		
		iterative.addActionListener(this);
		confirm.add(iterative);
		
		panel.add(confirm);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		System.out.println(Arrays.toString(args));
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == recursive)
		{
			try {
				long in = Long.parseLong(input.getText());
				output.setText("Fibonacci Zahl ist: " + Fibonacci.recursive(in));
			} catch(NumberFormatException ex)
			{
				System.err.println(numberErr);
				output.setText(numberErr);
			}
		}
		else if(e.getSource() == iterative)
		{
			try {
				long in = Long.parseLong(input.getText());
				output.setText("Fibonacci Zahl ist: " + Fibonacci.iterativ(in));
			} catch(NumberFormatException ex)
			{
				System.err.println(numberErr);
				output.setText(numberErr);
			}
		}
	}
}

package com.github.jannled.b42;

import javax.swing.JFrame;

public class Main 
{
	JFrame frame = new JFrame("Lebensform b42");
	Matrix matrix;
	
	public Main()
	{
		matrix = new Matrix(10, 10);
		frame.add(matrix);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new Main();
	}

}

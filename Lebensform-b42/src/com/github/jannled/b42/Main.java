package com.github.jannled.b42;

import java.util.Arrays;

import javax.swing.JFrame;

public class Main 
{
	JFrame frame;
	Canvas canvas;
	Zellhaufen matrix;
	public static boolean simRunning = true;
	
	public Main()
	{
		frame = new JFrame("Lebensform b42");
		matrix = new Zellhaufen(100, 100, 0.1F);
		matrix.getZelle(-123, 100);
		canvas = new Canvas(matrix);
		frame.add(canvas);
		frame.setBounds(10, 10, 1000, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loop();
	}
	
	public void loop()
	{
		Thread thread = new Thread(new Runnable() 
		{	
			@Override
			public void run() 
			{
				while(Main.simRunning)
				{
					getZellhaufen().update();
					getCanvas().repaint();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
	
	public static void main(String[] args) 
	{
		System.out.println(Arrays.toString(args));
		new Main();
	}

	public Zellhaufen getZellhaufen()
	{
		return matrix;
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}
}

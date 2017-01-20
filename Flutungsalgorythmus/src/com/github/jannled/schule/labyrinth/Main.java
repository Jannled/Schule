package com.github.jannled.schule.labyrinth;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

public class Main 
{
	JFrame frame;
	Maze maze;
	
	public Main()
	{
		JFrame frame = new JFrame("Maze Flutungsalgorythmus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maze = new Maze(new File("src/com/github/jannled/schule/labyrinth/mazes/Maze01.txt"));
		maze.setPreferredSize(new Dimension(800, 800));
		frame.add(maze);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{	
		new Main();
	}

}
